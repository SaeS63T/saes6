package sae.semestre.six.entities.pricehistory;

import sae.semestre.six.base.GenericDao;
import sae.semestre.six.entities.inventory.Inventory;

import java.util.List;

public interface PriceHistoryRepository extends GenericDao<PriceHistory, Long> {
    List<PriceHistory> findByInventory(Inventory inventory);
}
