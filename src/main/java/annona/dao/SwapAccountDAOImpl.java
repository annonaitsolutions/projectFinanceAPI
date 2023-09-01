package annona.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.SwapAccount;

@Repository
public class SwapAccountDAOImpl implements SwapAccountDAO {
	
	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new SwapAccountDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	/**
	 * Method to Save Swap Account details
	 */
	@Override
	@Transactional
	public void createSwapAccount(SwapAccount swapAccount) {
		em.persist(swapAccount);		
	}

	/**
	 * Method to display swap accounts based on Status
	 */
	@Override
	public List<SwapAccount> getAllSwapAccountsByStatus(String status) {
		TypedQuery<SwapAccount> q = em.createQuery(
								"SELECT o FROM SwapAccount o where o.status =:status",
								SwapAccount.class);
		
		q.setParameter("status",status);
		
		return q.getResultList();
		
	}
	
	/**
	 * Method to find Swap Account based on id
	 */
	@Override
	public SwapAccount findById(Long id) {
		return em.find(SwapAccount.class, id);
	}

	/**
	 * Method to Update Swap Account
	 */
	@Override
	@Transactional
	public void updateSwapAccount(SwapAccount swapAccount) {
		
		em.merge(swapAccount);
		em.flush();
	}

	/**
	 * Method to get all Swap Accounts
	 */
	@Override
	public List<SwapAccount> getAllSwapAccounts() {
		TypedQuery<SwapAccount> q = em.createQuery(	"SELECT o FROM SwapAccount o",
													 SwapAccount.class);
			
		return q.getResultList();		
	}

	@Override
	public SwapAccount findByEndUserId(Long id) {
		TypedQuery<SwapAccount> q = em.createQuery(	"SELECT o FROM SwapAccount o where o.endUserId =:id",
				 									SwapAccount.class);
		q.setParameter("id",id);
		return q.getSingleResult();
		
	}

	
}
