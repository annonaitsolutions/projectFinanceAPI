package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Correspondent;
import annona.domain.LetterOfCredit;

@Repository
public class CorrespondentDAOImpl implements CorrespondentDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new CorrespondentDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<Correspondent> getList() {

		TypedQuery<Correspondent> q = em.createQuery(
				"SELECT o FROM Correspondent o ", Correspondent.class);
		return q;

	}
	
	
	@Transactional
	public void updateCorrespondent(Correspondent correspondent) {

		em.merge(correspondent);
		em.flush();

	}
	
	@Override
	public  Correspondent getByCorrespondentId(Long id) {
		if (id == null)
			return null;
		return em.find(Correspondent.class, id);
	}
	
	
	
	
	@Transactional
	public void createCorrespondent(Correspondent correspondent) {	
		em.persist(correspondent);				    
	}
	
	@Override
	public TypedQuery<Correspondent> getCorrespondenttByPoKey(String poKey)  {
		 {
			if (poKey == null || poKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Correspondent> q = em.createQuery("SELECT o FROM Correspondent AS o WHERE o.poKey = :poKey",Correspondent.class);
			q.setParameter("poKey", poKey);
			return q;
		}
           }

}
