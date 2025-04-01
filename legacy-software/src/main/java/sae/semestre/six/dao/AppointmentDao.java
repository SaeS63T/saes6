package sae.semestre.six.dao;

import sae.semestre.six.model.Appointment;
import java.util.Date;
import java.util.List;

public interface AppointmentDao extends GenericDao<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByDateRange(Date startDate, Date endDate);
} 