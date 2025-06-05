package sae.semestre.six.entities.prescription;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionServiceTest {

    @Test
    void clearAllDataEmptiesMaps() throws Exception {
        PrescriptionService service = new PrescriptionService();
        java.lang.reflect.Field invField = PrescriptionService.class.getDeclaredField("medicineInventory");
        invField.setAccessible(true);
        ((java.util.Map<String,Integer>)invField.get(null)).put("X",1);
        service.clearAllData();
        assertTrue(((java.util.Map<?,?>)invField.get(null)).isEmpty());
    }
}
