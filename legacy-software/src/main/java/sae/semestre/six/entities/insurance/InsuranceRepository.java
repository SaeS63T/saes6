package sae.semestre.six.entities.insurance;

import sae.semestre.six.base.GenericDao;

import java.util.List;

public interface InsuranceRepository extends GenericDao<Insurance, Long> {
    List<Insurance> findByPatientId(Long patientId);
    Insurance findByPolicyNumber(String policyNumber);
}
