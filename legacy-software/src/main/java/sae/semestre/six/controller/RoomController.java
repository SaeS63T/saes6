package sae.semestre.six.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sae.semestre.six.dao.AppointmentDao;
import sae.semestre.six.dao.RoomDao;
import sae.semestre.six.model.Appointment;
import sae.semestre.six.model.Room;

import java.util.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    
    @Autowired
    private RoomDao roomDao;
    
    @Autowired
    private AppointmentDao appointmentDao;
    
    
    @PostMapping("/assign")
    public String assignRoom(@RequestParam Long appointmentId, @RequestParam String roomNumber) {
        try {
            Room room = roomDao.findByRoomNumber(roomNumber);
            Appointment appointment = appointmentDao.findById(appointmentId);
            
            
            if (room.getType().equals("SURGERY") && 
                !appointment.getDoctor().getSpecialization().equals("SURGEON")) {
                return "Error: Only surgeons can use surgery rooms";
            }
            
            
            if (room.getCurrentPatientCount() >= room.getCapacity()) {
                return "Error: Room is at full capacity";
            }
            
            
            room.setCurrentPatientCount(room.getCurrentPatientCount() + 1);
            appointment.setRoomNumber(roomNumber);
            
            roomDao.update(room);
            appointmentDao.update(appointment);
            
            return "Room assigned successfully";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    
    @GetMapping("/availability")
    public Map<String, Object> getRoomAvailability(@RequestParam String roomNumber) {
        Room room = roomDao.findByRoomNumber(roomNumber);
        Map<String, Object> result = new HashMap<>();
        
        result.put("roomNumber", room.getRoomNumber());
        result.put("capacity", room.getCapacity());
        result.put("currentPatients", room.getCurrentPatientCount());
        result.put("available", room.canAcceptPatient());
        
        return result;
    }
} 