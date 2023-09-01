package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.BuyingCost;
import annona.domain.CustomerHead;
import annona.domain.SellerBuyingCost;

@Repository
public class SellerBuyingCostDAOImpl implements SellerBuyingCostDAO {
	
	
	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new SellerBuyingCostDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<SellerBuyingCost> getSellerBuyingCostList(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<SellerBuyingCost> q = em.createQuery(
					"SELECT o FROM SellerBuyingCost AS o WHERE o.masterKey = :masterKey",
					SellerBuyingCost.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}
	

	@Transactional
	public SellerBuyingCost createSellerBuyingCost(SellerBuyingCost buyerCost) {

		em.persist(buyerCost);
		return buyerCost;
	}
	
	@Override
	public TypedQuery<SellerBuyingCost> getSellerBuyingCostbygoodsList(String masterKey,String goods ) {
		{
			if (masterKey == null || masterKey.length() == 0||goods == null || goods.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<SellerBuyingCost> q = em.createQuery(
					"SELECT o FROM SellerBuyingCost AS o WHERE o.masterKey = :masterKey and o.finalPro = :goods ",
					SellerBuyingCost.class);
			q.setParameter("masterKey", masterKey);
			q.setParameter("goods", goods);
			return q;
		}
	}
	
	
	@Override
	public SellerBuyingCost getSellerBuyingCostBuyerName(String buyerName , String masterKey) {
		// TODO Auto-generated method stub
		TypedQuery<SellerBuyingCost> q = em.createQuery("SELECT o FROM SellerBuyingCost o WHERE o.buyerName = :buyerName and o.masterKey=:masterKey ", SellerBuyingCost.class);
		q.setParameter("buyerName", buyerName);
		q.setParameter("masterKey", masterKey);
		return q.getSingleResult();
	}

}
