package sae.semestre.six.entities.scheduling.dto;


import java.util.Date;

public class AppointmentRequest {
    private Long doctorId;
    private Long patientId;
    private Date appointmentDate;

    public AppointmentRequest(
            Long doctorId,
            Long patientId,
            Date appointmentDate
    ) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
