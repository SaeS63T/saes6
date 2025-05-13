package sae.semestre.six.entities.room;

import sae.semestre.six.base.AbstractHibernateDao;
import org.springframework.stereotype.Repository;

@Repository
public class RoomDao extends AbstractHibernateDao<Room, Long> implements RoomRepository {
    
    @Override
    public Room findByRoomNumber(String roomNumber) {
        return (Room) getEntityManager()
                .createQuery("FROM Room WHERE roomNumber = :roomNumber")
                .setParameter("roomNumber", roomNumber)
                .getSingleResult();
    }
} 