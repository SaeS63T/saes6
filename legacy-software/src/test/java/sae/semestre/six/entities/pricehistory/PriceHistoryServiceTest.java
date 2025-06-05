package sae.semestre.six.entities.pricehistory;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sae.semestre.six.entities.inventory.Inventory;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PriceHistoryServiceTest {

    @Test
    void recordChangeSavesHistory() {
        PriceHistoryRepository repo = Mockito.mock(PriceHistoryRepository.class);
        PriceHistoryService service = new PriceHistoryService();
        try {
            java.lang.reflect.Field f = PriceHistoryService.class.getDeclaredField("priceHistoryDao");
            f.setAccessible(true);
            f.set(service, repo);
        } catch(Exception e) {throw new RuntimeException(e);}
        Inventory inv = new Inventory();
        service.recordChange(inv,1.0,2.0);
        verify(repo).save(any());
    }
}
