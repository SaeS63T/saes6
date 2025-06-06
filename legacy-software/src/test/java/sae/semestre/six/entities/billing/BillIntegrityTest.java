package sae.semestre.six.entities.billing;

import org.junit.jupiter.api.Test;
import sae.semestre.six.entities.patient.Patient;
import sae.semestre.six.entities.doctor.Doctor;

import static org.junit.jupiter.api.Assertions.*;

public class BillIntegrityTest {

    @Test
    public void testIntegrityHash() {
        Patient patient = new Patient();
        patient.setId(1L);
        Doctor doctor = new Doctor();
        doctor.setId(1L);

        Bill bill = new Bill(patient, doctor);
        bill.addDetail("X", 1, 10.0);
        bill.finalizeBill();

        assertNotNull(bill.getIntegrityHash());
        assertTrue(bill.verifyIntegrity());
    }
}
