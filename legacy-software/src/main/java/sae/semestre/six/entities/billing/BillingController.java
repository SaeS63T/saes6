package sae.semestre.six.entities.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sae.semestre.six.entities.billing.dto.BillingRequest;

import java.util.List;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private final BillingService billingService;

    @Autowired
    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/process")
    public String processBill(@RequestBody BillingRequest request) {
        try {
            billingService.processBill(
                    request.getPatientId(),
                    request.getDoctorId(),
                    request.getTreatments().toArray(new String[0])
            );
            return "Bill processed successfully.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @PutMapping("/price")
    public String updatePrice(@RequestParam String treatment, @RequestParam double price) {
        billingService.updateTreatmentPrice(treatment, price);
        return "Price updated";
    }

    @GetMapping("/prices")
    public List<String> getPrices() {
        return billingService.getFormattedPrices();
    }

    @GetMapping("/insurance")
    public String calculateInsurance(@RequestParam double amount) {
        double coverage = amount; // Placeholder logic
        return "Insurance coverage: $" + coverage;
    }

    @GetMapping("/revenue")
    public String getTotalRevenue() {
        return "Not tracked here anymore. Use a dedicated reporting module.";
    }

    @GetMapping("/pending")
    public String getPendingBills() {
        return "Pending bill tracking is deprecated.";
    }
}