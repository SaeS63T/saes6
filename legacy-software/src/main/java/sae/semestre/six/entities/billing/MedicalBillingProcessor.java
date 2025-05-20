package sae.semestre.six.entities.billing;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MedicalBillingProcessor {

    private final Map<String, Double> treatmentPrices = new HashMap<>();

    public MedicalBillingProcessor() {
        // Initialize with some default treatments and prices
        treatmentPrices.put("CONSULTATION", 50.0);
        treatmentPrices.put("XRAY", 100.0);
        treatmentPrices.put("BLOOD_TEST", 30.0);
    }

    public void updatePrice(String treatment, double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        treatmentPrices.put(treatment, price);
    }

    public List<String> getFormattedPrices() {
        return treatmentPrices.entrySet().stream()
                .map(entry -> entry.getKey() + ": $" + entry.getValue())
                .collect(Collectors.toList());
    }
}