package sae.semestre.six.entities.pricehistory;

import jakarta.persistence.*;
import sae.semestre.six.entities.inventory.Inventory;

import java.util.Date;

@Entity
@Table(name = "price_history")
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;
    
    @Column(name = "old_price")
    private Double oldPrice;
    
    @Column(name = "new_price")
    private Double newPrice;
    
    @Column(name = "change_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeDate = new Date();
    
    
    public Double getPriceIncrease() {
        return newPrice - oldPrice;
    }
    
    public Double getPercentageChange() {
        return (newPrice - oldPrice) / oldPrice * 100;
    }
} 