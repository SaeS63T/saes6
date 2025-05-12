package sae.semestre.six.entities.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sae.semestre.six.entities.appointment.AppointmentDao;
import sae.semestre.six.entities.doctor.DoctorDao;
import sae.semestre.six.entities.appointment.Appointment;
import sae.semestre.six.entities.doctor.Doctor;
import sae.semestre.six.entities.email.EmailService;
import java.util.*;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {
    
    @Autowired
    private AppointmentDao appointmentDao;
    
    @Autowired
    private DoctorDao doctorDao;
    
    private final EmailService emailService = EmailService.getInstance();
    
    
    @PostMapping("/appointment")
    public String scheduleAppointment(
            @RequestParam Long doctorId,
            @RequestParam Long patientId,
            @RequestParam Date appointmentDate) {
        try {
            Doctor doctor = doctorDao.findById(doctorId);
            
            
            List<Appointment> doctorAppointments = appointmentDao.findByDoctorId(doctorId);
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
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    
    @GetMapping("/available-slots")
    public List<Date> getAvailableSlots(@RequestParam Long doctorId, @RequestParam Date date) {
        List<Date> availableSlots = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        
        for (int hour = 9; hour <= 17; hour++) {
            cal.set(Calendar.HOUR_OF_DAY, hour);
            cal.set(Calendar.MINUTE, 0);
            
            boolean slotAvailable = true;
            for (Appointment app : appointmentDao.findByDoctorId(doctorId)) {
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