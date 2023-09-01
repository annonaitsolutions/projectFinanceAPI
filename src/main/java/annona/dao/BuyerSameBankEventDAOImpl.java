package annona.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.BuyerSameBankEvent;

@Repository
public class BuyerSameBankEventDAOImpl implements BuyerSameBankEventDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void insertSameBankEvents(BuyerSameBankEvent buyerSameBankEvent) {

		em.persist(buyerSameBankEvent);

	}

	
public TypedQuery<BuyerSameBankEvent> findAllEvents(String masterKey) {
		

		TypedQuery<BuyerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerSameBankEvent AS o WHERE  o.masterKey=:masterKey and o.status='Approved'",BuyerSameBankEvent.class);
		q.setParameter("masterKey", masterKey);
		return q;
	}

public TypedQuery<BuyerSameBankEvent> checkMasterKeyBuyerSame(String masterKey) {
	

	TypedQuery<BuyerSameBankEvent> q = em
			.createQuery(
					"SELECT o FROM BuyerSameBankEvent AS o WHERE  o.masterKey=:masterKey",BuyerSameBankEvent.class);
	q.setParameter("masterKey", masterKey);
	return q;
}


	@SuppressWarnings("unchecked")
	public Collection<BuyerSameBankEvent> findAllupdateEvents() {

		Query query = em
				.createQuery("SELECT o FROM BuyerSameBankEvent o where o.status='NA' and o.date11 IS NOT NULL");

		return (Collection<BuyerSameBankEvent>) query.getResultList();
	}



	public BuyerSameBankEvent findEvent(Long id) {

		return em.find(BuyerSameBankEvent.class, id);
	}

	@Transactional
	public void updateBuyerSameBankEvent(BuyerSameBankEvent buyerSameBankEvent) {

		em.merge(buyerSameBankEvent);
		em.flush();

	}

	@Override
	public TypedQuery<BuyerSameBankEvent> findDetails(String customer,
			String supplier, String masterKey, String goods) {
		TypedQuery<BuyerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerSameBankEvent AS o WHERE o.customerName = :customer and o.supplier=:supplier and o.masterKey =:masterKey and o.goods =:goods",
						BuyerSameBankEvent.class);
		q.setParameter("customer", customer);
		q.setParameter("supplier", supplier);
		q.setParameter("masterKey", masterKey);
		q.setParameter("goods", goods);
		return q;
	}

	@Override
	public TypedQuery<BuyerSameBankEvent> findPoKey(String poKey) {
		TypedQuery<BuyerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerSameBankEvent AS o WHERE o.poKey = :poKey",
						BuyerSameBankEvent.class);
		q.setParameter("poKey", poKey);
		return q;
	}
	
	public TypedQuery<BuyerSameBankEvent> findUpdateEvents(String name) {
		TypedQuery<BuyerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerSameBankEvent AS o WHERE o.customerName =:name and o.date11 IS NULL ",
						BuyerSameBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}
	
	public TypedQuery<BuyerSameBankEvent> findCheckStatus() {
		TypedQuery<BuyerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerSameBankEvent AS o WHERE o.status='Approved' ",
						BuyerSameBankEvent.class);
				
		return q;
	}
	
	public TypedQuery<BuyerSameBankEvent> findCheckStatus(String name) {
		TypedQuery<BuyerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerSameBankEvent AS o WHERE o.customerName =:name and o.status='Approved' ",
						BuyerSameBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}
	
	
	public TypedQuery<BuyerSameBankEvent> findStatus(String name) {
		TypedQuery<BuyerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerSameBankEvent AS o WHERE o.customerName =:name and o.status='NA' ",
						BuyerSameBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}
	
	public TypedQuery<BuyerSameBankEvent> findRejStatus(String name) {
		TypedQuery<BuyerSameBankEvent> q = em
				.createQuery(
						"SELECT o FROM BuyerSameBankEvent AS o WHERE o.customerName =:name and o.status='Rejected' ",
						BuyerSameBankEvent.class);
		q.setParameter("name", name);
		
		return q;
	}


}
