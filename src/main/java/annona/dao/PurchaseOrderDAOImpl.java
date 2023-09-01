package annona.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.PurchaseOrder;

@Repository
public class PurchaseOrderDAOImpl implements PurchaseOrderDAO {
	
	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new PurchaseOrderDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<PurchaseOrder> getList() {

		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o ", PurchaseOrder.class);
		return q;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getPurchaseGoodsListByCustomer(String  customerName) {
		Query q = em.createQuery("SELECT goods FROM PurchaseOrder  WHERE status = 'Approved' and customerName = :customerName", String.class);
		q.setParameter("customerName", customerName);
		return (List<String>) q.getResultList();
	}
	
	
	@Override
	public PurchaseOrder getByPurchaseId(Long id) {
		if (id == null)
			return null;
		return em.find(PurchaseOrder.class, id);
	}

	@Transactional
	public void updatePo(PurchaseOrder purchase) {

		em.merge(purchase);
		em.flush();

	}

	@Transactional
	public void insertPo(PurchaseOrder purchase) {

		em.persist(purchase);

	}
	
	@Override
	public TypedQuery<PurchaseOrder> getPoListByMasterKey(String masterKey)  {
		 {
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.masterKey = :masterKey",PurchaseOrder.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
           }
	
	public TypedQuery<PurchaseOrder> getPurchaseOrderByStatus() {
		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o where o.status='Pending'",
				PurchaseOrder.class);

		return q;
	}
	
	@Override
	public TypedQuery<PurchaseOrder> getPoListBycustomerName(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.customerName = :customerName",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<PurchaseOrder> getPoListByBranchName(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.customerName = :customerName",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<PurchaseOrder> getPoListBySupplierName(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.supplierName = :customerName",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	public TypedQuery<PurchaseOrder> getPurchaseOrderByApproved() {
		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o where o.status='Approved' and o.forWard='No'",PurchaseOrder.class);

		return q;
	}
	
	@Override
	public TypedQuery<PurchaseOrder> getPoListByDocument(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.supplierName = :customerName and o.status='Approved'",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	

	
	@Override
	public TypedQuery<PurchaseOrder> getPoListByMasterKeyAndName(String masterKey, String customerName)  {
		 {
			if (masterKey == null || masterKey.length() == 0 || customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.masterKey = :masterKey and o.customerName = :customerName",PurchaseOrder.class);
			q.setParameter("masterKey", masterKey);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	@Override
	public TypedQuery<PurchaseOrder> getPoListBycustomerNameAndStatus(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.customerName = :customerName and o.goodsStatus = 'Received'",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<PurchaseOrder> getPoBycustomerNameAndStatus(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.customerName = :customerName and o.goodsStatus = 'Received'",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	public TypedQuery<PurchaseOrder> getPurchaseOrderByvStatus(String customerName) {
		 {
				if (customerName == null || customerName.length() == 0)
					throw new IllegalArgumentException(
							"The username argument is required");
		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o where o.supplierName= :customerName and o.vStatus='Pending' and o.status='Pending'",
				PurchaseOrder.class);
		q.setParameter("customerName", customerName);
		return q;
	}

	}
	
	public TypedQuery<PurchaseOrder> getPurchaseOrderForRenewel(String customerName) {
		 {
				if (customerName == null || customerName.length() == 0)
					throw new IllegalArgumentException(
							"The username argument is required");
		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o where o.supplierName= :customerName and o.vStatus='Approved' ",
				PurchaseOrder.class);
		q.setParameter("customerName", customerName);
		return q;
	}

	}
	
	public TypedQuery<PurchaseOrder> getPoListForRates() {
		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o where (o.rStatus='Pending' or o.rStatus='Rejected') and o.request='No'",
				PurchaseOrder.class);

		return q;
	}
	
	public TypedQuery<PurchaseOrder> getPoListForRatesApproval() {
		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o where o.rStatus='Pending' and o.request='Yes'",
				PurchaseOrder.class);

		return q;
	}
	
	@Override
	public TypedQuery<PurchaseOrder> getPoListByMasterKeyAndStatus(String masterKey)  {
		 {
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.masterKey = :masterKey and o.status= 'Approved'",PurchaseOrder.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
           }

	public TypedQuery<PurchaseOrder> getPoListForAppRatesApproval() {
		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o where  o.transStatus is null and o.status='Pending' or o.status is null " ,
				PurchaseOrder.class);

		return q;
	}
	
	@Override
	public TypedQuery<PurchaseOrder> getPoListForPayment(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.customerName = :customerName and o.status='Approved' and (o.transStatus='None' or o.transStatus='Rejected')",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }

	@Override
	public TypedQuery<PurchaseOrder> getPoListByPoKey(String poKey)  {
		 {
			if (poKey == null || poKey.length() == 0)
				throw new IllegalArgumentException(
						"The poKey argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.poKey = :poKey",PurchaseOrder.class);
			q.setParameter("poKey", poKey);
			return q;
		}
           }
	
	public TypedQuery<PurchaseOrder> getPoPaymentClearence() {
		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o where o.status='Approved' and o.transResult='Pending'",
				PurchaseOrder.class);

		return q;
	}
	
	public TypedQuery<PurchaseOrder> getPoMultiplePaymentClearance() {
		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o where o.status='Approved' and o.transResult='Pending' and o.typeOfTrans = 'MultipleTransfer'",
				PurchaseOrder.class);

		return q;
	}
	
	public TypedQuery<PurchaseOrder> getPoMultiplePaymentByTransacrionId(String transactionId) {
		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o where o.status='Approved' and o.transResult='Pending' and o.typeOfTrans = 'MultipleTransfer' and o.transactionId =:transactionId",
				PurchaseOrder.class);
		q.setParameter("transactionId", transactionId);
		return q;
	}
	
	@Override
	public TypedQuery<PurchaseOrder> getPoListBySupplierNameAndClearence(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.supplierName = :customerName and o.transResult='Cleared'",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	public TypedQuery<PurchaseOrder> getPoForEvents(String name) {
		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o where o.customerName =:name and o.status='Approved'",
				PurchaseOrder.class);
		q.setParameter("name", name);

			return q;
		}
	
	
	@Override
	public TypedQuery<PurchaseOrder> getPoPaymentCleared(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.customerName = :customerName and o.transResult='Cleared'",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
	}
     
	
	public TypedQuery<PurchaseOrder> getPoData(String poKey)  {
		
			if (poKey == null || poKey.length() == 0)
				throw new IllegalArgumentException(
						"The poKey argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.poKey = :poKey",PurchaseOrder.class);
			q.setParameter("poKey", poKey);
			return q;
          }
	
