package annona.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Repayment;

@Repository
public class RepaymentDAOImpl implements RepaymentDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new RepaymentDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<Repayment> getRepaymentBYMAsterKey(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<Repayment> q = em.createQuery(
					"SELECT o FROM Repayment AS o WHERE o.masterKey = :masterKey",
					Repayment.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}
	
	@Override
	public TypedQuery<Repayment> getMasterPlanForRepayment(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Repayment> q = em.createQuery("SELECT o FROM Repayment AS o WHERE o.customer = :customer",Repayment.class);
			q.setParameter("customer", customer);
			return q;
		}
           }
	
	public TypedQuery<Repayment> getRepayByIdAndStatus() {
		TypedQuery<Repayment> q = em.createQuery(
				"SELECT o FROM Repayment o where o.cStatus='Pending'",
				Repayment.class);

		return q;
	}
	
	public TypedQuery<Repayment> getRepayFullList() {
		TypedQuery<Repayment> q = em.createQuery(
				"SELECT o FROM Repayment o",
				Repayment.class);

		return q;
	}
	
	public TypedQuery<Repayment> getRepayByIdAndStatusBank() {
		TypedQuery<Repayment> q = em.createQuery(
				"SELECT o FROM Repayment o where o.cStatus='Approved' and o.bStatus='Pending'",
				Repayment.class);

		return q;
	}
	
	public TypedQuery<Repayment> getRepayByAppMngStatus() {
		TypedQuery<Repayment> q = em.createQuery(
				"SELECT o FROM Repayment o where o.status='Pending'",
				Repayment.class);

		return q;
	}
	
	public TypedQuery<Repayment> getRepayByAccept() {
		TypedQuery<Repayment> q = em.createQuery(
				"SELECT o FROM Repayment o where o.accept='Yes' and o.payConfirm='Pending'",
				Repayment.class);

		return q;
	}
	
	@Override
	public TypedQuery<Repayment> getList() {

		TypedQuery<Repayment> q = em.createQuery(
				"SELECT o FROM Repayment o ", Repayment.class);
		return q;

	}
	
	@Transactional
	public void updateRepayment(Repayment repay) {

		em.merge(repay);
		em.flush();

	}
	
	@Override
	public  Repayment getByRepaymentId(Long id) {
		if (id == null)
			return null;
		return em.find(Repayment.class, id);
	}
	
	
	
	
	@Transactional
	public void createRepay(Repayment repayment) {	
		em.persist(repayment);				    
	}
	
	@Override
	public TypedQuery<Repayment> getMasterPlanForRepaymentAndAppStatus(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Repayment> q = em.createQuery("SELECT o FROM Repayment AS o WHERE o.customer = :customer and o.status='Approved' and o.accept='Pending'",Repayment.class);
			q.setParameter("customer", customer);
			return q;
		}
           }

	/**
	 * Method to get repayment list based on accept 
	 */
	@Override
	public List<Repayment> getRepaymentListByAccept(String accept) {
		TypedQuery<Repayment> q = em.createQuery("SELECT o FROM Repayment AS o WHERE o.accept = :accept",Repayment.class);
		q.setParameter("accept", accept);
		
		return q.getResultList();
	}

}
