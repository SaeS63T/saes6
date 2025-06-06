package sae.semestre.six.entities.doctor;

import sae.semestre.six.base.GenericDao;

import java.util.List;

public interface DoctorRepository extends GenericDao<Doctor, Long> {
    Doctor findByDoctorNumber(String doctorNumber);
    List<Doctor> findBySpecialization(String specialization);
    List<Doctor> findByDepartment(String department);
} 