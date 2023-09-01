package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.DraftSellerBuyingCost;

@Repository
public class DraftSellerBuyingCostDAOImpl implements DraftSellerBuyingCostDAO {

	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new DraftSellerBuyingCostDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Override
	public TypedQuery<DraftSellerBuyingCost> getDraftSellerBuyingCostList(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException("The username argument is required");

			TypedQuery<DraftSellerBuyingCost> q = em.createQuery(
					"SELECT o FROM DraftSellerBuyingCost AS o WHERE o.masterKey = :masterKey",
					DraftSellerBuyingCost.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}

	@Transactional
	public DraftSellerBuyingCost createDraftSellerBuyingCost(DraftSellerBuyingCost buyerCost) {

		em.persist(buyerCost);
		return buyerCost;
	}

	@Override
	public TypedQuery<DraftSellerBuyingCost> getDraftSellerBuyingCostbygoodsList(String masterKey, String goods) {
		{
			if (masterKey == null || masterKey.length() == 0 || goods == null || goods.length() == 0)
				throw new IllegalArgumentException("The username argument is required");

			TypedQuery<DraftSellerBuyingCost> q = em.createQuery(
					"SELECT o FROM DraftSellerBuyingCost AS o WHERE o.masterKey = :masterKey and o.finalPro = :goods ",
					DraftSellerBuyingCost.class);
			q.setParameter("masterKey", masterKey);
			q.setParameter("goods", goods);
			return q;
		}
	}

	@Override
	public TypedQuery<DraftSellerBuyingCost> getDraftSellerBuyingCostByMasterKey(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException("The masterKey argument is required");

			TypedQuery<DraftSellerBuyingCost> q = em.createQuery(
					"SELECT o FROM DraftSellerBuyingCost AS o WHERE o.masterKey = :masterKey",
					DraftSellerBuyingCost.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}

}
