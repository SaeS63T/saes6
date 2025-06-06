package sae.semestre.six.entities.inventory;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sae.semestre.six.entities.email.EmailService;
import sae.semestre.six.entities.pricehistory.PriceHistoryRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {

    @Test
    void lowStockItemsFiltered() {
        InventoryRepository repo = Mockito.mock(InventoryRepository.class);
        PriceHistoryRepository hRepo = Mockito.mock(PriceHistoryRepository.class);
        EmailService email = Mockito.mock(EmailService.class);
        Inventory low = new Inventory();
        low.setQuantity(1);
        low.setReorderLevel(5);
        Mockito.when(repo.findAll()).thenReturn(List.of(low));
        InventoryService service = new InventoryService();
        try {
            java.lang.reflect.Field f1 = InventoryService.class.getDeclaredField("inventoryDao");
            f1.setAccessible(true);
            f1.set(service, repo);
            java.lang.reflect.Field f2 = InventoryService.class.getDeclaredField("priceHistoryDao");
            f2.setAccessible(true);
            f2.set(service, hRepo);
            java.lang.reflect.Field f3 = InventoryService.class.getDeclaredField("emailService");
            f3.setAccessible(true);
            f3.set(service, email);
        } catch(Exception e) { throw new RuntimeException(e); }

        List<Inventory> result = service.getLowStockItems();
        assertEquals(1, result.size());
    }
}
