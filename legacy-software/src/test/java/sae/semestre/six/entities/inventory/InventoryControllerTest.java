package sae.semestre.six.entities.inventory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sae.semestre.six.entities.pricehistory.PriceHistory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class InventoryControllerTest {

    @Autowired
    private InventoryController inventoryController;

    @Test
    public void testUpdatePriceCreatesHistory() {
        List<PriceHistory> before = inventoryController.getPriceHistory("MED001");
        int initial = before.size();

        inventoryController.updatePrice("MED001", 0.20);

        List<PriceHistory> after = inventoryController.getPriceHistory("MED001");
        assertTrue(after.size() > initial);
    }
}
