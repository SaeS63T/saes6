package sae.semestre.six.entities.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sae.semestre.six.entities.email.EmailService;
import sae.semestre.six.entities.supplierinvoice.SupplierInvoice;
import sae.semestre.six.entities.supplierinvoice.SupplierInvoiceDetail;
import sae.semestre.six.entities.pricehistory.PriceHistory;

import java.util.*;
import java.util.stream.Collectors;
import java.io.FileWriter;
import java.io.IOException;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/supplier-invoice")
    public String processSupplierInvoice(@RequestBody SupplierInvoice invoice) {
        return inventoryService.processSupplierInvoice(invoice);
    }

    @GetMapping("/low-stock")
    public List<Inventory> getLowStockItems() {
        return inventoryService.getLowStockItems();
    }

    @PostMapping("/reorder")
    public String reorderItems() {
        return inventoryService.reorderItems();
    }

    @GetMapping
    public List<Inventory> getInventory() {
        return inventoryService.getAllItems();
    }

    @PutMapping("/{itemCode}/price")
    public String updatePrice(@PathVariable String itemCode, @RequestParam double price) {
        return inventoryService.updatePrice(itemCode, price);
    }

    @GetMapping("/{itemCode}/price-history")
    public List<PriceHistory> getPriceHistory(@PathVariable String itemCode) {
        return inventoryService.getPriceHistory(itemCode);
    }
}
