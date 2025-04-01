package sae.semestre.six.entities.appointment;

import sae.semestre.six.base.DaoHibernateAbstrait;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public class ImplDaoRendezVous extends DaoHibernateAbstrait<Appointment, Long> implements DaoRendezVous {
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Appointment> trouverParIdPatient(Long idPatient) {
        return getGestionnaireEntite()
                .createQuery("FROM Appointment WHERE patient.id = :idPatient")
                .setParameter("idPatient", idPatient)
                .getResultList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Appointment> trouverParIdMedecin(Long idMedecin) {
        return getGestionnaireEntite()
                .createQuery("FROM Appointment WHERE medecin.id = :idMedecin")
                .setParameter("idMedecin", idMedecin)
                .getResultList();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Appointment> trouverParPlageDates(Date dateDebut, Date dateFin) {
        return getGestionnaireEntite()
                .createQuery("FROM Appointment WHERE dateRendezVous BETWEEN :dateDebut AND :dateFin")
                .setParameter("dateDebut", dateDebut)
                .setParameter("dateFin", dateFin)
                .getResultList();
    }
} 