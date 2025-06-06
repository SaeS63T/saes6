package sae.semestre.six.entities.patienthistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/patient-history")
public class PatientHistoryController {

    @Autowired
    private PatientHistoryService patientHistoryService;

    @GetMapping("/search")
    public List<PatientHistory> searchHistory(
            @RequestParam String keyword,
            @RequestParam Date startDate,
            @RequestParam Date endDate) {
        return patientHistoryService.searchHistory(keyword, startDate, endDate);
    }

    @GetMapping("/patient/{patientId}/summary")
    public Map<String, Object> getPatientSummary(@PathVariable Long patientId) {
        return patientHistoryService.getPatientSummary(patientId);
    }
}
