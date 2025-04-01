package sae.semestre.six.dao;

import sae.semestre.six.model.Room;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDaoImpl extends AbstractHibernateDao<Room, Long> implements RoomDao {
    
    @Override
    public Room findByRoomNumber(String roomNumber) {
        return (Room) getEntityManager()
                .createQuery("FROM Room WHERE roomNumber = :roomNumber")
                .setParameter("roomNumber", roomNumber)
                .getSingleResult();
    }
} 