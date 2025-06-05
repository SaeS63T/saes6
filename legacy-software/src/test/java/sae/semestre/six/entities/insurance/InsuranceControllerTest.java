package sae.semestre.six.entities.insurance;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InsuranceControllerTest {

    @Test
    void crudOperations() {
        InsuranceService service = Mockito.mock(InsuranceService.class);
        InsuranceController controller = new InsuranceController(service);
        Insurance ins = new Insurance();
        Mockito.when(service.create(ins)).thenReturn(ins);
        Mockito.when(service.getById(1L)).thenReturn(ins);
        Mockito.when(service.getByPatient(1L)).thenReturn(List.of(ins));
        Mockito.when(service.update(ins)).thenReturn(ins);

        assertSame(ins, controller.create(ins));
        assertSame(ins, controller.getById(1L));
        assertEquals(1, controller.getByPatient(1L).size());
        assertSame(ins, controller.update(1L, ins));
        controller.delete(1L);
        Mockito.verify(service).delete(1L);
    }
}
