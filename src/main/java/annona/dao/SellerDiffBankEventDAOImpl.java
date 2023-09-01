package annona.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.BuyerDiffBankEvent;
import annona.domain.SellerDiffBankEvent;
import annona.domain.SellerSameBankEvent;


@Repository
public class SellerDiffBankEventDAOImpl implements SellerDiffBankEventDAO {
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public void insertDiffBankEvents(SellerDiffBankEvent sellerDiffBankEvent) {
		
		em.persist(sellerDiffBankEvent);
		
	}


public TypedQuery<SellerDiffBankEvent> findAllEvents(String masterKey) {
		

		TypedQuery<SellerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerDiffBankEvent AS o WHERE  o.masterKey=:masterKey and o.status='Approved'",SellerDiffBankEvent.class);
		q.setParameter("masterKey", masterKey);
		return q;
	}


public TypedQuery<SellerDiffBankEvent> checkMasterKeySellerDiffrent(String masterKey) {
		

		TypedQuery<SellerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerDiffBankEvent AS o WHERE  o.masterKey=:masterKey",SellerDiffBankEvent.class);
		q.setParameter("masterKey", masterKey);
		return q;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<SellerDiffBankEvent> findAllupdateEvents() {
		
		Query query = em.createQuery("SELECT o FROM SellerDiffBankEvent o where o.status='NA' and o.date16 IS NOT NULL");
		
		return (Collection<SellerDiffBankEvent>)query.getResultList();
	}
	
	public SellerDiffBankEvent findEvent(Long id) {
		
		return em.find(SellerDiffBankEvent.class,id);
	}

	
	@Transactional
	public void updateSellerDiffBankEvent(
			SellerDiffBankEvent sellerDiffBankEvent) {
		em.merge(sellerDiffBankEvent);
		em.flush();
	}


	@Override
	public TypedQuery<SellerDiffBankEvent> findDetails(String customer,
			String buyer, String masterKey, String goods) {
		TypedQuery<SellerDiffBankEvent> q = em.createQuery("SELECT o FROM SellerDiffBankEvent AS o WHERE o.customerName = :customer and o.buyer=:buyer and o.masterKey =:masterKey and o.goods =:goods",SellerDiffBankEvent.class);
		q.setParameter("customer", customer);
		q.setParameter("buyer", buyer);
		q.setParameter("masterKey", masterKey);
		q.setParameter("goods", goods);
		return q;
	}


	@Override
	public TypedQuery<SellerDiffBankEvent> findByUsername(String username) {
		if (username == null || username.length() == 0)
			throw new IllegalArgumentException(
					"The username argument is required");
		
		TypedQuery<SellerDiffBankEvent> q = em.createQuery("SELECT o FROM SellerDiffBankEvent AS o WHERE o.customerName = :username and o.customerCol='Yes'",SellerDiffBankEvent.class);
		q.setParameter("username", username);
		return q;
	}



	@Override
	public TypedQuery<SellerDiffBankEvent> findMasterKey(String masterKey) {
		TypedQuery<SellerDiffBankEvent> q = em.createQuery("SELECT o FROM SellerDiffBankEvent AS o WHERE o.masterKey =:masterKey",SellerDiffBankEvent.class);
		
		q.setParameter("masterKey", masterKey);
		
		return q;
	}

	
	@Override
	public TypedQuery<SellerDiffBankEvent> findInvoiceKey(String invoiceKey) {
     TypedQuery<SellerDiffBankEvent> q = em.createQuery("SELECT o FROM SellerDiffBankEvent AS o WHERE  o.invoiceKey =:invoiceKey ",SellerDiffBankEvent.class);
		
		q.setParameter("invoiceKey", invoiceKey);
		
		return q;
	}
	
	
	public TypedQuery<SellerDiffBankEvent> findUpdateEvents(String name) {
		TypedQuery<SellerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerDiffBankEvent AS o WHERE o.customerName =:name and o.date16 IS NULL ",
						SellerDiffBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}
	
	public TypedQuery<SellerDiffBankEvent> findCheckStatus() {
		TypedQuery<SellerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerDiffBankEvent AS o WHERE o.status='Approved' ",
						SellerDiffBankEvent.class);
		
		return q;
	}
	
	public TypedQuery<SellerDiffBankEvent> findCheckStatus(String name) {
		TypedQuery<SellerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerDiffBankEvent AS o WHERE o.customerName =:name and o.status='Approved' ",
						SellerDiffBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}
	
	public TypedQuery<SellerDiffBankEvent> findStatus(String name) {
		TypedQuery<SellerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerDiffBankEvent AS o WHERE o.customerName =:name and o.status='NA' ",
						SellerDiffBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}
	
	public TypedQuery<SellerDiffBankEvent> findRejStatus(String name) {
		TypedQuery<SellerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM SellerDiffBankEvent AS o WHERE o.customerName =:name and o.status='Rejected' ",
						SellerDiffBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}

}
