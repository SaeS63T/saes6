package sae.semestre.six.entities.patienthistory;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PatientHistoryControllerTest {

    @Test
    void searchAndSummary() throws Exception {
        PatientHistoryService service = Mockito.mock(PatientHistoryService.class);
        List<PatientHistory> histories = Collections.emptyList();
        Mockito.when(service.searchHistory(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(histories);
        Mockito.when(service.getPatientSummary(1L)).thenReturn(Map.of("visitCount", 0));

        PatientHistoryController controller = new PatientHistoryController();
        Field f = PatientHistoryController.class.getDeclaredField("patientHistoryService");
        f.setAccessible(true);
        f.set(controller, service);

        assertTrue(controller.searchHistory("x", new Date(), new Date()).isEmpty());
        assertEquals(0, controller.getPatientSummary(1L).get("visitCount"));
    }
}
