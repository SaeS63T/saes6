package sae.semestre.six.dao;

import sae.semestre.six.model.Appointment;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public class AppointmentDaoImpl extends AbstractHibernateDao<Appointment, Long> implements AppointmentDao {
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Appointment> findByPatientId(Long patientId) {
        return getEntityManager()
                .createQuery("FROM Appointment WHERE patient.id = :patientId")
                .setParameter("patientId", patientId)
                .getResultList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Appointment> findByDoctorId(Long doctorId) {
        return getEntityManager()
                .createQuery("FROM Appointment WHERE doctor.id = :doctorId")
                .setParameter("doctorId", doctorId)
                .getResultList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Appointment> findByDateRange(Date startDate, Date endDate) {
        return getEntityManager()
                .createQuery("FROM Appointment WHERE appointmentDate BETWEEN :startDate AND :endDate")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
} 