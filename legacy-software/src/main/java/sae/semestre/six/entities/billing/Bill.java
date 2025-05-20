package sae.semestre.six.entities.billing;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import sae.semestre.six.entities.doctor.Doctor;
import sae.semestre.six.entities.patient.Patient;

@Entity
@Table(name = "bills")
public class Bill {

    public enum Status {
        PENDING, PAID, CANCELLED, FINALIZED
    }

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.PENDING;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<BillDetail> details = new HashSet<>();

    protected Bill() { }

    public Bill(Patient patient, Doctor doctor) {
        this.patient = patient;
        this.doctor = doctor;
        this.billDate = new Date();
        this.status = Status.PENDING;
    }

    public void addDetail(String name, int quantity, double price) {
        ensureModifiable();
        BillDetail detail = new BillDetail(name, quantity, price);
        detail.setBill(this);
        this.details.add(detail);
    }

    public void finalizeBill() {
        if (details.isEmpty()) {
            throw new IllegalStateException("Bill must contain at least one detail to be finalized.");
        }
        this.status = Status.FINALIZED;
    }

    public double getTotalAmount() {
        return details.stream()
                .mapToDouble(BillDetail::getTotal)
                .sum();
    }

    public boolean isFinalized() {
        return this.status == Status.FINALIZED;
    }

    private void ensureModifiable() {
        if (isFinalized()) {
            throw new IllegalStateException("Cannot modify a finalized bill.");
        }
    }

    // Getters (pas de setters publics sauf si nécessaires pour JPA)

    public Long getId() { return id; }
    public String getBillNumber() { return billNumber; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public Date getBillDate() { return billDate; }
    public Set<BillDetail> getDetails() { return details; }
    public Status getStatus() { return status; }
} 