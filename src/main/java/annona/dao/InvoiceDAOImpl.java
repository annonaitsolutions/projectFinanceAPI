package annona.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Invoice;
import annona.form.InventoryForm;

@Repository
public class InvoiceDAOImpl implements InvoiceDAO{
	
	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new InvoiceDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public Invoice getByInvoiceId(Long id) {
		if (id == null)
			return null;
		return em.find(Invoice.class, id);
	}

	@Transactional
	public void updateInvoice(Invoice invoice) {

		em.merge(invoice);
		em.flush();

	}
	
	@Override
	public TypedQuery<Invoice> getList() {

		TypedQuery<Invoice> q = em.createQuery(
				"SELECT o FROM Invoice o ", Invoice.class);
		return q;

	}

	@Transactional
	public void insertInvoice(Invoice invoice) {

		em.persist(invoice);

	}
	
	public TypedQuery<Invoice> getInvoiceListByMasterKey(String masterKey,String customerHeadName)  {
		 {
			if (masterKey == null || masterKey.length() == 0 || customerHeadName == null || customerHeadName.length() == 0 )
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.masterKey = :masterKey and o.customerHeadName =:customerHeadName",Invoice.class);
			q.setParameter("masterKey", masterKey);
			q.setParameter("customerHeadName", customerHeadName);
			return q;
		}
           }
	
	public TypedQuery<Invoice> getInvoiceByStatus() {
		TypedQuery<Invoice> q = em.createQuery(
				"SELECT o FROM Invoice o where o.status='Pending'",
				Invoice.class);

		return q;
	}
	
