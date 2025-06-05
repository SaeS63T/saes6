package sae.semestre.six.entities.room;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sae.semestre.six.entities.appointment.AppointmentRepository;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RoomServiceTest {

    @Test
    void availabilityUsesRepository() {
        RoomRepository roomRepo = Mockito.mock(RoomRepository.class);
        AppointmentRepository appRepo = Mockito.mock(AppointmentRepository.class);
        Room room = new Room();
        room.setRoomNumber("A1");
        room.setCapacity(1);
        Mockito.when(roomRepo.findByRoomNumber("A1")).thenReturn(room);

        RoomService service = new RoomService();
        java.lang.reflect.Field f1;
        try {
            f1 = RoomService.class.getDeclaredField("roomDao");
            f1.setAccessible(true);
            f1.set(service, roomRepo);
            java.lang.reflect.Field f2 = RoomService.class.getDeclaredField("appointmentDao");
            f2.setAccessible(true);
            f2.set(service, appRepo);
        } catch(Exception e) {throw new RuntimeException(e);}

        Map<String,Object> m = service.getRoomAvailability("A1");
        assertEquals("A1", m.get("roomNumber"));
    }
}
