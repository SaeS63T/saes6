package sae.semestre.six.entities.patient;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class PatientServiceTest {

    @Test
    void getByIdDelegatesToDao() {
        PatientDao dao = Mockito.mock(PatientDao.class);
        Patient p = new Patient();
        Mockito.when(dao.findById(1L)).thenReturn(p);
        PatientService service = new PatientService(dao);
        assertSame(p, service.getById(1L));
        Mockito.verify(dao).findById(1L);
    }
}
