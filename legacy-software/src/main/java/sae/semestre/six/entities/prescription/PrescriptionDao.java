package sae.semestre.six.entities.prescription;

import sae.semestre.six.base.GenericDao;

import java.util.List;

public interface PrescriptionDao extends GenericDao<Prescription, Long> {
    List<Prescription> findByPatientId(Long patientId);
} 