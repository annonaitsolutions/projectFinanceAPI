package annona.trade.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.trade.domain.BuyerTrade;


@Repository
public class BuyerTradeDAOImpl implements BuyerTradeDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void createUser(BuyerTrade buyerTrade) {
		em.persist(buyerTrade);
	}

	@SuppressWarnings("unchecked")
	public Collection<BuyerTrade> getList() {

		Query query = em.createQuery("SELECT o FROM BuyerTrade o");

		return (Collection<BuyerTrade>) query.getResultList();
	}

	public BuyerTrade findId(Long id) {

		return em.find(BuyerTrade.class, id);

	}

	@Transactional
	public void update(BuyerTrade buyerTrade) {

		em.merge(buyerTrade);

		em.flush();
	}

	public TypedQuery<BuyerTrade> findByName(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<BuyerTrade> q = em.createQuery(
				"SELECT o FROM BuyerTrade AS o WHERE o.name = :name and status='Approved'",
				BuyerTrade.class);
		q.setParameter("name", name);
		return q;
	}

	@Override
	public TypedQuery<BuyerTrade> getByPending() {

		TypedQuery<BuyerTrade> q = em
				.createQuery(
						"SELECT o FROM BuyerTrade o where o.cStatus = 'Pending' or o.cStatus = 'Rejected'",
						BuyerTrade.class);
		return q;

	}

	public TypedQuery<BuyerTrade> getByBuyer() {

		TypedQuery<BuyerTrade> q = em.createQuery(
				"SELECT o FROM BuyerTrade o where o.status = 'Pending'",
				BuyerTrade.class);
		return q;

	}
	
	public TypedQuery<BuyerTrade> findByCustomerHeadName(String customerHeadName) {
		if (customerHeadName == null || customerHeadName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<BuyerTrade> q = em.createQuery(
				"SELECT o FROM BuyerTrade AS o WHERE o.name = :customerHeadName",
				BuyerTrade.class);
		q.setParameter("customerHeadName", customerHeadName);
		return q;
	}
	
	public TypedQuery<BuyerTrade> findByNameAndStatus(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<BuyerTrade> q = em.createQuery(
				"SELECT o FROM BuyerTrade AS o WHERE o.name = :name and o.status='Approved'",
				BuyerTrade.class);
		q.setParameter("name", name);
		return q;
	}
	
	public TypedQuery<BuyerTrade> findByCustomerHeadNameAndStatus(String customerHeadName) {
		if (customerHeadName == null || customerHeadName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<BuyerTrade> q = em.createQuery(
				"SELECT o FROM BuyerTrade AS o WHERE o.name = :customerHeadName and o.status ='Approved'",
				BuyerTrade.class);
		q.setParameter("customerHeadName", customerHeadName);
		return q;
	}
	
	@Override
	public TypedQuery<BuyerTrade> getByPending(String customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");
		
		TypedQuery<BuyerTrade> q = em
				.createQuery(
						"SELECT o FROM BuyerTrade o where o.bName= :customerName and (o.cStatus = 'Pending' or o.cStatus = 'Rejected')",
						BuyerTrade.class);
		q.setParameter("customerName", customerName);
		return q;

	}
	
	public TypedQuery<BuyerTrade> getForApproval() {

		TypedQuery<BuyerTrade> q = em.createQuery(
				"SELECT o FROM BuyerTrade o where o.cStatus = 'Pending' or o.cStatus = 'Rejected'",
				BuyerTrade.class);
		return q;

	}
	
	public TypedQuery<BuyerTrade> getForcApproval() {

		TypedQuery<BuyerTrade> q = em.createQuery(
				"SELECT o FROM BuyerTrade o where o.cStatus = 'Pending'",
				BuyerTrade.class);
		return q;

	}
}
