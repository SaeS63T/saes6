package sae.semestre.six.entities.appointment;

import org.springframework.stereotype.Repository;
import sae.semestre.six.base.AbstractHibernateDao;

import java.util.Date;
import java.util.List;

@Repository
public class ImplDaoRendezVous extends AbstractHibernateDao<Appointment, Long> implements DaoRendezVous {
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Appointment> trouverParIdPatient(Long idPatient) {
        return getEntityManager()
                .createQuery("FROM Appointment WHERE patient.id = :idPatient")
                .setParameter("idPatient", idPatient)
                .getResultList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Appointment> trouverParIdMedecin(Long idMedecin) {
        return getEntityManager()
                .createQuery("FROM Appointment WHERE medecin.id = :idMedecin")
                .setParameter("idMedecin", idMedecin)
                .getResultList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Appointment> trouverParPlageDates(Date dateDebut, Date dateFin) {
        return getEntityManager()
                .createQuery("FROM Appointment WHERE dateRendezVous BETWEEN :dateDebut AND :dateFin")
                .setParameter("dateDebut", dateDebut)
                .setParameter("dateFin", dateFin)
                .getResultList();
    }
} 