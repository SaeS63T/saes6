package sae.semestre.six.dao;

import sae.semestre.six.model.Prescription;
import java.util.List;

public interface PrescriptionDao extends GenericDao<Prescription, Long> {
    List<Prescription> findByPatientId(Long patientId);
} 