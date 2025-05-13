package sae.semestre.six.entities.inventory;

import sae.semestre.six.base.GenericDao;

import java.util.List;

public interface InventoryRepository extends GenericDao<Inventory, Long> {
    Inventory findByItemCode(String itemCode);
    List<Inventory> findByQuantityLessThan(Integer quantity);
    List<Inventory> findNeedingRestock();
    void update(Inventory inventory);
    void updateStock(String itemCode, Integer quantity);
    void updatePrice(String itemCode, Double price);
    List<Inventory> findAll();
} 