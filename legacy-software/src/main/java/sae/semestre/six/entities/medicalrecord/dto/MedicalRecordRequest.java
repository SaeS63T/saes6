package sae.semestre.six.entities.medicalrecord.dto;

import java.util.List;

public class MedicalRecordRequest {
    private Long patientId;
    private Long doctorId;
    private String diagnosis;
    private List<BillingItemRequest> items;

    public MedicalRecordRequest() {
    }

    public MedicalRecordRequest(Long patientId, Long doctorId, String diagnosis, List<BillingItemRequest> items) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diagnosis = diagnosis;
        this.items = items;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<BillingItemRequest> getItems() {
        return items;
    }

    public void setItems(List<BillingItemRequest> items) {
        this.items = items;
    }
}
