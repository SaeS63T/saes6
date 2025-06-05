package sae.semestre.six.entities.insurance;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InsuranceServiceTest {

    @Test
    void basicCrud() {
        InsuranceDao dao = Mockito.mock(InsuranceDao.class);
        Insurance ins = new Insurance();
        Mockito.when(dao.save(ins)).thenReturn(ins);
        Mockito.when(dao.findById(1L)).thenReturn(ins);
        Mockito.when(dao.findByPatientId(1L)).thenReturn(List.of(ins));
        InsuranceService service = new InsuranceService(dao);

        assertSame(ins, service.create(ins));
        assertSame(ins, service.getById(1L));
        assertEquals(1, service.getByPatient(1L).size());
        service.delete(1L);
        Mockito.verify(dao).deleteById(1L);
    }
}
