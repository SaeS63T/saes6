package sae.semestre.six.entities.billing.dto;

import java.util.List;

public class BillingRequest {
    private String patientId;
    private String doctorId;
    private List<String> treatments;

    public BillingRequest(
            String patientId,
            String doctorId,
            List<String> treatments
    ) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.treatments = treatments;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public List<String> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<String> treatments) {
        this.treatments = treatments;
    }
}

