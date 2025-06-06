package sae.semestre.six.entities.insurance;

import org.springframework.stereotype.Repository;
import sae.semestre.six.base.AbstractHibernateDao;

import java.util.List;

@Repository
public class InsuranceDao extends AbstractHibernateDao<Insurance, Long> implements InsuranceRepository {

    @Override
    @SuppressWarnings("unchecked")
    public List<Insurance> findByPatientId(Long patientId) {
        return getEntityManager()
                .createQuery("FROM Insurance WHERE patient.id = :patientId")
                .setParameter("patientId", patientId)
                .getResultList();
    }

    @Override
    public Insurance findByPolicyNumber(String policyNumber) {
        return (Insurance) getEntityManager()
                .createQuery("FROM Insurance WHERE policyNumber = :policyNumber")
                .setParameter("policyNumber", policyNumber)
                .getSingleResult();
    }
}
