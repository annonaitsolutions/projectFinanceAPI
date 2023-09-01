package annona.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.BuyerDiffBankEvent;

@Repository
public class BuyerDiffBankDAOImpl implements BuyerDiffBankEventDAO {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public void insertDiffBankEvents(BuyerDiffBankEvent buyerDiffBankEvent) {
		
		em.persist(buyerDiffBankEvent);
		
	}


public TypedQuery<BuyerDiffBankEvent> findAllEvents(String masterKey) {
		

		TypedQuery<BuyerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerDiffBankEvent AS o WHERE  o.masterKey=:masterKey and o.status='Approved'",BuyerDiffBankEvent.class);
		q.setParameter("masterKey", masterKey);
		return q;
	}
	
public TypedQuery<BuyerDiffBankEvent> checkMasterKeyBuyerDiffrent(String masterKey) {
	

	TypedQuery<BuyerDiffBankEvent> q = em
			.createQuery(
					"SELECT o FROM BuyerDiffBankEvent AS o WHERE  o.masterKey=:masterKey",BuyerDiffBankEvent.class);
	q.setParameter("masterKey", masterKey);
	return q;
}


	@SuppressWarnings("unchecked")
	public Collection<BuyerDiffBankEvent> findAllupdateEvents() {
		 Query query = em.createQuery("SELECT o FROM BuyerDiffBankEvent o where o.status='NA' and o.date13 IS NOT NULL");
			
		    return (Collection<BuyerDiffBankEvent>) query.getResultList();
	}

   
	public BuyerDiffBankEvent findEvent(Long id) {
		
		return em.find(BuyerDiffBankEvent.class,id);
	}
	@Transactional
	public void updateBuyerDiffBankEvent(BuyerDiffBankEvent buyerDiffBankEvent) {
		
		em.merge(buyerDiffBankEvent);
		em.flush();
			
	}
	
	@Override
	public TypedQuery<BuyerDiffBankEvent> findDetails(String customer,
			String supplier, String masterKey, String goods) {
		TypedQuery<BuyerDiffBankEvent> q = em.createQuery("SELECT o FROM BuyerDiffBankEvent AS o WHERE o.customerName = :customer and o.supplier=:supplier and o.masterKey =:masterKey and o.goods =:goods",BuyerDiffBankEvent.class);
		q.setParameter("customer", customer);
		q.setParameter("supplier", supplier);
		q.setParameter("masterKey", masterKey);
		q.setParameter("goods", goods);
		return q;
	}


	@Override
	public TypedQuery<BuyerDiffBankEvent> findPoKey(String poKey) {
     TypedQuery<BuyerDiffBankEvent> q = em.createQuery("SELECT o FROM BuyerDiffBankEvent AS o WHERE  o.poKey =:poKey ",BuyerDiffBankEvent.class);
		
		q.setParameter("poKey", poKey);
		
		return q;
	}
	
	
	public TypedQuery<BuyerDiffBankEvent> findUpdateEvents(String name) {
		TypedQuery<BuyerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerDiffBankEvent AS o WHERE o.customerName =:name and o.date13 IS NULL ",
						BuyerDiffBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}
	
	public TypedQuery<BuyerDiffBankEvent> findCheckStatus() {
		TypedQuery<BuyerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerDiffBankEvent AS o WHERE o.status='Approved' ",
						BuyerDiffBankEvent.class);
				
		return q;
	}
	public TypedQuery<BuyerDiffBankEvent> findCheckStatus(String name) {
		TypedQuery<BuyerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerDiffBankEvent AS o WHERE o.customerName =:name and o.status='Approved' ",
						BuyerDiffBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}
	
	public TypedQuery<BuyerDiffBankEvent> findStatus(String name) {
		TypedQuery<BuyerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerDiffBankEvent AS o WHERE o.customerName =:name and o.status='NA' ",
						BuyerDiffBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}
	
	public TypedQuery<BuyerDiffBankEvent> findRejStatus(String name) {
		TypedQuery<BuyerDiffBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerDiffBankEvent AS o WHERE o.customerName =:name and o.status='Rejected' ",
						BuyerDiffBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}



	
	
	

}
