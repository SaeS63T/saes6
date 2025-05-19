package sae.semestre.six.entities.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sae.semestre.six.base.Utils;
import sae.semestre.six.entities.email.EmailService;
import sae.semestre.six.entities.supplierinvoice.SupplierInvoice;
import sae.semestre.six.entities.supplierinvoice.SupplierInvoiceDetail;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryDao;

    private final EmailService emailService = EmailService.getInstance();

    public String processSupplierInvoice(SupplierInvoice invoice) {
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

    public List<Inventory> getLowStockItems() {
        return inventoryDao.findAll().stream()
                .filter(Inventory::needsRestock)
                .collect(Collectors.toList());
    }

    public String reorderItems() {
        List<Inventory> lowStockItems = inventoryDao.findNeedingRestock();

        for (Inventory item : lowStockItems) {
            int reorderQuantity = item.getReorderLevel() * 2;

            String filePath = "C:\\hospital\\orders.txt";
            String fileContent = "REORDER: " + item.getItemCode() + ", Quantity: " + reorderQuantity + "\n";

            try {
                Utils.writeToFile(filePath, fileContent);
            } catch (IOException e) {
                System.out.println(e);
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
