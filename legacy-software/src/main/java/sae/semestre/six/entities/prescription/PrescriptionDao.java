package sae.semestre.six.entities.prescription;

import sae.semestre.six.base.AbstractHibernateDao;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PrescriptionDao extends AbstractHibernateDao<Prescription, Long> implements PrescriptionRepository {
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Prescription> findByPatientId(Long patientId) {
        return getEntityManager()
                .createQuery("FROM Prescription WHERE patient.id = :patientId")
                .setParameter("patientId", patientId)
                .getResultList();
    }

    @Override
    public Prescription findByPrescriptionNumber(String number) {
        return (Prescription) getEntityManager()
                .createQuery("FROM Prescription WHERE prescriptionNumber = :num")
                .setParameter("num", number)
                .getSingleResult();
    }
}
