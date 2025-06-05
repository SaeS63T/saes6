package sae.semestre.six.entities.room;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RoomControllerTest {

    @Test
    void assignAndAvailability() throws Exception {
        RoomService service = Mockito.mock(RoomService.class);
        Mockito.when(service.assignRoom(1L, "A1")).thenReturn("OK");
        Mockito.when(service.getRoomAvailability("A1")).thenReturn(Map.of("available", true));

        RoomController controller = new RoomController();
        Field f = RoomController.class.getDeclaredField("roomService");
        f.setAccessible(true);
        f.set(controller, service);

        assertEquals("OK", controller.assignRoom(1L, "A1"));
        assertTrue((Boolean) controller.getRoomAvailability("A1").get("available"));
        Mockito.verify(service).assignRoom(1L, "A1");
    }
}
