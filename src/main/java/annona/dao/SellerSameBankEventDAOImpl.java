package annona.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.BuyerSameBankEvent;
import annona.domain.SellerSameBankEvent;

@Repository
public class SellerSameBankEventDAOImpl implements SellerSameBankEventDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void insertSameBankEvents(SellerSameBankEvent sellerSameBankEvent) {
		
		em.persist(sellerSameBankEvent);
		
	}


public TypedQuery<SellerSameBankEvent> findAllEvents(String masterKey) {
		

		TypedQuery<SellerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerSameBankEvent AS o WHERE  o.masterKey=:masterKey and o.status='Approved'",SellerSameBankEvent.class);
		q.setParameter("masterKey", masterKey);
		return q;
	}
public TypedQuery<SellerSameBankEvent> checkMasterKeySellerSame(String masterKey) {
	

	TypedQuery<SellerSameBankEvent> q = em
			.createQuery(
					"SELECT o FROM SellerSameBankEvent AS o WHERE  o.masterKey=:masterKey",SellerSameBankEvent.class);
	q.setParameter("masterKey", masterKey);
	return q;
}


	@SuppressWarnings("unchecked")
	public Collection<SellerSameBankEvent> findAllupdateEvents() {
		
		Query query = em.createQuery("SELECT o FROM SellerSameBankEvent o where o.status='NA' and o.date13 IS NOT NULL");
		
		return (Collection<SellerSameBankEvent>)query.getResultList();
	}
	
	public SellerSameBankEvent findEvent(Long id) {
		
		return em.find(SellerSameBankEvent.class,id);
	}

	
/*	@Transactional
	public void updateSellerBankSameEvent(SellerSameBankEvent sellerSameBankEvent) {
		em.merge(sellerSameBankEvent);
		em.flush();
	}*/
	
    @Transactional
	public void updateSellerSameBankEvent(SellerSameBankEvent sellerSameBankEvent) {
		em.merge(sellerSameBankEvent);
		em.flush();
	}


	public TypedQuery<SellerSameBankEvent> findDetails(String customer,
			String buyer, String masterKey, String goods) {
		TypedQuery<SellerSameBankEvent> q = em.createQuery("SELECT o FROM SellerSameBankEvent AS o WHERE o.customerName = :customer and o.buyer=:buyer and o.masterKey =:masterKey and o.goods =:goods",SellerSameBankEvent.class);
		q.setParameter("customer", customer);
		q.setParameter("buyer", buyer);
		q.setParameter("masterKey", masterKey);
		q.setParameter("goods", goods);
		return q;
	}



	@Override
	public TypedQuery<SellerSameBankEvent> findByUsername(String username) {
		if (username == null || username.length() == 0)
			throw new IllegalArgumentException(
					"The username argument is required");
		
		TypedQuery<SellerSameBankEvent> q = em.createQuery("SELECT o FROM SellerSameBankEvent AS o WHERE o.customerName = :username and o.customerCol='Yes'",SellerSameBankEvent.class);
		q.setParameter("username", username);
		return q;
	}


	@Override
	public TypedQuery<SellerSameBankEvent> findMasterKey(String masterKey) {
		TypedQuery<SellerSameBankEvent> q = em.createQuery("SELECT o FROM SellerSameBankEvent AS o WHERE o.masterKey =:masterKey",SellerSameBankEvent.class);
		
		q.setParameter("masterKey", masterKey);
		
		return q;
	}
	
	
	public TypedQuery<SellerSameBankEvent> findInvoiceKey(String invoiceKey) {
		TypedQuery<SellerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerSameBankEvent AS o WHERE o.invoiceKey = :invoiceKey",
						SellerSameBankEvent.class);
		q.setParameter("invoiceKey", invoiceKey);
		return q;
	}
	
	public TypedQuery<SellerSameBankEvent> findUpdateEvents(String name) {
		TypedQuery<SellerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerSameBankEvent AS o WHERE o.customerName =:name and o.date13 IS NULL ",
						SellerSameBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}
	
	public TypedQuery<SellerSameBankEvent> findCheckStatus() {
		TypedQuery<SellerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerSameBankEvent AS o WHERE o.status='Approved' ",
						SellerSameBankEvent.class);		
		return q;
	}
	
	public TypedQuery<SellerSameBankEvent> findCheckStatus(String name) {
		TypedQuery<SellerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerSameBankEvent AS o WHERE o.customerName =:name and o.status='Approved' ",
						SellerSameBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}
	
	public TypedQuery<SellerSameBankEvent> findStatus(String name) {
		TypedQuery<SellerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerSameBankEvent AS o WHERE o.customerName =:name and o.status='NA' ",
						SellerSameBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}
	
	public TypedQuery<SellerSameBankEvent> findRejStatus(String name) {
		TypedQuery<SellerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerSameBankEvent AS o WHERE o.customerName =:name and o.status='Rejected' ",
						SellerSameBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}


}
