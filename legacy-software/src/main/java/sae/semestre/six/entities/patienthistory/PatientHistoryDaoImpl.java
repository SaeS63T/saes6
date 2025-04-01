package sae.semestre.six.entities.patienthistory;

import sae.semestre.six.base.AbstractHibernateDao;
import org.springframework.stereotype.Repository;
import jakarta.persistence.Query;
import java.util.*;

@Repository
public class PatientHistoryDaoImpl extends AbstractHibernateDao<PatientHistory, Long> implements PatientHistoryDao {
    
    @Override
    @SuppressWarnings("unchecked")
    public List<PatientHistory> findCompleteHistoryByPatientId(Long patientId) {
        
        return getEntityManager()
            .createQuery("SELECT DISTINCT ph FROM PatientHistory ph " +
                "JOIN ph.patient p " +
                "JOIN ph.appointments a " +
                "JOIN ph.prescriptions pr " +
                "JOIN ph.treatments t " +
                "JOIN ph.bills b " +
                "JOIN ph.labResults lr " +
                "WHERE p.id = :patientId " +
                "ORDER BY ph.visitDate DESC")
            .setParameter("patientId", patientId)
            .getResultList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<PatientHistory> searchByMultipleCriteria(String keyword, Date startDate, Date endDate) {
        
        String sql = "SELECT ph FROM PatientHistory ph " +
            "WHERE (LOWER(ph.diagnosis) LIKE :keyword " +
            "OR LOWER(ph.symptoms) LIKE :keyword " +
            "OR LOWER(ph.notes) LIKE :keyword " +
            "OR EXISTS (SELECT 1 FROM ph.treatments t WHERE LOWER(t.name) LIKE :keyword) " +
            "OR EXISTS (SELECT 1 FROM ph.prescriptions p WHERE LOWER(p.medication) LIKE :keyword)) " +
            "AND ph.visitDate BETWEEN :startDate AND :endDate";
            
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        
        
        return query.getResultList();
    }
} 