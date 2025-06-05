package sae.semestre.six.entities.scheduling;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sae.semestre.six.entities.appointment.Appointment;
import sae.semestre.six.entities.appointment.AppointmentService;
import sae.semestre.six.entities.doctor.Doctor;
import sae.semestre.six.entities.doctor.DoctorService;
import sae.semestre.six.entities.email.EmailService;
import sae.semestre.six.entities.patient.Patient;
import sae.semestre.six.entities.patient.PatientService;
import sae.semestre.six.entities.room.Room;
import sae.semestre.six.entities.room.RoomRepository;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SchedulingServiceTest {

    @Test
    void scheduleAppointmentReturnsMessage() {
        AppointmentService appService = Mockito.mock(AppointmentService.class);
        DoctorService docService = Mockito.mock(DoctorService.class);
        PatientService patService = Mockito.mock(PatientService.class);
        RoomRepository roomRepo = Mockito.mock(RoomRepository.class);
        EmailService email = Mockito.mock(EmailService.class);

        Doctor doc = new Doctor();
        Patient pat = new Patient();
        Room room = new Room();
        room.setCapacity(1);
        Mockito.when(docService.getById(1L)).thenReturn(doc);
        Mockito.when(patService.getById(2L)).thenReturn(pat);
        Mockito.when(roomRepo.findByRoomNumber("A1")).thenReturn(room);
        Mockito.when(appService.getByDoctorId(1L)).thenReturn(Collections.emptyList());

        SchedulingService service = new SchedulingService(appService,docService,patService,roomRepo,email);
        String msg = service.scheduleAppointment(1L,2L,new Date(0),"A1");
        assertTrue(msg.toLowerCase().contains("scheduled"));
    }
}
