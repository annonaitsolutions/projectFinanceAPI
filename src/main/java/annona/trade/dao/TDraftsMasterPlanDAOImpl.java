package annona.trade.dao;

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
import annona.trade.domain.TDraftsMasterPlan;

@Repository
public class TDraftsMasterPlanDAOImpl implements TDraftsMasterPlanDAO {
	
	 
	
	
	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new TDraftsMasterPlanDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Transactional
	public void updateMasterPlan(TDraftsMasterPlan drafts) {

		em.merge(drafts);
		em.flush();

	}

	@Transactional
	public TDraftsMasterPlan createDrafts(TDraftsMasterPlan drafts) {

		em.persist(drafts);
		return drafts;
	}
	
	@Override
	public TypedQuery<TDraftsMasterPlan> getByCustomerAndFlag(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<TDraftsMasterPlan> q = em.createQuery("SELECT o FROM TDraftsMasterPlan AS o WHERE o.customer = :customer and o.flag = 0 ",TDraftsMasterPlan.class);
			q.setParameter("customer", customer);
			return q;
		}
           }
	
	@Override
	public  TDraftsMasterPlan getByMasterPlanId(Long id) {
		if (id == null)
			return null;
		return em.find(TDraftsMasterPlan.class, id);
	}
	
	  @Transactional
	 public void deleteRow(Long id) {
		Query query = em.createNativeQuery("DELETE FROM TDraftsMasterPlan WHERE ID = " + id);
	    	query.executeUpdate();
		        }
		    
		    } 


	
	 
	

