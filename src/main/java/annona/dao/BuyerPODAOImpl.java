package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.BuyerPO;


@Repository
public class BuyerPODAOImpl implements BuyerPODAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new BuyerPODAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	

	@Transactional
	public void createUser(BuyerPO buyerPO) {	
		em.persist(buyerPO);				    
	}

	@Override
	@Transactional
	public void updatePo(BuyerPO buyerPO) {
		em.merge(buyerPO);
		em.flush();
	}


	
	@Override
	public TypedQuery<BuyerPO> getBuyerPOByName(String customerName) {
		{
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<BuyerPO> q = em.createQuery(
					"SELECT o FROM BuyerPO AS o WHERE o.name = :customerName",
					BuyerPO.class);
			q.setParameter("customerName", customerName);
			return q;
		}
	}
	
	@Override
	public TypedQuery<BuyerPO> getBuyerPOByHeadName(String customerHeadName) {
		{
			if (customerHeadName == null || customerHeadName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<BuyerPO> q = em.createQuery(
					"SELECT o FROM BuyerPO AS o WHERE o.customerHeadName = :customerHeadName",
					BuyerPO.class);
			q.setParameter("customerHeadName", customerHeadName);
			return q;
		}
	}

	@Override
	public TypedQuery<BuyerPO> getBuyerPOByHeadNameAndRemainingInvoices(String customerHeadName) {
		{
			if (customerHeadName == null || customerHeadName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<BuyerPO> q = em.createQuery(
					"SELECT o FROM BuyerPO AS o WHERE o.customerHeadName = :customerHeadName AND (o.invoiceRemaining!='0' OR o.invoiceRemaining!='0.0')",
					BuyerPO.class);
			q.setParameter("customerHeadName", customerHeadName);
			return q;
		}
	}
	
	
	public TypedQuery<BuyerPO> getPoKey(String poKey) {
		{
			

			TypedQuery<BuyerPO> q = em.createQuery(
					"SELECT o FROM BuyerPO AS o WHERE o.poKey = :poKey",
					BuyerPO.class);
			q.setParameter("poKey", poKey);
			return q;
		}
	}

	@Override
	public TypedQuery<BuyerPO> getById(Long id) {
		{
			TypedQuery<BuyerPO> q = em.createQuery(
					"SELECT o FROM BuyerPO AS o WHERE o.id = :id",
					BuyerPO.class);
			q.setParameter("id", id);
			return q;
		}
	}

}
