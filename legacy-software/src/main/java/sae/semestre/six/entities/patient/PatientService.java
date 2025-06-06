package sae.semestre.six.entities.patient;

import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientDao patientDao;

    public PatientService(
            PatientDao patientDao
    ) {
        this.patientDao = patientDao;
    }

    public Patient getById(Long id) {
        return patientDao.findById(id);
    }

}
