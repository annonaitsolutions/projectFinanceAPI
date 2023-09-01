package annona.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.LimitBurst;

@Repository
public class LimitBurstDAOImpl implements LimitBurstDAO{
	
	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new LimitBurstDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Override
	public TypedQuery<LimitBurst> getList() {

		TypedQuery<LimitBurst> q = em.createQuery(
				"SELECT o FROM LimitBurst o ", LimitBurst.class);
		return q;

	}
	
	@Override
	public LimitBurst getByLimitBurstId(Long id) {
		if (id == null)
			return null;
		return em.find(LimitBurst.class, id);
	}

	@Transactional
	public void updateLimitBurst(LimitBurst limit) {

		em.merge(limit);
		em.flush();

	}

	@Transactional
	public void insertLimitBurst(LimitBurst limit) {

		em.persist(limit);

	}
	
	public TypedQuery<LimitBurst> getByBankStatus() {
		TypedQuery<LimitBurst> q = em.createQuery(
				"SELECT o FROM LimitBurst o where o.bStatus='Pending' or o.aStatus='Rejected'",
				LimitBurst.class);

		return q;
	}
	
	
	public TypedQuery<LimitBurst> getByAppMngStatus() {
		TypedQuery<LimitBurst> q = em.createQuery(
				"SELECT o FROM LimitBurst o where o.aStatus='Pending'",
				LimitBurst.class);

		return q;
	}
	
	public TypedQuery<LimitBurst> getFullList() {
		TypedQuery<LimitBurst> q = em.createQuery(
				"SELECT o FROM LimitBurst o",
				LimitBurst.class);

		return q;
	}
	
	@Override
	public TypedQuery<LimitBurst> getByName(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<LimitBurst> q = em.createQuery("SELECT o FROM LimitBurst AS o WHERE o.customerHeadName = :customer",LimitBurst.class);
			q.setParameter("customer", customer);
			return q;
		}
           }

	/**
	 * Method to get LimitBurst based on poKey
	 * @param poKey
	 * @return
	 */
	@Override
	public List<LimitBurst> getLimitBurstByPOKey(String poKey) {
		TypedQuery<LimitBurst> q = em.createQuery(
								   "SELECT o FROM LimitBurst As o where o.poKey =:poKey",
				LimitBurst.class);
		q.setParameter("poKey", poKey);
		return q.getResultList();
	}
	
	
	

}
