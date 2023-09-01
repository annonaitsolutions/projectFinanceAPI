package annona.dao;

import annona.domain.Inventory;
import annona.domain.InventoryForProduction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.List;

@Repository
public class InventoryForProductionDAOImpl implements InventoryForProductionDAO{
	
	@PersistenceContext
	EntityManager em;
	
	public EntityManager entityManager() {
		EntityManager em = new InventoryForProductionDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void insertInventoryForProduction(InventoryForProduction inventoryForProduction){
		em.persist(inventoryForProduction);
	}

	@Override
	public InventoryForProduction getByInventoryForProductionId(Long id) {
		if (id == null)
			return null;
		return em.find(InventoryForProduction.class, id);
	}

	@Override
	@Transactional
	public void updateInventoryForProduction(InventoryForProduction inventoryForProduction) {
		em.merge(inventoryForProduction);
		em.flush();
	}

	@Override
	public Collection<InventoryForProduction> getList() {
		Query query = em.createQuery("SELECT o FROM InventoryForProduction o");
		return (Collection<InventoryForProduction>) query.getResultList();
	}
}
