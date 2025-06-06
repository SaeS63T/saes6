package sae.semestre.six.entities.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/assign")
    public String assignRoom(@RequestParam Long appointmentId, @RequestParam String roomNumber) {
        return roomService.assignRoom(appointmentId, roomNumber);
    }

    @GetMapping("/availability")
    public Map<String, Object> getRoomAvailability(@RequestParam String roomNumber) {
        return roomService.getRoomAvailability(roomNumber);
    }
}
