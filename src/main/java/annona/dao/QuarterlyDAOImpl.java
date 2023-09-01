package annona.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Quarterly;

@Repository
public class QuarterlyDAOImpl implements QuarterlyDAO{
	

	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new QuarterlyDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<Quarterly> getList() {

		TypedQuery<Quarterly> q = em.createQuery(
				"SELECT o FROM Quarterly o ", Quarterly.class);
		return q;

	}
	
	@Override
	public TypedQuery<Quarterly> getByTransIdList(String transactionId)  {
		 {
			if (transactionId == null || transactionId.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Quarterly> q = em.createQuery("SELECT o FROM Quarterly AS o WHERE o.transactionId = :transactionId",Quarterly.class);
			q.setParameter("transactionId", transactionId);
			return q;
		}
           }
	
	@Transactional
	public void updateQuarterly(Quarterly repay) {

		em.merge(repay);
		em.flush();

	}
	
	@Override
	public  Quarterly getByQuarterlyId(Long id) {
		if (id == null)
			return null;
		return em.find(Quarterly.class, id);
	}
	
	
	
	
	@Transactional
	public void createRepay(Quarterly repayment) {	
		em.persist(repayment);				    
	}

	/**
	 * Method to get quarterly records based on customer name
	 */
	@Override
	public List<Quarterly> getQuarterlyListByCustomer(String customer) {
		TypedQuery<Quarterly> q = em.createQuery("SELECT o FROM Quarterly AS o WHERE o.customer = :customer",Quarterly.class);
		q.setParameter("customer", customer);
		
		return q.getResultList();
	}

}
