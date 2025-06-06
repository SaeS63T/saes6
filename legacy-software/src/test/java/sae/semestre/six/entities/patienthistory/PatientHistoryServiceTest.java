package sae.semestre.six.entities.patienthistory;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PatientHistoryServiceTest {

    @Test
    void searchAndSummary() {
        PatientHistoryRepository repo = Mockito.mock(PatientHistoryRepository.class);
        Mockito.when(repo.searchByMultipleCriteria(Mockito.anyString(), Mockito.any(), Mockito.any()))
                .thenReturn(Collections.emptyList());
        Mockito.when(repo.findCompleteHistoryByPatientId(1L))
                .thenReturn(Collections.emptyList());
        PatientHistoryService service = new PatientHistoryService();
        try {
            java.lang.reflect.Field f = PatientHistoryService.class.getDeclaredField("patientHistoryDao");
            f.setAccessible(true);
            f.set(service, repo);
        } catch(Exception e) {throw new RuntimeException(e);}

        assertTrue(service.searchHistory("x", new Date(), new Date()).isEmpty());
        Map<String,Object> summary = service.getPatientSummary(1L);
        assertEquals(0, summary.get("visitCount"));
    }
}
