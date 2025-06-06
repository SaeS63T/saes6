package sae.semestre.six.entities.medicalrecord;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import sae.semestre.six.entities.billing.BillRepository;
import sae.semestre.six.entities.doctor.Doctor;
import sae.semestre.six.entities.doctor.DoctorRepository;
import sae.semestre.six.entities.email.EmailService;
import sae.semestre.six.entities.medicalrecord.dto.BillingItemRequest;
import sae.semestre.six.entities.patient.Patient;
import sae.semestre.six.entities.patient.PatientRepository;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MedicalRecordServiceTest {

    @Test
    void createRecordWithBilling() {
        MedicalRecordDao dao = Mockito.mock(MedicalRecordDao.class);
        BillRepository billRepo = Mockito.mock(BillRepository.class);
        PatientRepository patientRepo = Mockito.mock(PatientRepository.class);
        DoctorRepository doctorRepo = Mockito.mock(DoctorRepository.class);
        EmailService email = Mockito.mock(EmailService.class);
        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        when(patientRepo.findById(1L)).thenReturn(patient);
        when(doctorRepo.findById(2L)).thenReturn(doctor);
        MedicalRecordService service = new MedicalRecordService(dao,billRepo,patientRepo,doctorRepo,email);

        MedicalRecord rec = service.createRecordWithBilling(1L,2L,"diag", Collections.emptyList());
        assertEquals(patient, rec.getPatient());
        verify(billRepo).save(any());
        verify(dao).save(rec);
    }
}
