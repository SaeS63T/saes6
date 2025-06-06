package sae.semestre.six.entities.billing;

import sae.semestre.six.base.GenericDao;

import java.util.Date;
import java.util.List;

public interface BillRepository extends GenericDao<Bill, Long> {
    Bill findByBillNumber(String billNumber);
    List<Bill> findByPatientId(Long patientId);
    List<Bill> findByDoctorId(Long doctorId);
    List<Bill> findByDateRange(Date startDate, Date endDate);
    List<Bill> findByStatus(String status);
} 