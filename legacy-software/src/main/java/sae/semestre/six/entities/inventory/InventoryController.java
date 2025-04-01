package sae.semestre.six.entities.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sae.semestre.six.entities.model.SupplierInvoice;
import sae.semestre.six.entities.model.SupplierInvoiceDetail;
import sae.semestre.six.entities.email.EmailService;
import java.util.*;
import java.util.stream.Collectors;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    
    @Autowired
    private InventoryDao inventoryDao;
    
    private final EmailService emailService = EmailService.getInstance();
    
    
    @PostMapping("/supplier-invoice")
    public String processSupplierInvoice(@RequestBody SupplierInvoice invoice) {
        try {
            
            for (SupplierInvoiceDetail detail : invoice.getDetails()) {
                Inventory inventory = detail.getInventory();
                
                
                inventory.setQuantity(inventory.getQuantity() + detail.getQuantity());
                inventory.setUnitPrice(detail.getUnitPrice());
                inventory.setLastRestocked(new Date());
                
                
                inventoryDao.update(inventory);
            }
            
            return "Supplier invoice processed successfully";
        } catch (Exception e) {
            
            return "Error: " + e.getMessage();
        }
    }
    
    
    @GetMapping("/low-stock")
    public List<Inventory> getLowStockItems() {
        return inventoryDao.findAll().stream()
            .filter(Inventory::needsRestock)
            .collect(Collectors.toList());
    }
    
    
    @PostMapping("/reorder")
    public String reorderItems() {
        List<Inventory> lowStockItems = inventoryDao.findNeedingRestock();
        
        for (Inventory item : lowStockItems) {
            
            int reorderQuantity = item.getReorderLevel() * 2;
            
            
            try (FileWriter fw = new FileWriter("C:\\hospital\\orders.txt", true)) {
                fw.write("REORDER: " + item.getItemCode() + ", Quantity: " + reorderQuantity + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
            emailService.sendEmail(
                "supplier@example.com",
                "Reorder Request",
                "Please restock " + item.getName() + " (Quantity: " + reorderQuantity + ")"
            );
        }
        
        return "Reorder requests sent for " + lowStockItems.size() + " items";
    }
} 