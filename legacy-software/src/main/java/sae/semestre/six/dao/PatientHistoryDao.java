package sae.semestre.six.dao;

import sae.semestre.six.model.PatientHistory;
import java.util.List;
import java.util.Date;

public interface PatientHistoryDao extends GenericDao<PatientHistory, Long> {
    List<PatientHistory> findCompleteHistoryByPatientId(Long patientId);
    List<PatientHistory> searchByMultipleCriteria(String keyword, Date startDate, Date endDate);
} 