package sae.semestre.six.dao;

import sae.semestre.six.model.Patient;
import java.util.List;

public interface PatientDao extends GenericDao<Patient, Long> {
    Patient findByPatientNumber(String patientNumber);
    List<Patient> findByLastName(String lastName);
} 