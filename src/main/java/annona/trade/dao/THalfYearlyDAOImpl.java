package annona.trade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.trade.domain.THalfYearly;

@Repository
public class THalfYearlyDAOImpl implements THalfYearlyDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new THalfYearlyDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<THalfYearly> getList() {

		TypedQuery<THalfYearly> q = em.createQuery(
				"SELECT o FROM THalfYearly o ", THalfYearly.class);
		return q;

	}
	
	@Override
	public TypedQuery<THalfYearly> getByTransIdList(String transactionId)  {
		 {
			if (transactionId == null || transactionId.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<THalfYearly> q = em.createQuery("SELECT o FROM THalfYearly AS o WHERE o.transactionId = :transactionId",THalfYearly.class);
			q.setParameter("transactionId", transactionId);
			return q;
		}
           }
	
	@Transactional
	public void updateHalfYearly(THalfYearly repay) {

		em.merge(repay);
		em.flush();

	}
	
	@Override
	public  THalfYearly getByHalfYearlyId(Long id) {
		if (id == null)
			return null;
		return em.find(THalfYearly.class, id);
	}
	
	
	
	
	@Transactional
	public void createRepay(THalfYearly repayment) {	
		em.persist(repayment);				    
	}

	/**
	 * Method to get trade HalfYearly records based on customer name
	 */
	@Override
	public List<THalfYearly> getTHalfYearlyListByCustomer(String customer) {
		TypedQuery<THalfYearly> q = em.createQuery("SELECT o FROM THalfYearly AS o WHERE o.customer = :customer",THalfYearly.class);
		q.setParameter("customer", customer);
		
		return q.getResultList();
	}


}