	@Override
	public TypedQuery<Invoice> getInvoiceListBycustomerName(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.customerHeadName = :customerName",Invoice.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	
	@Override
	public TypedQuery<Invoice> getInoviceListBycustomerNameAndStatus(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.customerName = :customerName and o.status = 'Approved'",Invoice.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	
	@SuppressWarnings("unchecked")
	public List<InventoryForm> getInoviceListBycustomerName(String customerName) {
		 
				if (customerName == null || customerName.length() == 0)
					throw new IllegalArgumentException(
							"The username argument is required");

		Query query = em
				.createQuery( "SELECT T1.id ,T1.customerName, T1.buyerName,T1.masterKey,T1.poKey,T1.status,T1.poDate,T1.amount,T1.goods,T1.quantity,T2.avail FROM Invoice T1 ,InvoiceInventory T2  WHERE T1.poKey= T2.poKey and T1.customerName = :customerName and T1.status = 'Approved'" );
		query.setParameter("customerName", customerName);
		List<Object[]> invoiceList = query.getResultList();
		List<InventoryForm> autoInventoryForm = new ArrayList<InventoryForm>();
		
		if (invoiceList != null) {
			for (int i = 0; i < invoiceList.size(); i++) {
				InventoryForm inventoryForm = new InventoryForm();
				inventoryForm.setId(Long.parseLong(invoiceList.get(i)[0].toString()));
				inventoryForm.setCustomerName(invoiceList.get(i)[1].toString());
				inventoryForm.setBuyerName(invoiceList.get(i)[2].toString());
				inventoryForm.setMasterKey((invoiceList.get(i)[3].toString()));
				inventoryForm.setPoKey(invoiceList.get(i)[4].toString());
				inventoryForm.setStatus(invoiceList.get(i)[5].toString());
				/* inventoryForm.setPoDate(Date.parse(invoiceList.get(i)[6].toString())); */
				inventoryForm.setAmount(Float.parseFloat(invoiceList.get(i)[7].toString()));
				inventoryForm.setGoods(invoiceList.get(i)[8].toString());
				inventoryForm.setQuantity(invoiceList.get(i)[9].toString());
				inventoryForm.setAvail(Float.parseFloat(invoiceList.get(i)[10].toString()));
				autoInventoryForm.add(inventoryForm);
			}
			return autoInventoryForm;
		} else {
			return null;
		}
	}
	

	
	
	
	@Override
	public TypedQuery<Invoice> getInvoiceListByBranchName(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.customerName = :customerName",Invoice.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	

	public TypedQuery<Invoice> getInvoiceListByMasterKeyAndName(String masterKey, String customerName)  {
		 {
			if (masterKey == null || masterKey.length() == 0 || customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.masterKey = :masterKey and o.customerName = :customerName",Invoice.class);
			q.setParameter("masterKey", masterKey);
			q.setParameter("customerName", customerName);
			return q;
		}
           }

	public TypedQuery<Invoice> getInvoiceListForRates() {
		TypedQuery<Invoice> q = em.createQuery(
				"SELECT o FROM Invoice o where (o.rStatus='Pending' or o.rStatus='Rejected') and o.request='No'",
				Invoice.class);

		return q;
	}
	
	public TypedQuery<Invoice> getInvoiceListForRatesApproval() {
		TypedQuery<Invoice> q = em.createQuery(
				"SELECT o FROM Invoice o where o.rStatus='Pending' and o.request='Yes'",
				Invoice.class);

		return q;
	}
	
	@Override
	public TypedQuery<Invoice> getInvoiceListForMnyRequest(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.customerName = :customerName and o.status='Approved' and requestMoney='No'",Invoice.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	public TypedQuery<Invoice> getInvoiceListForSetRates() {
		TypedQuery<Invoice> q = em.createQuery(
				"SELECT o FROM Invoice o where o.requestMoney='Yes' or o.transStatus='Rejected'",
				Invoice.class);

		return q;
	}
	
	public TypedQuery<Invoice> getInvoiceListForAppMng() {
		TypedQuery<Invoice> q = em.createQuery(
				"SELECT o FROM Invoice o where o.transStatus='Pending'",
				Invoice.class);

		return q;
	}
	
	public TypedQuery<Invoice> getInvoiceListForClearence() {
		TypedQuery<Invoice> q = em.createQuery(
				"SELECT o FROM Invoice o where o.requestMoney='Accepted'",
				Invoice.class);

		return q;
	}
	
	@Override
	public TypedQuery<Invoice> getInvoiceListForMnyRequestAndAccept(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.customerName = :customerName and o.requestMoney='Approved'",Invoice.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	
	@Override
	public TypedQuery<Invoice> getInvoiceListForBuyer(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.buyerName = :customerName and o.transStatus='Cleared'",Invoice.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<Invoice> getInvoiceListByBuyerName(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.buyerName = :customerName",Invoice.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	public TypedQuery<Invoice> getInvoiceClear() {
		TypedQuery<Invoice> q = em.createQuery(
				"SELECT o FROM Invoice o where o.requestMoney='PayClearence'",
				Invoice.class);

		return q;
	}
	
	@Override
	public TypedQuery<Invoice> getInvoiceListForClosing(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.customerName = :customerName and (o.requestMoney='PayDone' or o.requestMoney='PayReceived')",Invoice.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	
	@Override
	public TypedQuery<Invoice> getInvoiceByMasterKeyList(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<Invoice> q = em.createQuery(
					"SELECT o FROM Invoice AS o WHERE o.masterKey = :masterKey",
					Invoice.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}
	
	@Override
	public List<Invoice> getInvoiceByCategoryAndDate(
			String goodsCategory, Date fromDate, Date toDate) {
		
		TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.goodsCategory = :goodsCategory and o.poDate >= :fromDate and o.poDate <= :toDate",Invoice.class);
		q.setParameter("goodsCategory", goodsCategory);
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
		List<Invoice> invoiceList= q.getResultList();
		return invoiceList;
		
	}
	
	public TypedQuery<Invoice> getInvoiceForEvents(String name) {
		TypedQuery<Invoice> q = em.createQuery(
				"SELECT o FROM Invoice o where o.customerName=:name and o.status='Approved'",
				Invoice.class);
		q.setParameter("name", name);

			return q;
		}

	@Override
	public TypedQuery<Invoice> getInvoiceData(String invoiceKey) {
		if (invoiceKey == null || invoiceKey.length() == 0)
			throw new IllegalArgumentException(
					"The poKey argument is required");
		
		TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.poKey = :invoiceKey",Invoice.class);
		q.setParameter("invoiceKey", invoiceKey);
		return q;
	}
	
	
	@Override
	public TypedQuery<Invoice> getPoListBycustomerName(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.customerName = :customerName",Invoice.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<Invoice> getInvoiceListForGoods(String whMngName)  {
		 {
			if (whMngName == null || whMngName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.whMngName = :whMngName and o.status='Approved' and goodsStatus= 'Send'",Invoice.class);
			q.setParameter("whMngName", whMngName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<Invoice> getInvoiceListBuyerForGoods(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.buyerName = :customerName and goodsStatus= 'Sent'",Invoice.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<Invoice> getInvoiceListBuyer(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.buyerName = :customerName",Invoice.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public List<Invoice> getInvoiceReports(String userName,
			String goodsCategory, Date fromDate, Date toDate) {
		
		TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.customerName = :userName and o.goodsCategory = :goodsCategory and o.poDate >= :fromDate and o.poDate <= :toDate",Invoice.class);
		q.setParameter("goodsCategory", goodsCategory);
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
		q.setParameter("userName", userName);
		List<Invoice> invoiceList= q.getResultList();
		return invoiceList;
		
	}
	
	@Override
	public String getModeByQuantity(String userName, String goodsCategory, Date fromDate, Date toDate) {
		String quantity =(String) em.createQuery("SELECT quantity FROM  Invoice AS o "
				+ "WHERE o.customerName = :userName and o.goodsCategory = :goodsCategory and o.poDate >= :fromDate and o.poDate <= :toDate"
				+ " GROUP BY o.quantity ORDER BY COUNT(o.quantity) DESC")
				.setParameter("goodsCategory", goodsCategory).setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate).setParameter("userName", userName).setMaxResults(1).getSingleResult();
		return quantity;
	}
	
	@Override
	public String getModeByQuantity(String goodsCategory, Date fromDate, Date toDate) {
		String quantity =(String) em.createQuery("SELECT quantity FROM  Invoice AS o "
				+ "WHERE o.goodsCategory = :goodsCategory and o.poDate >= :fromDate and o.poDate <= :toDate"
				+ " GROUP BY o.quantity ORDER BY COUNT(o.quantity) DESC")
				.setParameter("goodsCategory", goodsCategory).setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate).setMaxResults(1).getSingleResult();
		return quantity;
	}
	
	@Override
	public TypedQuery<Invoice> getInvoiceListForDisReports(String buyerName)  {
		 {
			if (buyerName == null || buyerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Invoice> q = em.createQuery("SELECT o FROM Invoice AS o WHERE o.buyerName = :buyerName and goodsStatus= 'Received'",Invoice.class);
			q.setParameter("buyerName", buyerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<Invoice> getInvoiceByInvoiceKey(String poKey) {
		{
			if (poKey == null || poKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<Invoice> q = em.createQuery(
					"SELECT o FROM Invoice AS o WHERE o.poKey = :poKey",
					Invoice.class);
			q.setParameter("poKey", poKey);
			return q;
		}
	}

	
	
}
