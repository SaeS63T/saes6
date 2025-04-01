package sae.semestre.six.dao;

import sae.semestre.six.model.Doctor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DoctorDaoImpl extends AbstractHibernateDao<Doctor, Long> implements DoctorDao {
    
    @Override
    public Doctor findByDoctorNumber(String doctorNumber) {
        return (Doctor) getEntityManager()
                .createQuery("FROM Doctor WHERE doctorNumber = :doctorNumber")
                .setParameter("doctorNumber", doctorNumber)
                .getSingleResult();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Doctor> findBySpecialization(String specialization) {
        return getEntityManager()
                .createQuery("FROM Doctor WHERE specialization = :specialization")
                .setParameter("specialization", specialization)
                .getResultList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Doctor> findByDepartment(String department) {
        return getEntityManager()
                .createQuery("FROM Doctor WHERE department = :department")
                .setParameter("department", department)
                .getResultList();
    }
} 