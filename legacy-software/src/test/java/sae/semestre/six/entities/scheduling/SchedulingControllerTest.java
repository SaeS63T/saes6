package sae.semestre.six.entities.scheduling;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.Date;
import sae.semestre.six.entities.scheduling.dto.AppointmentRequest;

import static org.junit.jupiter.api.Assertions.*;

public class SchedulingControllerTest {

    @Test
    void scheduleAndSlots() {
        SchedulingService service = Mockito.mock(SchedulingService.class);
        Mockito.when(service.scheduleAppointment(1L,2L,new Date(0),"A1"))
                .thenReturn("ok");
        Mockito.when(service.getAvailableSlots(1L, new Date(0)))
                .thenReturn(Collections.emptyList());

        SchedulingController controller = new SchedulingController(service);
        assertEquals("ok", controller.scheduleAppointment(
                new AppointmentRequestImpl(1L,2L,new Date(0),"A1"))
                .getBody());
        assertTrue(controller.getAvailableSlots(1L, new Date(0)).getBody().isEmpty());
    }

    private static class AppointmentRequestImpl extends AppointmentRequest {
        AppointmentRequestImpl(Long d, Long p, Date date, String room) {
            super(d, p, date, room);
        }
    }
}
