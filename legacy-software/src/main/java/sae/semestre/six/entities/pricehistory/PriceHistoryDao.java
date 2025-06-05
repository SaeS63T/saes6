package sae.semestre.six.entities.pricehistory;

import org.springframework.stereotype.Repository;
import sae.semestre.six.base.AbstractHibernateDao;
import sae.semestre.six.entities.inventory.Inventory;

import java.util.List;

@Repository
public class PriceHistoryDao extends AbstractHibernateDao<PriceHistory, Long> implements PriceHistoryRepository {
    @Override
    @SuppressWarnings("unchecked")
    public List<PriceHistory> findByInventory(Inventory inventory) {
        return getEntityManager()
                .createQuery("FROM PriceHistory WHERE inventory = :inventory ORDER BY changeDate DESC")
                .setParameter("inventory", inventory)
                .getResultList();
    }
}
