package sae.semestre.six.entities.appointment;

import sae.semestre.six.base.GenericDao;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends GenericDao<Appointment, Long> {
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByDateRange(Date startDate, Date endDate);
} 