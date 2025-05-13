package sae.semestre.six.entities.patient;

import sae.semestre.six.base.GenericDao;

import java.util.List;

public interface PatientRepository extends GenericDao<Patient, Long> {
    Patient findByPatientNumber(String patientNumber);
    List<Patient> findByLastName(String lastName);
} 