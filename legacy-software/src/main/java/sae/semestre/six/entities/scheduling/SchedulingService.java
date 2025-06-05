package sae.semestre.six.entities.scheduling;

import org.springframework.stereotype.Service;
import sae.semestre.six.entities.appointment.Appointment;
import sae.semestre.six.entities.appointment.AppointmentService;
import sae.semestre.six.entities.doctor.Doctor;
import sae.semestre.six.entities.doctor.DoctorService;
import sae.semestre.six.entities.email.EmailService;
import sae.semestre.six.entities.patient.Patient;
import sae.semestre.six.entities.patient.PatientService;
import sae.semestre.six.entities.room.Room;
import sae.semestre.six.entities.room.RoomRepository;

import java.util.*;

@Service
public class SchedulingService {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final RoomRepository roomRepository;
    private final EmailService emailService;

    public SchedulingService(AppointmentService appointmentService,
                             DoctorService doctorService,
                             PatientService patientService,
                             RoomRepository roomRepository,
                             EmailService emailService) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.roomRepository = roomRepository;
        this.emailService = emailService;
    }

    public String scheduleAppointment(Long doctorId, Long patientId, Date appointmentDate, String roomNumber) {
        Doctor doctor = doctorService.getById(doctorId);
        Patient patient = patientService.getById(patientId);
        Room room = roomRepository.findByRoomNumber(roomNumber);

        if (doctor == null || patient == null || room == null) {
            return "Invalid doctor, patient or room";
        }

        List<Appointment> doctorAppointments = appointmentService.getByDoctorId(doctorId);

        for (Appointment existing : doctorAppointments) {
            if (existing.getAppointmentDate().equals(appointmentDate)) {
                return "Doctor is not available at this time";
            }
        }

        if (!room.canAcceptPatient()) {
            return "Room is not available";
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(appointmentDate);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour < 9 || hour > 17) {
            return "Appointments only available between 9 AM and 5 PM";
        }

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(appointmentDate);
        appointment.setStatus("SCHEDULED");
        appointment.setRoomNumber(roomNumber);

        appointmentService.save(appointment);

        room.setCurrentPatientCount(room.getCurrentPatientCount() + 1);
        roomRepository.update(room);

        emailService.sendEmail(
                patient.getPatientNumber() + "@example.com",
                "Appointment Confirmation",
                "Your appointment is scheduled on " + appointmentDate
        );

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
