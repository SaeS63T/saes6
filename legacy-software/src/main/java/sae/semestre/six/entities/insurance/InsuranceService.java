package sae.semestre.six.entities.insurance;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InsuranceService {

    private final InsuranceDao insuranceDao;

    public InsuranceService(InsuranceDao insuranceDao) {
        this.insuranceDao = insuranceDao;
    }

    public Insurance getById(Long id) {
        return insuranceDao.findById(id);
    }

    public List<Insurance> getByPatient(Long patientId) {
        return insuranceDao.findByPatientId(patientId);
    }

    @Transactional
    public Insurance create(Insurance insurance) {
        return insuranceDao.save(insurance);
    }

    @Transactional
    public Insurance update(Insurance insurance) {
        insuranceDao.update(insurance);
        return insurance;
    }

    @Transactional
    public void delete(Long id) {
        insuranceDao.deleteById(id);
    }

    public Insurance findByPolicyNumber(String policyNumber) {
        return insuranceDao.findByPolicyNumber(policyNumber);
    }
}
