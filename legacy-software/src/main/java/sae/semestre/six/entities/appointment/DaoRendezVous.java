package sae.semestre.six.entities.appointment;

import sae.semestre.six.base.GenericDao;

import java.util.Date;
import java.util.List;

public interface DaoRendezVous extends GenericDao<Appointment, Long> {
    List<Appointment> trouverParIdPatient(Long idPatient);
    List<Appointment> trouverParIdMedecin(Long idMedecin);
    List<Appointment> trouverParPlageDates(Date dateDebut, Date dateFin);
} 