package sae.semestre.six.entities.doctor;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorServiceTest {

    @Test
    void getByIdDelegatesToDao() {
        DoctorRepository dao = Mockito.mock(DoctorRepository.class);
        Doctor doc = new Doctor();
        Mockito.when(dao.findById(1L)).thenReturn(doc);
        DoctorService service = new DoctorService(dao);
        assertSame(doc, service.getById(1L));
        Mockito.verify(dao).findById(1L);
    }
}
