package sae.semestre.six.dao;

import sae.semestre.six.model.Doctor;
import java.util.List;

public interface DoctorDao extends GenericDao<Doctor, Long> {
    Doctor findByDoctorNumber(String doctorNumber);
    List<Doctor> findBySpecialization(String specialization);
    List<Doctor> findByDepartment(String department);
} 