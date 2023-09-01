package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.DraftBuyingCost;

@Repository
public class DraftBuyingCostDAOImpl implements DraftBuyingCostDAO {

	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new DraftBuyingCostDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Override
	public TypedQuery<DraftBuyingCost> getDraftBuyingCostBYMAsterKey(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException("The masterKey argument is required");

			TypedQuery<DraftBuyingCost> q = em.createQuery(
					"SELECT o FROM DraftBuyingCost AS o WHERE o.masterKey = :masterKey", DraftBuyingCost.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}

	@Override
	@Transactional
	public DraftBuyingCost createDraftMasterPlan(DraftBuyingCost draftBuyerCost) {

		em.persist(draftBuyerCost);
		return draftBuyerCost;
	}

}
