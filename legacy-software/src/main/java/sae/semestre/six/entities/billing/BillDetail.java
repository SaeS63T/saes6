package sae.semestre.six.entities.billing;

import jakarta.persistence.*;

@Entity
@Table(name = "bill_details")
public class BillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @Column(name = "treatment_name", nullable = false)
    private String treatmentName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    protected BillDetail() {}

    public BillDetail(String treatmentName, int quantity, double unitPrice) {
        if (quantity <= 0 || unitPrice < 0) {
            throw new IllegalArgumentException("Quantity must be > 0 and price >= 0");
        }
        this.treatmentName = treatmentName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public double getTotal() {
        return this.quantity * this.unitPrice;
    }

    // Package-private: seul Bill peut l’appeler
    void setBill(Bill bill) {
        this.bill = bill;
    }

    // Getters (pas de setters publics)
    public Long getId() { return id; }
    public Bill getBill() { return bill; }
    public String getTreatmentName() { return treatmentName; }
    public Integer getQuantity() { return quantity; }
    public Double getUnitPrice() { return unitPrice; }
} 