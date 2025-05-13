package sae.semestre.six.entities.billing;

import org.springframework.stereotype.Service;
import sae.semestre.six.entities.doctor.Doctor;
import sae.semestre.six.entities.doctor.DoctorRepository;
import sae.semestre.six.entities.patient.Patient;
import sae.semestre.six.entities.patient.PatientRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BillingService {

    private final BillRepository billRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public BillingService(
            BillRepository billRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository
            ) {
        this.billRepository = billRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }

    public void processBill(String patientId, String source, String[] items) {

    }

    public Bill generateBill(Patient patient, Doctor doctor, List<String> treatments, Map<String, Double> priceList) {
        Bill bill = new Bill();
        bill.setBillNumber("BILL" + System.currentTimeMillis());
        bill.setPatient(patient);
        bill.setDoctor(doctor);

        Set<BillDetail> details = new HashSet<>();
        double total = 0.0;

        for (String treatment : treatments) {
            double price = priceList.getOrDefault(treatment, 0.0);
            total += price;

            BillDetail detail = new BillDetail();
            detail.setBill(bill);
            detail.setTreatmentName(treatment);
            detail.setUnitPrice(price);
            details.add(detail);
        }

        if (total > 500) {
            total *= 0.9;
        }

        bill.setTotalAmount(total);
        bill.setBillDetails(details);

        return bill;
    }

}