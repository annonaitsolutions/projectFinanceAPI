package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.ConsortiumBank;


@Repository
public class ConsortiumDAOImpl implements ConsortiumDAO{
	
	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new ConsortiumDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public ConsortiumBank createConsortium(ConsortiumBank bank) {

		em.persist(bank);
		return bank;
	}
	
	@Override
	public  ConsortiumBank getByConsortiumId(Long id) {
		if (id == null)
			return null;
		return em.find(ConsortiumBank.class, id);
	}
	
	@Transactional
	public void updateConsortium(ConsortiumBank bank) {

		em.merge(bank);
		em.flush();

	}
	
	
	@Override
	public TypedQuery<ConsortiumBank> getConsortiumByMasterKey(String masterKey)  {
		 {
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<ConsortiumBank> q = em.createQuery("SELECT o FROM ConsortiumBank AS o WHERE o.masterKey = :masterKey ",ConsortiumBank.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
           }
	

}
