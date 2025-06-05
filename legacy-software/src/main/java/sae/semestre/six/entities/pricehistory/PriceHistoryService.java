package sae.semestre.six.entities.pricehistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sae.semestre.six.entities.inventory.Inventory;

import java.util.Date;
import java.util.List;

@Service
public class PriceHistoryService {

    @Autowired
    private PriceHistoryRepository priceHistoryDao;

    public void recordChange(Inventory inventory, Double oldPrice, Double newPrice) {
        PriceHistory history = new PriceHistory();
        history.setInventory(inventory);
        history.setOldPrice(oldPrice);
        history.setNewPrice(newPrice);
        history.setChangeDate(new Date());
        priceHistoryDao.save(history);
    }

    public List<PriceHistory> getHistoryForInventory(Inventory inventory) {
        return priceHistoryDao.findByInventory(inventory);
    }
}
