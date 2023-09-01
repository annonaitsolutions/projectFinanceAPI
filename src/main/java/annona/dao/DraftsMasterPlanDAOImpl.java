package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.CustomerHead;
import annona.domain.DraftsMasterPlan;
import annona.domain.MasterPlan;

@Repository
public class DraftsMasterPlanDAOImpl implements DraftsMasterPlanDAO {
	
	 
	
	
	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new EndUserDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Transactional
	public void updateMasterPlan(DraftsMasterPlan drafts) {

		em.merge(drafts);
		em.flush();

	}

	@Transactional
	public DraftsMasterPlan createDrafts(DraftsMasterPlan drafts) {

		em.persist(drafts);
		return drafts;
	}
	
	@Override
	public TypedQuery<DraftsMasterPlan> getByCustomerAndFlag(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<DraftsMasterPlan> q = em.createQuery("SELECT o FROM DraftsMasterPlan AS o WHERE o.customer = :customer and o.flag = 0 ",DraftsMasterPlan.class);
			q.setParameter("customer", customer);
			return q;
		}
           }
	
	@Override
	public  DraftsMasterPlan getByMasterPlanId(Long id) {
		if (id == null)
			return null;
		return em.find(DraftsMasterPlan.class, id);
	}
	
	  @Transactional
	 public void deleteRow(Long id) {
		Query query = em.createNativeQuery("DELETE FROM DraftsMasterPlan WHERE ID = " + id);
	    	query.executeUpdate();
		        }
		    
		    } 


	
	 
	

