package sae.semestre.six.entities.room;

import sae.semestre.six.base.GenericDao;

public interface RoomRepository extends GenericDao<Room, Long> {
    Room findByRoomNumber(String roomNumber);
} 