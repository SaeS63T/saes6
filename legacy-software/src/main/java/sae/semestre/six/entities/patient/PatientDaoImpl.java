package sae.semestre.six.entities.patient;

import sae.semestre.six.base.AbstractHibernateDao;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractHibernateDao<Patient, Long> implements PatientDao {
    
    @Override
    public Patient findByPatientNumber(String patientNumber) {
        return (Patient) getEntityManager()
                .createQuery("FROM Patient WHERE patientNumber = :patientNumber")
                .setParameter("patientNumber", patientNumber)
                .getSingleResult();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Patient> findByLastName(String lastName) {
        return getEntityManager()
                .createQuery("FROM Patient WHERE lastName LIKE :lastName")
                .setParameter("lastName", lastName + "%")
                .getResultList();
    }
} 