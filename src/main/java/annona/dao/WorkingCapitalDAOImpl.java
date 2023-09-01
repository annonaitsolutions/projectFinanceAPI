package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.BuyingCost;
import annona.domain.WorkingCapital;

@Repository
public class WorkingCapitalDAOImpl implements WorkingCapitalDAO{
	
	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new WorkingCapitalDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<WorkingCapital> getWCBYMAsterKey(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<WorkingCapital> q = em.createQuery(
					"SELECT o FROM WorkingCapital AS o WHERE o.masterKey = :masterKey",
					WorkingCapital.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}

	@Transactional
	public WorkingCapital createMasterPlan(WorkingCapital wc) {

		em.persist(wc);
		return wc;
	}

}
