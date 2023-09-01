package annona.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.HalfYearly;
import annona.domain.Quarterly;

@Repository
public class HalfYearlyDAOImpl implements HalfYearlyDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new HalfYearlyDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<HalfYearly> getList() {

		TypedQuery<HalfYearly> q = em.createQuery(
				"SELECT o FROM HalfYearly o ", HalfYearly.class);
		return q;

	}
	
	@Override
	public TypedQuery<HalfYearly> getByTransIdList(String transactionId)  {
		 {
			if (transactionId == null || transactionId.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<HalfYearly> q = em.createQuery("SELECT o FROM HalfYearly AS o WHERE o.transactionId = :transactionId",HalfYearly.class);
			q.setParameter("transactionId", transactionId);
			return q;
		}
           }
	
	@Transactional
	public void updateHalfYearly(HalfYearly repay) {

		em.merge(repay);
		em.flush();

	}
	
	
	@Override
	public  HalfYearly getByHalfYearlyId(Long id) {
		if (id == null)
			return null;
		return em.find(HalfYearly.class, id);
	}
	
	
	
	
	@Transactional
	public void createRepay(HalfYearly repayment) {	
		em.persist(repayment);				    
	}

	/**
	 * Method to get HalfYearly records based on customer name
	 */
	@Override
	public List<HalfYearly> getHalfYearlyListByCustomer(String customer) {
		TypedQuery<HalfYearly> q = em.createQuery("SELECT o FROM HalfYearly AS o WHERE o.customer = :customer",HalfYearly.class);
		q.setParameter("customer", customer);
		
		return q.getResultList();
	}

}
