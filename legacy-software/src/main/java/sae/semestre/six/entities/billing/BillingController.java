package sae.semestre.six.entities.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sae.semestre.six.base.Utils;
import sae.semestre.six.entities.billing.dto.BillingRequest;
import sae.semestre.six.entities.doctor.DoctorService;
import sae.semestre.six.entities.doctor.Doctor;
import sae.semestre.six.entities.patient.Patient;
import sae.semestre.six.entities.email.EmailService;
import java.util.*;
import java.io.*;
import org.hibernate.Hibernate;
import sae.semestre.six.entities.patient.PatientService;

@RestController
@RequestMapping("/billing")
public class BillingController {
    
    private static volatile BillingController instance;
    private Map<String, Double> priceList = new HashMap<>();
    private double totalRevenue = 0.0;
    private List<String> pendingBills = new ArrayList<>();

    private final EmailService emailService;
    private final BillingService billingService;
    private final DoctorService doctorService;
    private final PatientService patientService;

    @Autowired
    private BillingController(
            EmailService emailService,
            BillingService billingService,
            DoctorService doctorService,
            PatientService patientService
    ) {
        this.emailService = emailService;
        this.billingService = billingService;
        this.doctorService = doctorService;
        this.patientService = patientService;

        this.initData();
    }

    private void initData() {
        priceList.put("CONSULTATION", 50.0);
        priceList.put("XRAY", 150.0);
        priceList.put("CHIRURGIE", 1000.0);
    }

    @PostMapping("/process")
    public String processBill(@RequestBody BillingRequest request) {
        try {
            Patient patient = patientService.getById(Long.parseLong(request.getPatientId()));
            Doctor doctor = doctorService.getById(Long.parseLong(request.getDoctorId()));

            Bill bill = billingService.generateBill(patient, doctor, request.getTreatments(), priceList);
            billingService.saveBill(bill);

            String filePath = "C:\\hospital\\billing.txt";
            String fileContent = bill.getBillNumber() + ": $" + bill.getTotalAmount() + "\n";
            Utils.writeToFile(filePath, fileContent);

            totalRevenue += bill.getTotalAmount();

            emailService.sendEmail(
                    "admin@hospital.com",
                    "New Bill Generated",
                    "Bill Number: " + bill.getBillNumber() + "\nTotal: $" + bill.getTotalAmount()
            );

            return "Bill processed successfully";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    @PutMapping("/price")
    public String updatePrice(
            @RequestParam String treatment,
            @RequestParam double price) {
        priceList.put(treatment, price);
        recalculateAllPendingBills();
        return "Price updated";
    }
    
    private void recalculateAllPendingBills() {
        for (String billId : pendingBills) {
            BillingRequest request = new BillingRequest(
                    billId,
                    "RECALC",
                    List.of("CONSULTATION")
            );
            processBill(request);
        }
    }
    
    @GetMapping("/prices")
    public Map<String, Double> getPrices() {
        return priceList;
    }
    
    @GetMapping("/insurance")
    public String calculateInsurance(@RequestParam double amount) {
        double coverage = amount;
        return "Insurance coverage: $" + coverage;
    }
    
    @GetMapping("/revenue")
    public String getTotalRevenue() {
        return "Total Revenue: $" + totalRevenue;
    }
    
    @GetMapping("/pending")
    public List<String> getPendingBills() {
        return pendingBills;
    }
} 