package sae.semestre.six.entities.prescription;

import sae.semestre.six.base.GenericDao;

import java.util.List;

public interface PrescriptionRepository extends GenericDao<Prescription, Long> {
    List<Prescription> findByPatientId(Long patientId);
    Prescription findByPrescriptionNumber(String number);
}
