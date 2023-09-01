package annona.trade.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.BuyingCost;
import annona.domain.Collateral;
import annona.trade.domain.TBuyingCost;

@Repository
public class TBuyingCostDAOImpl implements TBuyingCostDAO {

	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new TBuyingCostDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<TBuyingCost> getBuyingCostBYMAsterKey(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<TBuyingCost> q = em.createQuery(
					"SELECT o FROM TBuyingCost AS o WHERE o.masterKey = :masterKey",
					TBuyingCost.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}

	@Transactional
	public TBuyingCost createMasterPlan(TBuyingCost buyerCost) {

		em.persist(buyerCost);
		return buyerCost;
	}

}
