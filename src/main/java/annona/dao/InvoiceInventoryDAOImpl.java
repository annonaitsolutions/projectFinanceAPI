package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.InvoiceInventory;

@Repository
public class InvoiceInventoryDAOImpl implements InvoiceInventoryDAO{
	
	@PersistenceContext
	EntityManager em;
	
	public EntityManager entityManager() {
		EntityManager em = new InvoiceInventoryDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void insertInvoiceInventory(InvoiceInventory invoiceInventory){

		em.persist(invoiceInventory);

	}
	

	@Override
	public InvoiceInventory getByInvoiceInventoryId(Long id) {
		if (id == null)
			return null;
		return em.find(InvoiceInventory.class, id);
	}

	@Transactional
	public void updateInvoiceInventory(InvoiceInventory invoiceInventory) {

		em.merge(invoiceInventory);
		em.flush();

	}
	

	@Override
	public TypedQuery<InvoiceInventory> getInventoryByKeyList(String poKey, String customerName) {
		{
			if (poKey == null || poKey.length() == 0 || customerName == null || customerName.length() == 0 )
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<InvoiceInventory> q = em.createQuery(
					"SELECT o FROM InvoiceInventory AS o WHERE o.poKey = :poKey and o.customerName = :customerName",
					InvoiceInventory.class);
			q.setParameter("poKey", poKey);
			q.setParameter("customerName", customerName);
		
			return q;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public Collection<InvoiceInventory> getList() {

		Query query = em.createQuery("SELECT o FROM InvoiceInventory o");

		return (Collection<InvoiceInventory>) query.getResultList();
	}
	
	@Override
	public TypedQuery<InvoiceInventory> getInvoiceInventoryBycustomerName(String customerName)  {
		TypedQuery<InvoiceInventory> p;
	
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<InvoiceInventory> q = em.createQuery("SELECT o FROM InvoiceInventory AS o WHERE o.customerName = :customerName",InvoiceInventory.class);
			q.setParameter("customerName", customerName);
			
			try {
				 p = q;
			} catch (Exception ex) {
				return null;
			}
			
			return p;
		
     }

	@Override
	public List<InvoiceInventory> getInvoiceInventoryByCustomerNWarehouse(String customerName, String warehouseName) {
		if (customerName == null || customerName.length() == 0 || warehouseName == null || warehouseName.length() == 0)
			throw new IllegalArgumentException(
					"The customerName or warehouseName argument is required");
		
		TypedQuery<InvoiceInventory> q = em.createQuery(
				                         "SELECT o FROM InvoiceInventory AS o WHERE o.customerName = :customerName and o.wareHouseName =:warehouseName",
				                         InvoiceInventory.class);
		
		q.setParameter("customerName", customerName);
		q.setParameter("warehouseName", warehouseName);
		
		return q.getResultList();
	}

	/**
	 * Method to get Invoice inventory by warehouse
	 * @param warehouseName
	 * @return
	 */
	@Override
	public List<InvoiceInventory> getInvoiceInventoryByWarehouse(String warehouseName) {
		if (warehouseName == null || warehouseName.length() == 0)
			throw new IllegalArgumentException(
					"The warehouseName argument is required");
		
		TypedQuery<InvoiceInventory> q = em.createQuery(
				                         "SELECT o FROM InvoiceInventory AS o WHERE o.wareHouseName =:warehouseName",
				                         InvoiceInventory.class);
		
		
		q.setParameter("warehouseName", warehouseName);
		
		return q.getResultList();
	}

}
