package sae.semestre.six.entities.doctor;

import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorDao doctorDao;

    public DoctorService(
            DoctorDao doctorDao
    ) {
        this.doctorDao = doctorDao;
    }

    public Doctor getById(Long id) {
        return doctorDao.findById(id);
    }
}
