package annona.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.PoStock;

@Repository
public class PoStockDAOImpl implements PoStockDAO{
	
	@PersistenceContext
	EntityManager em;
	
	public EntityManager entityManager() {
		EntityManager em = new PoStockDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void insertPoStock(PoStock poStock){

		em.persist(poStock);

	}
	

	@Override
	public PoStock getByPoStockId(Long id) {
		if (id == null)
			return null;
		return em.find(PoStock.class, id);
	}

	@Transactional
	public void updatePoStock(PoStock poStock) {

		em.merge(poStock);
		em.flush();

	}
	
	@Override
	public TypedQuery<PoStock> getList() {

		TypedQuery<PoStock> q = em.createQuery(
				"SELECT o FROM PoStock o ", PoStock.class);
		return q;

	}
	
	@Override
	public TypedQuery<PoStock> getPoStockList(String username) {
		{
			if (username == null || username.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<PoStock> q = em.createQuery(
					"SELECT o FROM PoStock AS o WHERE o.customerName = :username",
					PoStock.class);
			q.setParameter("username", username);
			return q;
		}
	}
	
	@Override
	public TypedQuery<PoStock> getPoStockGoodsName(String goodsName, String userName) {
		
			if (goodsName == null || goodsName.length() == 0 || userName == null || userName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<PoStock> q = em.createQuery(
					"SELECT o FROM PoStock AS o WHERE o.goodsName = :goodsName and o.customerName = :userName",
					PoStock.class);
			q.setParameter("goodsName", goodsName);
			q.setParameter("userName", userName);
			return q;
		
	}

	/**
	 * Method to get PO stock list based on customer and warehouse
	 * @param userName
	 * @param warehouseName
	 * @return
	 */
	@Override
	public List<PoStock> getPoStockByCustomerNWarehouse(String userName, String warehouseName) {
		if (userName == null || userName.length() == 0 || warehouseName == null || warehouseName.length() == 0)
			throw new IllegalArgumentException(
					"The username or warehouseName argument is required");

		TypedQuery<PoStock> q = em.createQuery(
								"SELECT o FROM PoStock AS o WHERE o.customerName = :userName and o.wareHouseName = :warehouseName",
								PoStock.class);
		
		q.setParameter("userName", userName);
		q.setParameter("warehouseName", warehouseName);
		
		return q.getResultList();		
	}

	/**
	 * Method to get POStock by warehouse Name 
	 */
	@Override
	public List<PoStock> getPoStockByWarehouse(String warehouseName) {
		if (warehouseName == null || warehouseName.length() == 0)
			throw new IllegalArgumentException(
					"The warehouseName argument is required");

		TypedQuery<PoStock> q = em.createQuery(
								"SELECT o FROM PoStock AS o WHERE o.wareHouseName = :warehouseName",
								PoStock.class);
		
		q.setParameter("warehouseName", warehouseName);
		
		return q.getResultList();	
	}

}
