package sae.semestre.six.entities.inventory;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "supplier_invoices")
public class SupplierInvoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "invoice_number", unique = true)
    private String invoiceNumber;
    
    @Column(name = "supplier_name")
    private String supplierName;
    
    @Column(name = "invoice_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate = new Date();
    
    @OneToMany(mappedBy = "supplierInvoice", cascade = CascadeType.ALL)
    private Set<SupplierInvoiceDetail> details = new HashSet<>();
    
    @Column(name = "total_amount")
    private Double totalAmount = 0.0;
    
    public Set<SupplierInvoiceDetail> getDetails() {
        return details;
    }
    
    public void setDetails(Set<SupplierInvoiceDetail> details) {
        this.details = details;
    }
} 