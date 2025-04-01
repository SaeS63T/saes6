package sae.semestre.six.entities.inventory;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventory")
public class Inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "item_code", unique = true)
    private String itemCode;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "quantity")
    private Integer quantity = 0;
    
    @Column(name = "unit_price")
    private Double unitPrice;
    
    @Column(name = "reorder_level")
    private Integer reorderLevel;
    
    @Column(name = "last_restocked")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastRestocked;
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getItemCode() {
        return itemCode;
    }
    
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        
        if (needsRestock()) {
            System.out.println("WARNING: Item " + itemCode + " needs restock!");
        }
    }
    
    public Double getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public Integer getReorderLevel() {
        return reorderLevel;
    }
    
    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
    
    public Date getLastRestocked() {
        return lastRestocked;
    }
    
    public void setLastRestocked(Date lastRestocked) {
        this.lastRestocked = lastRestocked;
    }
    
    
    public boolean needsRestock() {
        return quantity <= reorderLevel;
    }
    
    
    public void decrementStock(int amount) {
        this.quantity -= amount;
        if (needsRestock()) {
            System.out.println("WARNING: Item " + itemCode + " needs restock!");
        }
    }
} 