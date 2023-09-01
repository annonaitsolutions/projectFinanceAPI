package annona.trade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.trade.domain.TFullAmount;

@Repository
public class TFullAmountDAOImpl implements TFullAmountDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new TFullAmountDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<TFullAmount> getList() {

		TypedQuery<TFullAmount> q = em.createQuery(
				"SELECT o FROM TFullAmount o ", TFullAmount.class);
		return q;

	}
	
	@Transactional
	public void updateFullAmount(TFullAmount repay) {

		em.merge(repay);
		em.flush();

	}
	
	@Override
	public  TFullAmount getByFullAmountId(Long id) {
		if (id == null)
			return null;
		return em.find(TFullAmount.class, id);
	}
	
	@Override
	public TypedQuery<TFullAmount> getByTransIdList(String transactionId)  {
		 {
			if (transactionId == null || transactionId.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<TFullAmount> q = em.createQuery("SELECT o FROM TFullAmount AS o WHERE o.transactionId = :transactionId",TFullAmount.class);
			q.setParameter("transactionId", transactionId);
			return q;
		}
           }
	
	
	
	
	@Transactional
	public void createRepay(TFullAmount repayment) {	
		em.persist(repayment);				    
	}

	/**
	 * Method to get trade FullAmount records based on customer name
	 */
	@Override
	public List<TFullAmount> getTFullAmountListByCustomer(String customer) {
		TypedQuery<TFullAmount> q = em.createQuery("SELECT o FROM TFullAmount AS o WHERE o.customer = :customer",TFullAmount.class);
		q.setParameter("customer", customer);
		
		return q.getResultList();
	}


}
