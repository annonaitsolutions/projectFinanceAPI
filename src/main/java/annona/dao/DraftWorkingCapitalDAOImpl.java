package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.DraftWorkingCapital;

@Repository
public class DraftWorkingCapitalDAOImpl implements DraftWorkingCapitalDAO {

	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new DraftWorkingCapitalDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Override
	public TypedQuery<DraftWorkingCapital> getDWCBYMAsterKey(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException("The masterKey argument is required");

			TypedQuery<DraftWorkingCapital> q = em.createQuery(
					"SELECT o FROM DraftWorkingCapital AS o WHERE o.masterKey = :masterKey", DraftWorkingCapital.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}

	@Override
	@Transactional
	public DraftWorkingCapital createDraftMasterPlan(DraftWorkingCapital wc) {

		em.persist(wc);
		return wc;
	}

}
