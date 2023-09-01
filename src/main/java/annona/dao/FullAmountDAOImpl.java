package annona.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.FullAmount;
import annona.domain.HalfYearly;

@Repository
public class FullAmountDAOImpl implements FullAmountDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new FullAmountDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<FullAmount> getList() {

		TypedQuery<FullAmount> q = em.createQuery(
				"SELECT o FROM FullAmount o ", FullAmount.class);
		return q;

	}
	
	@Transactional
	public void updateFullAmount(FullAmount repay) {

		em.merge(repay);
		em.flush();

	}
	
	@Override
	public  FullAmount getByFullAmountId(Long id) {
		if (id == null)
			return null;
		return em.find(FullAmount.class, id);
	}
	
	@Override
	public TypedQuery<FullAmount> getByTransIdList(String transactionId)  {
		 {
			if (transactionId == null || transactionId.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<FullAmount> q = em.createQuery("SELECT o FROM FullAmount AS o WHERE o.transactionId = :transactionId",FullAmount.class);
			q.setParameter("transactionId", transactionId);
			return q;
		}
           }
	
	
	
	
	@Transactional
	public void createRepay(FullAmount repayment) {	
		em.persist(repayment);				    
	}

	/**
	 * Method to get FullAmount records based on customer name
	 */
	@Override
	public List<FullAmount> getFullAmountListByCustomer(String customer) {
		TypedQuery<FullAmount> q = em.createQuery("SELECT o FROM FullAmount AS o WHERE o.customer = :customer",FullAmount.class);
		q.setParameter("customer", customer);
		
		return q.getResultList();
	}


}
