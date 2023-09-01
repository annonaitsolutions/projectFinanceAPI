package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Collateral;
import annona.domain.CustomerHead;

@Repository
public class CollateralDAOImpl implements CollateralDAO {

	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new CollateralDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<Collateral> getCollateralBYMAsterKey(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<Collateral> q = em.createQuery(
					"SELECT o FROM Collateral AS o WHERE o.masterKey = :masterKey",
					Collateral.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}
	
	
	
	
	@Transactional
	public void createUser(Collateral collateral) {	
		em.persist(collateral);				    
	}
}
