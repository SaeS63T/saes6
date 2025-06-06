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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    
    public Double getPriceIncrease() {
        return newPrice - oldPrice;
    }
    
    public Double getPercentageChange() {
        return (newPrice - oldPrice) / oldPrice * 100;
    }
} 