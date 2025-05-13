package sae.semestre.six.entities.scheduling;

import org.springframework.stereotype.Service;
import sae.semestre.six.entities.appointment.Appointment;
import sae.semestre.six.entities.appointment.AppointmentService;
import sae.semestre.six.entities.doctor.Doctor;
import sae.semestre.six.entities.doctor.DoctorService;
import sae.semestre.six.entities.email.EmailService;

import java.util.*;

@Service
public class SchedulingService {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final EmailService emailService;

    public SchedulingService(AppointmentService appointmentService, DoctorService doctorService, EmailService emailService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.emailService = emailService;
    }

    public String scheduleAppointment(Long doctorId, Long patientId, Date appointmentDate) {
        Doctor doctor = doctorService.getById(doctorId);
        List<Appointment> doctorAppointments = appointmentService.getByDoctorId(doctorId);

        for (Appointment existing : doctorAppointments) {
            if (existing.getAppointmentDate().equals(appointmentDate)) {
                return "Doctor is not available at this time";
            }
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(appointmentDate);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour < 9 || hour > 17) {
            return "Appointments only available between 9 AM and 5 PM";
        }

        emailService.sendEmail(
                doctor.getEmail(),
                "New Appointment Scheduled",
                "You have a new appointment on " + appointmentDate
        );

        return "Appointment scheduled successfully";
    }

    public List<Date> getAvailableSlots(Long doctorId, Date date) {
        List<Appointment> existingAppointments = appointmentService.getByDoctorId(doctorId);
        List<Date> availableSlots = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        for (int hour = 9; hour <= 17; hour++) {
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, 0);

            boolean slotAvailable = true;
            for (Appointment app : appointmentService.getByDoctorId(doctorId)) {
                Calendar appCal = Calendar.getInstance();
                appCal.setTime(app.getAppointmentDate());
                if (appCal.get(Calendar.HOUR_OF_DAY) == hour) {
                    slotAvailable = false;
                    break;
                }
            }

            if (slotAvailable) {
                availableSlots.add(cal.getTime());
            }
        }

        return availableSlots;
    }
}
