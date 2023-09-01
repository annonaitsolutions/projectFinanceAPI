package annona.trade.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.MasterPlan;
import annona.trade.domain.TMasterPlan;

@Repository
public class TMasterPlanDAOImpl implements TMasterPlanDAO {

	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new TMasterPlanDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public TMasterPlan createMasterPlan(TMasterPlan masterPlan) {

		em.persist(masterPlan);
		return masterPlan;
	}
	
	@Override
	public  TMasterPlan getByMasterPlanId(Long id) {
		if (id == null)
			return null;
		return em.find(TMasterPlan.class, id);
	}
	
	@Transactional
	public void updatePlan(TMasterPlan master) {

		em.merge(master);
		em.flush();

	}
	@Override
	public TypedQuery<TMasterPlan> getByStatusAndCust(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<TMasterPlan> q = em.createQuery("SELECT o FROM TMasterPlan AS o WHERE o.customer = :customer and o.aStatus ='pending'",TMasterPlan.class);
			q.setParameter("customer", customer);
			return q;
		}
           }
	
     public TypedQuery<TMasterPlan> getMasterKey(String customer) {
	
		
		TypedQuery<TMasterPlan> q = em.createQuery("SELECT o FROM TMasterPlan AS o WHERE o.customer =:customer and status='Approved'",TMasterPlan.class);
		
		q.setParameter("customer",customer);
		
		
		
		return q;
	}
	

	
	@Override
	public TypedQuery<TMasterPlan> getMasterPlanFullList(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<TMasterPlan> q = em.createQuery("SELECT o FROM TMasterPlan AS o WHERE o.customer = :customer",TMasterPlan.class);
			q.setParameter("customer", customer);
			return q;
		}
           }
	
	public TypedQuery<TMasterPlan> getMasterPlanByPenStatus() {
		TypedQuery<TMasterPlan> q = em.createQuery(
				"SELECT o FROM TMasterPlan o where o.status='pending'",
				TMasterPlan.class);

		return q;
	}
	
	public TypedQuery<TMasterPlan> getMasterPlanByClientPenStatus() {
		TypedQuery<TMasterPlan> q = em.createQuery(
				"SELECT o FROM TMasterPlan o where o.aStatus='pending'",
				TMasterPlan.class);

		return q;
	}
	
	public TypedQuery<TMasterPlan> getMasterPlanByAppStatus() {
		TypedQuery<TMasterPlan> q = em.createQuery(
				"SELECT o FROM TMasterPlan o where o.status='Approved' and (o.ApprovalSent='No' or o.managerStatus='Rejected')",
				TMasterPlan.class);

		return q;
	}
	
	public TypedQuery<TMasterPlan> getAllMasterPlans() {
		TypedQuery<TMasterPlan> q = em.createQuery(
				"SELECT o FROM TMasterPlan o ",
				TMasterPlan.class);
      return q;
	}

	public TypedQuery<TMasterPlan> getMasterPlanByMng() {
		TypedQuery<TMasterPlan> q = em.createQuery(
				"SELECT o FROM TMasterPlan o where o.managerStatus='Pending' and o.ApprovalSent='Yes'",
				TMasterPlan.class);

		return q;
	}
	
	
	@Override
	public TypedQuery<TMasterPlan> getMasterPlanForAccept(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<TMasterPlan> q = em.createQuery("SELECT o FROM TMasterPlan AS o WHERE o.customer = :customer and o.managerStatus='Approved' and o.accept='None'",TMasterPlan.class);
			q.setParameter("customer", customer);
			return q;
		}
           }
	

	@Override
	public TypedQuery<TMasterPlan> getMasterPlanForFunds(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<TMasterPlan> q = em.createQuery("SELECT o FROM TMasterPlan AS o WHERE o.customer = :customer and o.accept ='Yes'",TMasterPlan.class);
			q.setParameter("customer", customer);
			return q;
		}
           }
	
	@Override
	public TypedQuery<TMasterPlan> getMasterPlanByMasterKey(String masterKey)  {
		 {
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<TMasterPlan> q = em.createQuery("SELECT o FROM TMasterPlan AS o WHERE o.masterKey = :masterKey ",TMasterPlan.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
           }

	
	public TypedQuery<TMasterPlan> getApprovedMasterKey() {

		
		TypedQuery<TMasterPlan> q = em.createQuery("SELECT o FROM TMasterPlan AS o WHERE status='Approved' ",TMasterPlan.class);
	
		return q;
	}

	public TypedQuery<TMasterPlan> getAmount(String userName, String masterKey) {

		if (masterKey == null || masterKey.length() == 0)
			throw new IllegalArgumentException(
					"The username argument is required");
		
		TypedQuery<TMasterPlan> q = em.createQuery("SELECT o FROM TMasterPlan AS o WHERE o.customer = :userName and o.masterKey = :masterKey and status='Approved' ",TMasterPlan.class);
		q.setParameter("userName", userName);
		q.setParameter("masterKey", masterKey);
		return q;
	}
	
	

}
