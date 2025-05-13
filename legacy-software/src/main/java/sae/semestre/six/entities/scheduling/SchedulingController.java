package sae.semestre.six.entities.scheduling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sae.semestre.six.entities.scheduling.dto.AppointmentRequest;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @PostMapping("/appointment")
    public ResponseEntity<String> scheduleAppointment(@RequestBody AppointmentRequest request) {
        try {
            String result = schedulingService.scheduleAppointment(
                    request.getDoctorId(),
                    request.getPatientId(),
                    request.getAppointmentDate()
            );
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/available-slots")
    public ResponseEntity<List<Date>> getAvailableSlots(@RequestParam Long doctorId, @RequestParam Date date) {
        try {
            List<Date> slots = schedulingService.getAvailableSlots(doctorId, date);
            return ResponseEntity.ok(slots);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
