package sae.semestre.six.entities.insurance;

import jakarta.persistence.*;
import sae.semestre.six.entities.patient.Patient;

import java.util.Date;

@Entity
@Table(name = "insurance")
public class Insurance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "policy_number", unique = true)
    private String policyNumber;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    
    @Column(name = "provider")
    private String provider;
    
    @Column(name = "coverage_percentage")
    private Double coveragePercentage;
    
    @Column(name = "max_coverage")
    private Double maxCoverage;
    
    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Double getCoveragePercentage() {
        return coveragePercentage;
    }

    public void setCoveragePercentage(Double coveragePercentage) {
        this.coveragePercentage = coveragePercentage;
    }

    public Double getMaxCoverage() {
        return maxCoverage;
    }

    public void setMaxCoverage(Double maxCoverage) {
        this.maxCoverage = maxCoverage;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


    public Double calculateCoverage(Double billAmount) {
        Double coverage = billAmount * (coveragePercentage / 100);
        return coverage > maxCoverage ? maxCoverage : coverage;
    }
    
    
    public boolean isValid() {
        return new Date().before(expiryDate);
    }
} 