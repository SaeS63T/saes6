package sae.semestre.six.dao;

import sae.semestre.six.model.Room;

public interface RoomDao extends GenericDao<Room, Long> {
    Room findByRoomNumber(String roomNumber);
} 