	@Override
	public TypedQuery<PurchaseOrder> getPoByMasterKeyList(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<PurchaseOrder> q = em.createQuery(
					"SELECT o FROM PurchaseOrder AS o WHERE o.masterKey = :masterKey",
					PurchaseOrder.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}

	@Override
	public List<PurchaseOrder> getPOByCategoryAndDate(
			String goodsCategory, Date fromDate, Date toDate) {
		
		TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.goodsCategory = :goodsCategory and o.purchaseDate >= :fromDate and o.purchaseDate <= :toDate",PurchaseOrder.class);
		q.setParameter("goodsCategory", goodsCategory);
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
		List<PurchaseOrder> purchaseOrderList= q.getResultList();
		return purchaseOrderList;
		
	}
	
	public TypedQuery<PurchaseOrder> getPoCancel() {
		TypedQuery<PurchaseOrder> q = em.createQuery(
				"SELECT o FROM PurchaseOrder o where o.status='Cancel'",
				PurchaseOrder.class);
	      

			return q;
		}

	
	@Override
	public TypedQuery<PurchaseOrder> getPoListBySupplierNameAndGoodsStatus(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.supplierName = :customerName and o.status='Approved' and o.goodsStatus= 'Send'",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<PurchaseOrder> getPoListGoodsReceive(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.customerName = :customerName  and o.goodsStatus= 'Sent'",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<PurchaseOrder> getPoDocument(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.customerName = :customerName and o.forward = 'Yes'",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<PurchaseOrder> getPoListForUpload(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The pokey argument is required");
			
			TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.customerName = :customerName",PurchaseOrder.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public List<PurchaseOrder> getPOByHeadCategoryAndDate(String userName,
			String goodsCategory, Date fromDate, Date toDate) {
		
		TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.customerName = :userName and o.goodsCategory = :goodsCategory and o.purchaseDate >= :fromDate and o.purchaseDate <= :toDate ORDER BY o.quantity ASC",PurchaseOrder.class);
		q.setParameter("goodsCategory", goodsCategory);
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
		q.setParameter("userName", userName);
		List<PurchaseOrder> purchaseOrderList= q.getResultList();
		return purchaseOrderList;
		
	}

	/**
	 * Method to fetch mode(frequently occurring quantity)
	 */
	@Override
	public String getModeByQuantity(String userName, String goodsCategory, Date fromDate, Date toDate) {
		String quantity =(String) em.createQuery("SELECT quantity FROM  PurchaseOrder AS o "
				+ "WHERE o.customerName = :userName and o.goodsCategory = :goodsCategory and o.purchaseDate >= :fromDate and o.purchaseDate <= :toDate"
				+ " GROUP BY o.quantity ORDER BY COUNT(o.quantity) DESC")
				.setParameter("goodsCategory", goodsCategory).setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate).setParameter("userName", userName).setMaxResults(1).getSingleResult();
		return quantity;
	}
	
	@Override
	public String getModeByQuantity(String goodsCategory, Date fromDate, Date toDate) {
		String quantity =(String) em.createQuery("SELECT quantity FROM  PurchaseOrder AS o "
				+ "WHERE o.goodsCategory = :goodsCategory and o.purchaseDate >= :fromDate and o.purchaseDate <= :toDate"
				+ " GROUP BY o.quantity ORDER BY COUNT(o.quantity) DESC")
				.setParameter("goodsCategory", goodsCategory).setParameter("fromDate", fromDate)
				.setParameter("toDate", toDate).setMaxResults(1).getSingleResult();
		return quantity;
	}





}
