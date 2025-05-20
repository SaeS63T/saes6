package sae.semestre.six.entities.billing;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sae.semestre.six.base.Utils;
import sae.semestre.six.entities.doctor.DoctorRepository;
import sae.semestre.six.entities.email.EmailService;
import sae.semestre.six.entities.patient.Patient;
import sae.semestre.six.entities.doctor.Doctor;
import sae.semestre.six.entities.patient.PatientRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class BillingService {

    private final BillRepository billRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final EmailService emailService;
    private final MedicalBillingProcessor billingProcessor;

    public BillingService(BillRepository billRepository,
                          PatientRepository patientRepository,
                          DoctorRepository doctorRepository,
                          EmailService emailService,
                          MedicalBillingProcessor billingProcessor) {
        this.billRepository = billRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.emailService = emailService;
        this.billingProcessor = billingProcessor;
    }

    @Transactional
    public void processBill(String patientId, String doctorId, String[] items) throws IOException {
        Patient patient = patientRepository.findById(Long.parseLong(patientId));

        Doctor doctor = doctorRepository.findById(Long.parseLong(doctorId));

        Bill bill = new Bill(patient, doctor);

        Arrays.stream(items).forEach(itemStr -> {
            String[] parts = itemStr.split(":");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid item format. Expected: name:quantity:price");
            }
            String name = parts[0];
            int quantity = Integer.parseInt(parts[1]);
            double price = Double.parseDouble(parts[2]);

            bill.addDetail(name, quantity, price);
        });

        emailService.sendEmail(
                "admin@hospital.com",
                "New Bill Generated",
                "Bill Number: " + bill.getBillNumber() + "\nTotal: $" + bill.getTotalAmount()
        );

        bill.finalizeBill();
        billRepository.save(bill);

        String filePath = "C:\\hospital\\billing.txt";
        String fileContent = bill.getBillNumber() + ": $" + bill.getTotalAmount() + "\n";
        Utils.writeToFile(filePath, fileContent);
    }

    public void updateTreatmentPrice(String treatment, double price) {
        billingProcessor.updatePrice(treatment, price);
    }

    public List<String> getFormattedPrices() {
        return billingProcessor.getFormattedPrices();
    }
}