package sae.semestre.six.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "treatments")
public class Treatment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "patient_history_id")
    private PatientHistory patientHistory;
    
    @Column(name = "treatment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date treatmentDate;
    
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;
    
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public PatientHistory getPatientHistory() { return patientHistory; }
    public void setPatientHistory(PatientHistory patientHistory) { this.patientHistory = patientHistory; }
    
    public Date getTreatmentDate() { return treatmentDate; }
    public void setTreatmentDate(Date treatmentDate) { this.treatmentDate = treatmentDate; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
} 