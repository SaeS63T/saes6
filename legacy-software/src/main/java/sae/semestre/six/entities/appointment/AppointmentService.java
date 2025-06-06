package sae.semestre.six.entities.appointment;

import org.springframework.stereotype.Service;
import sae.semestre.six.entities.doctor.Doctor;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentDao appointmentDao;

    public AppointmentService(
            AppointmentDao appointmentDao
    ) {
        this.appointmentDao = appointmentDao;
    }

    public List<Appointment> getByDoctorId(Long doctorId) {
        return appointmentDao.findByDoctorId(doctorId);
    }

    public Appointment save(Appointment appointment) {
        return appointmentDao.save(appointment);
    }

}
