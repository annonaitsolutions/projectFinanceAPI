package annona.trade.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Supplier;
import annona.domain.UploadedFile;
import annona.trade.domain.SupplierTrade;

@Repository
public class SupplierTradeDAOImpl implements SupplierTradeDAO {

	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new SupplierTradeDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void createUser(SupplierTrade supplierTrade) {
		em.persist(supplierTrade);
	}

	@SuppressWarnings("unchecked")
	public Collection<SupplierTrade> getList() {

		Query query = em.createQuery("SELECT o FROM SupplierTrade o");

		return (Collection<SupplierTrade>) query.getResultList();
	}

	public SupplierTrade findId(Long id) {

		return em.find(SupplierTrade.class, id);

	}

	public TypedQuery<SupplierTrade> findByName(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<SupplierTrade> q = em.createQuery(
				"SELECT o FROM SupplierTrade AS o WHERE o.name = :name and status='Approved'",
				SupplierTrade.class);
		q.setParameter("name", name);
		return q;
	}

	@Transactional
	public void update(SupplierTrade supplierTrade) {

		em.merge(supplierTrade);

		em.flush();
	}

	@Override
	public TypedQuery<SupplierTrade> getByPending() {

		TypedQuery<SupplierTrade> q = em
				.createQuery(
						"SELECT o FROM SupplierTrade o where o.cStatus = 'Pending' or o.cStatus = 'Rejected'",
						SupplierTrade.class);
		return q;

	}

	public TypedQuery<SupplierTrade> getBySupplier() {

		TypedQuery<SupplierTrade> q = em.createQuery(
				"SELECT o FROM SupplierTrade o where o.status = 'Pending'",
				SupplierTrade.class);
		return q;

	}
	
	public TypedQuery<SupplierTrade> findByCustomerHeadName(String customerHeadName) {
		if (customerHeadName == null || customerHeadName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<SupplierTrade> q = em.createQuery(
				"SELECT o FROM SupplierTrade AS o WHERE o.name = :customerHeadName",
				SupplierTrade.class);
		q.setParameter("customerHeadName", customerHeadName);
		return q;
	}
	
	public TypedQuery<SupplierTrade> findByNameAndStatus(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<SupplierTrade> q = em.createQuery(
				"SELECT o FROM SupplierTrade AS o WHERE o.name = :name and o.status='Approved'",
				SupplierTrade.class);
		q.setParameter("name", name);
		return q;
	}
	
	public TypedQuery<SupplierTrade> findByCustomerHeadNameAndStatus(String customerHeadName) {
		if (customerHeadName == null || customerHeadName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<SupplierTrade> q = em.createQuery(
				"SELECT o FROM SupplierTrade AS o WHERE o.name = :customerHeadName and o.status ='Approved'",
				SupplierTrade.class);
		q.setParameter("customerHeadName", customerHeadName);
		return q;
	}
	
	public TypedQuery<SupplierTrade> getForApproval() {

		TypedQuery<SupplierTrade> q = em.createQuery(
				"SELECT o FROM SupplierTrade o where o.cStatus = 'Pending' or o.cStatus = 'Rejected'",
				SupplierTrade.class);
		return q;

	}
	
	public TypedQuery<SupplierTrade> getForcApproval() {

		TypedQuery<SupplierTrade> q = em.createQuery(
				"SELECT o FROM SupplierTrade o where o.cStatus = 'Pending'",
				SupplierTrade.class);
		return q;

	}
	
	@Override
	public TypedQuery<SupplierTrade> getBySupplierPending(String customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");
		
		TypedQuery<SupplierTrade> q = em
				.createQuery(
						"SELECT o FROM SupplierTrade o where o.bName= :customerName and (o.cStatus = 'Pending' or o.cStatus = 'Rejected')",
						SupplierTrade.class);
		q.setParameter("customerName", customerName);
		return q;

	}
}
