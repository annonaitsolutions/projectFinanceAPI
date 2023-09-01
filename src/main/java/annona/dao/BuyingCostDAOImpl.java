package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.BuyingCost;

import java.util.List;

@Repository
public class BuyingCostDAOImpl implements BuyingCostDAO {

	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new EndUserDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Override
	public TypedQuery<BuyingCost> getBuyingCostBYMAsterKey(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<BuyingCost> q = em.createQuery(
					"SELECT o FROM BuyingCost AS o WHERE o.masterKey = :masterKey",
					BuyingCost.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}


	@Override
	public BuyingCost getBuyingCostByCustomerName(String supplierName , String masterKey) {
		// TODO Auto-generated method stub
		TypedQuery<BuyingCost> q = em.createQuery("SELECT o FROM BuyingCost o WHERE o.supplierName = :supplierName and o.masterKey = :masterKey", BuyingCost.class);
		q.setParameter("supplierName", supplierName);
		q.setParameter("masterKey", masterKey);
		return q.getSingleResult();
	}

	@Transactional
	public BuyingCost createMasterPlan(BuyingCost buyerCost) {

		em.persist(buyerCost);
		return buyerCost;
	}

	@Override
	public List<String> getMaterials(String supplierName) {
		// TODO Auto-generated method stub
		TypedQuery<String> q = em.createQuery("SELECT DISTINCT  material FROM BuyingCost o WHERE o.supplierName = :supplierName", String.class);
		q.setParameter("supplierName", supplierName);
		try {
			return q.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public TypedQuery<BuyingCost> getById(Long id) {
		{
			if (id == null || id == 0)
				throw new IllegalArgumentException(
						"The id is required");

			TypedQuery<BuyingCost> q = em.createQuery(
					"SELECT o FROM BuyingCost AS o WHERE o.id = :id",
					BuyingCost.class);
			q.setParameter("id", id);
			return q;
		}
	}

}
