package sae.semestre.six.entities.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sae.semestre.six.entities.appointment.Appointment;
import sae.semestre.six.entities.appointment.AppointmentRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomDao;

    @Autowired
    private AppointmentRepository appointmentDao;

    public String assignRoom(Long appointmentId, String roomNumber) {
        try {
            Room room = roomDao.findByRoomNumber(roomNumber);
            Appointment appointment = appointmentDao.findById(appointmentId);

            if (room == null || appointment == null) {
                return "Error: Room or Appointment not found";
            }

            if ("SURGERY".equals(room.getType()) &&
                    !"SURGEON".equals(appointment.getDoctor().getSpecialization())) {
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

    public Map<String, Object> getRoomAvailability(String roomNumber) {
        Room room = roomDao.findByRoomNumber(roomNumber);
        Map<String, Object> result = new HashMap<>();

        if (room != null) {
            result.put("roomNumber", room.getRoomNumber());
            result.put("capacity", room.getCapacity());
            result.put("currentPatients", room.getCurrentPatientCount());
            result.put("available", room.canAcceptPatient());
        } else {
            result.put("error", "Room not found");
        }

        return result;
    }
}

