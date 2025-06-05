package sae.semestre.six.entities.billing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sae.semestre.six.entities.billing.dto.BillingRequest;

import java.io.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BillingControllerTest {

    @Autowired
    private BillingController billingController;

    @Value("${hospital.billing.text.path}")
    private String billingTextPath;
    
    @Test
    public void testProcessBill() {
        
        File billingFile = new File(billingTextPath);
        long initialFileSize = billingFile.length();

        BillingRequest request = new BillingRequest(
                "TEST001",
                "DOC001",
                List.of("CONSULTATION")
        );
        String result = billingController.processBill(request);
        
        
        assertTrue(result.contains("successfully"));
        assertTrue(billingFile.length() > initialFileSize);
    }
    
    @Test
    public void testCalculateInsurance() {
        
        double result = Double.parseDouble(
            billingController.calculateInsurance(1000.0)
                .replace("Insurance coverage: $", "")
        );
        
        
        assertEquals(700.0, result, 0.01);
    }
    
    
    @Test
    public void testUpdatePrice() {
        billingController.updatePrice("CONSULTATION", 75.0);
        assertEquals(75.0, billingController.getPrices().get("CONSULTATION"), 0.01);
    }
} 