package annona.trade.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Collateral;
import annona.domain.CustomerHead;
import annona.trade.domain.TCollateral;

@Repository
public class TCollateralDAOImpl implements TCollateralDAO {

	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new TCollateralDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<TCollateral> getCollateralBYMAsterKey(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<TCollateral> q = em.createQuery(
					"SELECT o FROM TCollateral AS o WHERE o.masterKey = :masterKey",
					TCollateral.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}
	
	
	
	
	@Transactional
	public void createUser(TCollateral collateral) {	
		em.persist(collateral);				    
	}
}
