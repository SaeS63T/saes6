package sae.semestre.six.entities.billing;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.*;
import sae.semestre.six.entities.doctor.Doctor;
import sae.semestre.six.entities.patienthistory.PatientHistory;
import sae.semestre.six.entities.patient.Patient;

@Entity
@Table(name = "bills")
public class Bill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "bill_number", unique = true)
    private String billNumber;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    
    @Column(name = "bill_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billDate = new Date();
    
    @Column(name = "total_amount")
    private Double totalAmount = 0.0;
    
    @Column(name = "status")
    private String status = "PENDING";
    
    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<BillDetail> billDetails = new HashSet<>();
    
    
    @Column(name = "created_date")
    private Date createdDate = new Date();
    
    @Column(name = "last_modified")
    private Date lastModified = new Date();

    @ManyToOne
    private PatientHistory patientHistory;
    
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getBillNumber() { return billNumber; }

    public void setBillNumber(String billNumber) { this.billNumber = billNumber; }
    
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    
    public Date getBillDate() { return billDate; }
    public void setBillDate(Date billDate) { this.billDate = billDate; }
    
    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { 
        this.status = status;
        this.lastModified = new Date(); 
    }
    
    public Set<BillDetail> getBillDetails() { return billDetails; }
    public void setBillDetails(Set<BillDetail> billDetails) { this.billDetails = billDetails; }
} 