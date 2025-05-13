package sae.semestre.six.entities.labresult;

import jakarta.persistence.*;
import sae.semestre.six.entities.patienthistory.PatientHistory;

import java.util.Date;

@Entity
@Table(name = "lab_results")
public class LabResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "patient_history_id")
    private PatientHistory patientHistory;
    
    @Column(name = "test_name")
    private String testName;
    
    @Column(name = "result_value")
    private String resultValue;
    
    @Column(name = "test_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date testDate;
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public PatientHistory getPatientHistory() { return patientHistory; }
    public void setPatientHistory(PatientHistory patientHistory) { this.patientHistory = patientHistory; }
    
    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }
    
    public String getResultValue() { return resultValue; }
    public void setResultValue(String resultValue) { this.resultValue = resultValue; }
    
    public Date getTestDate() { return testDate; }
    public void setTestDate(Date testDate) { this.testDate = testDate; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
} 