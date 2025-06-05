package sae.semestre.six.entities.billing;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sae.semestre.six.entities.doctor.DoctorRepository;
import sae.semestre.six.entities.email.EmailService;
import sae.semestre.six.entities.patient.PatientRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BillingServiceTest {

    @Test
    void getFormattedPricesDelegates() {
        BillRepository billRepo = Mockito.mock(BillRepository.class);
        PatientRepository patientRepo = Mockito.mock(PatientRepository.class);
        DoctorRepository doctorRepo = Mockito.mock(DoctorRepository.class);
        EmailService email = Mockito.mock(EmailService.class);
        MedicalBillingProcessor proc = Mockito.mock(MedicalBillingProcessor.class);
        Mockito.when(proc.getFormattedPrices()).thenReturn(List.of("X"));
        BillingService service = new BillingService(billRepo,patientRepo,doctorRepo,email,proc);
        assertEquals(List.of("X"), service.getFormattedPrices());
        Mockito.verify(proc).getFormattedPrices();
    }
}
