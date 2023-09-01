package annona.trade.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.trade.domain.TRepayment;

@Repository
public class TRepaymentDAOImpl implements TRepaymentDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new TRepaymentDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<TRepayment> getRepaymentBYMAsterKey(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<TRepayment> q = em.createQuery(
					"SELECT o FROM TRepayment AS o WHERE o.masterKey = :masterKey",
					TRepayment.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}
	
	@Override
	public TypedQuery<TRepayment> getMasterPlanForRepayment(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<TRepayment> q = em.createQuery("SELECT o FROM TRepayment AS o WHERE o.customer = :customer",TRepayment.class);
			q.setParameter("customer", customer);
			return q;
		}
           }
	
	public TypedQuery<TRepayment> getRepayByIdAndStatus() {
		TypedQuery<TRepayment> q = em.createQuery(
				"SELECT o FROM TRepayment o where o.cStatus='Pending'",
				TRepayment.class);

		return q;
	}
	
	public TypedQuery<TRepayment> getRepayFullList() {
		TypedQuery<TRepayment> q = em.createQuery(
				"SELECT o FROM TRepayment o",
				TRepayment.class);

		return q;
	}
	
	public TypedQuery<TRepayment> getRepayByIdAndStatusBank() {
		TypedQuery<TRepayment> q = em.createQuery(
				"SELECT o FROM TRepayment o where o.cStatus='Approved' and o.bStatus='Pending'",
				TRepayment.class);

		return q;
	}
	
	public TypedQuery<TRepayment> getRepayByAppMngStatus() {
		TypedQuery<TRepayment> q = em.createQuery(
				"SELECT o FROM TRepayment o where o.status='Pending'",
				TRepayment.class);

		return q;
	}
	
	public TypedQuery<TRepayment> getRepayByAccept() {
		TypedQuery<TRepayment> q = em.createQuery(
				"SELECT o FROM TRepayment o where o.accept='Yes' and o.payConfirm='Pending'",
				TRepayment.class);

		return q;
	}
	
	@Override
	public TypedQuery<TRepayment> getList() {

		TypedQuery<TRepayment> q = em.createQuery(
				"SELECT o FROM TRepayment o ", TRepayment.class);
		return q;

	}
	
	@Transactional
	public void updateRepayment(TRepayment repay) {

		em.merge(repay);
		em.flush();

	}
	
	@Override
	public  TRepayment getByRepaymentId(Long id) {
		if (id == null)
			return null;
		return em.find(TRepayment.class, id);
	}
	
	
	
	
	@Transactional
	public void createRepay(TRepayment repayment) {	
		em.persist(repayment);				    
	}
	
	@Override
	public TypedQuery<TRepayment> getMasterPlanForRepaymentAndAppStatus(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<TRepayment> q = em.createQuery("SELECT o FROM TRepayment AS o WHERE o.customer = :customer and o.status='Approved' and o.accept='Pending'",TRepayment.class);
			q.setParameter("customer", customer);
			return q;
		}
           }

	/**
	 * Method to get trade repayment list based on accept 
	 */
	@Override
	public List<TRepayment> getTRepaymentListByAccept(String accept) {
		TypedQuery<TRepayment> q = em.createQuery("SELECT o FROM TRepayment AS o WHERE o.accept = :accept",TRepayment.class);
		q.setParameter("accept", accept);
		
		return q.getResultList();
	}

}
