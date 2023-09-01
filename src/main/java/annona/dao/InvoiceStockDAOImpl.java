package annona.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.InvoiceStock;

@Repository
public class InvoiceStockDAOImpl implements InvoiceStockDAO{
	
	@PersistenceContext
	EntityManager em;
	
	public EntityManager entityManager() {
		EntityManager em = new InvoiceStockDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void insertInvoiceStock(InvoiceStock invoiceStock){

		em.persist(invoiceStock);

	}
	

	@Override
	public InvoiceStock getByInvoiceStockId(Long id) {
		if (id == null)
			return null;
		return em.find(InvoiceStock.class, id);
	}

	@Transactional
	public void updateInvoiceStock(InvoiceStock invoiceStock) {

		em.merge(invoiceStock);
		em.flush();

	}
	
	@Override
	public TypedQuery<InvoiceStock> getList() {

		TypedQuery<InvoiceStock> q = em.createQuery(
				"SELECT o FROM InvoiceStock o ", InvoiceStock.class);
		return q;

	}
	
	@Override
	public TypedQuery<InvoiceStock> getInvoiceStockList(String username) {
		{
			if (username == null || username.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<InvoiceStock> q = em.createQuery(
					"SELECT o FROM InvoiceStock AS o WHERE o.customerName = :username",
					InvoiceStock.class);
			q.setParameter("username", username);
			return q;
		}
	}
	
	@Override
	public TypedQuery<InvoiceStock> getInvoiceStockGoodsName(String goodsName, String userName) {
		
			if (goodsName == null || goodsName.length() == 0 || userName == null || userName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<InvoiceStock> q = em.createQuery(
					"SELECT o FROM InvoiceStock AS o WHERE o.goodsName = :goodsName and o.customerName = :userName",
					InvoiceStock.class);
			q.setParameter("goodsName", goodsName);
			q.setParameter("userName", userName);
			return q;
		
	}

	/**
	 * Method to get Invoice stock based on customerName and warehouseName
	 * @param userName
	 * @param warehouseName
	 * @return
	 */
	@Override
	public List<InvoiceStock> getInvoiceStockByCustomerNWarehouse(String userName, String warehouseName) {
		if (userName == null || userName.length() == 0 || warehouseName == null || warehouseName.length() == 0)
			throw new IllegalArgumentException(
					"The username or warehouseName argument is required");

		TypedQuery<InvoiceStock> q = em.createQuery(
									 "SELECT o FROM InvoiceStock AS o WHERE o.customerName = :userName and o.wareHouseName =:warehouseName",
									 InvoiceStock.class);
		q.setParameter("userName", userName);
		q.setParameter("warehouseName", warehouseName);
		
		return q.getResultList();
	}
	
	/**
	 * Method to get Invoice stock based on warehouseName
	 * @param userName
	 * @param warehouseName
	 * @return
	 */
	@Override
	public List<InvoiceStock> getInvoiceStockByWarehouse(String warehouseName) {
		if (warehouseName == null || warehouseName.length() == 0)
			throw new IllegalArgumentException(
					"The warehouseName argument is required");

		TypedQuery<InvoiceStock> q = em.createQuery(
									 "SELECT o FROM InvoiceStock AS o WHERE o.wareHouseName =:warehouseName",
									 InvoiceStock.class);
		
		q.setParameter("warehouseName", warehouseName);
		
		return q.getResultList();
	}
	
	@Override
	public TypedQuery<InvoiceStock> getInvoiceStockListByCustHead(String username) {
		{
			if (username == null || username.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<InvoiceStock> q = em.createQuery(
					"SELECT o FROM InvoiceStock AS o WHERE o.customerHeadName = :username",
					InvoiceStock.class);
			q.setParameter("username", username);
			return q;
		}
	}

}
