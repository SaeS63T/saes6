package sae.semestre.six.entities.patienthistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatientHistoryService {

    @Autowired
    private PatientHistoryRepository patientHistoryDao;

    public List<PatientHistory> searchHistory(String keyword, Date startDate, Date endDate) {
        return patientHistoryDao.searchByMultipleCriteria(keyword, startDate, endDate);
    }

    public Map<String, Object> getPatientSummary(Long patientId) {
        List<PatientHistory> histories = patientHistoryDao.findCompleteHistoryByPatientId(patientId);

        Map<String, Object> summary = new HashMap<>();
        summary.put("visitCount", histories.size());

        double totalBilled = histories.stream()
                .mapToDouble(PatientHistory::getTotalBilledAmount)
                .sum();

        summary.put("totalBilled", totalBilled);

        return summary;
    }
}
