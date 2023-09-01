package annona.trade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.FullAmount;
import annona.domain.Quarterly;
import annona.domain.Repayment;
import annona.trade.domain.TQuarterly;

@Repository
public class TQuarterlyDAOImpl implements TQuarterlyDAO{
	

	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new TQuarterlyDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<TQuarterly> getList() {

		TypedQuery<TQuarterly> q = em.createQuery(
				"SELECT o FROM TQuarterly o ", TQuarterly.class);
		return q;

	}
	
	@Override
	public TypedQuery<TQuarterly> getByTransIdList(String transactionId)  {
		 {
			if (transactionId == null || transactionId.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<TQuarterly> q = em.createQuery("SELECT o FROM TQuarterly AS o WHERE o.transactionId = :transactionId",TQuarterly.class);
			q.setParameter("transactionId", transactionId);
			return q;
		}
           }
	
	@Transactional
	public void updateQuarterly(TQuarterly repay) {

		em.merge(repay);
		em.flush();

	}
	
	@Override
	public  TQuarterly getByQuarterlyId(Long id) {
		if (id == null)
			return null;
		return em.find(TQuarterly.class, id);
	}
	
	
	
	
	@Transactional
	public void createRepay(TQuarterly repayment) {	
		em.persist(repayment);				    
	}

	/**
	 * Method to get trade quarterly records based on customer name
	 */
	@Override
	public List<TQuarterly> getTQuarterlyListByCustomer(String customer) {
		TypedQuery<TQuarterly> q = em.createQuery("SELECT o FROM TQuarterly AS o WHERE o.customer = :customer",TQuarterly.class);
		q.setParameter("customer", customer);
		
		return q.getResultList();
	}

}
