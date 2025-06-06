package sae.semestre.six.entities.billing;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class MedicalBillingProcessor {

    private final Map<String, Double> treatmentPrices = new HashMap<>();

    @Value("${hospital.treatment.defaults:}")
    private String defaultPrices;

    public MedicalBillingProcessor() {
    }

    @PostConstruct
    private void init() {
        if (defaultPrices != null && !defaultPrices.isBlank()) {
            String[] pairs = defaultPrices.split(",");
            for (String pair : pairs) {
                String[] parts = pair.split(":");
                if (parts.length == 2) {
                    treatmentPrices.put(parts[0], Double.parseDouble(parts[1]));
                }
            }
        }
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