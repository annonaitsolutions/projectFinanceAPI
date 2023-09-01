package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Inventory;

@Repository
public class InventoryDAOImpl implements InventoryDAO{
	
	@PersistenceContext
	EntityManager em;
	
	public EntityManager entityManager() {
		EntityManager em = new InventoryDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void insertInventory(Inventory inventory){

		em.persist(inventory);

	}
	

	@Override
	public Inventory getByInventoryId(Long id) {
		if (id == null)
			return null;
		return em.find(Inventory.class, id);
	}

	@Transactional
	public void updateInventory(Inventory inventory) {

		em.merge(inventory);
		em.flush();

	}
	

	@Override
	public TypedQuery<Inventory> getInventoryByKeyList(String poKey, String customerName) {
		{
			if (poKey == null || poKey.length() == 0 || customerName == null || customerName.length() == 0 )
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<Inventory> q = em.createQuery(
					"SELECT o FROM Inventory AS o WHERE o.poKey = :poKey and o.customerName = :customerName",
					Inventory.class);
			q.setParameter("poKey", poKey);
			q.setParameter("customerName", customerName);
	
			return q;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<Inventory> getList() {

		Query query = em.createQuery("SELECT o FROM Inventory o");

		return (Collection<Inventory>) query.getResultList();
	}
	
	@Override
	public TypedQuery<Inventory> getInventoryBycustomerName(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Inventory> q = em.createQuery("SELECT o FROM Inventory AS o WHERE o.customerName = :customerName",Inventory.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<Inventory> getInventoryByGoods(String goods)  {
		 {
			if (goods == null || goods.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Inventory> q = em.createQuery("SELECT o FROM Inventory AS o WHERE o.goods = :goods",Inventory.class);
			q.setParameter("goods", goods);
			return q;
		}
           }


	/**
	 * Method to get Inventory list by warehouseName and customerName
	 * @param warehouseName
	 * @return
	 */
	@Override
	public List<Inventory> getInventoryListByWareHouseNCustomerName(String wareHouseName, String customerName) {
		TypedQuery<Inventory> q = em.createQuery("SELECT o FROM Inventory AS o WHERE o.wareHouseName = :wareHouseName and o.customerName =:customerName",Inventory.class);
		q.setParameter("wareHouseName", wareHouseName);
		q.setParameter("customerName", customerName);
		return q.getResultList();
	}

	/**
	 * Method to get Inventory list by warehouseName
	 * @param warehouseName
	 * @return
	 */
	@Override
	public List<Inventory> getInventoryListByWareHouse(String wareHouseName) {
		TypedQuery<Inventory> q = em.createQuery("SELECT o FROM Inventory AS o WHERE o.wareHouseName = :wareHouseName",Inventory.class);
		q.setParameter("wareHouseName", wareHouseName);
		
		return q.getResultList();
	}

}
