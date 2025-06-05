package sae.semestre.six.entities.scheduling.dto;


import java.util.Date;

public class AppointmentRequest {
    private Long doctorId;
    private Long patientId;
    private Date appointmentDate;
    private String roomNumber;

    public AppointmentRequest(
            Long doctorId,
            Long patientId,
            Date appointmentDate,
            String roomNumber
    ) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.roomNumber = roomNumber;
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

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}
