package sae.semestre.six.entities.medicalrecord;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sae.semestre.six.entities.billing.Bill;
import sae.semestre.six.entities.billing.BillRepository;
import sae.semestre.six.entities.doctor.Doctor;
import sae.semestre.six.entities.doctor.DoctorRepository;
import sae.semestre.six.entities.email.EmailService;
import sae.semestre.six.entities.medicalrecord.dto.BillingItemRequest;
import sae.semestre.six.entities.patient.Patient;
import sae.semestre.six.entities.patient.PatientRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MedicalRecordService {

    private final MedicalRecordDao medicalRecordDao;
    private final BillRepository billRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final EmailService emailService;

    public MedicalRecordService(MedicalRecordDao medicalRecordDao,
                                BillRepository billRepository,
                                PatientRepository patientRepository,
                                DoctorRepository doctorRepository,
                                EmailService emailService) {
        this.medicalRecordDao = medicalRecordDao;
        this.billRepository = billRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.emailService = emailService;
    }

    @Transactional
    public MedicalRecord createRecordWithBilling(Long patientId,
                                                 Long doctorId,
                                                 String diagnosis,
                                                 List<BillingItemRequest> items) {
        Patient patient = patientRepository.findById(patientId);
        Doctor doctor = doctorRepository.findById(doctorId);

        MedicalRecord record = new MedicalRecord();
        record.setPatient(patient);
        record.setDoctor(doctor);
        record.setDiagnosis(diagnosis);
        record.setRecordNumber(UUID.randomUUID().toString());
        record.setRecordDate(new Date());

        Bill bill = new Bill(patient, doctor);
        for (BillingItemRequest item : items) {
            bill.addDetail(item.getName(), item.getQuantity(), item.getUnitPrice());
        }
        bill.finalizeBill();

        billRepository.save(bill);
        medicalRecordDao.save(record);

        emailService.sendEmail(
                patient.getPatientNumber() + "@example.com",
                "New Medical Record",
                "Record " + record.getRecordNumber() + " created. Total due: $" + bill.getTotalAmount()
        );

        return record;
    }
}
