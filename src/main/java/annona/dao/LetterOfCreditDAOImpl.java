package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.LetterOfCredit;
import annona.domain.PurchaseOrder;

@Repository
public class LetterOfCreditDAOImpl implements LetterOfCreditDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new LetterOfCreditDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<LetterOfCredit> getList() {

		TypedQuery<LetterOfCredit> q = em.createQuery(
				"SELECT o FROM LetterOfCredit o ", LetterOfCredit.class);
		return q;

	}
	
	
	@Transactional
	public void updateLetterOfCredit(LetterOfCredit credit) {

		em.merge(credit);
		em.flush();

	}
	
	@Override
	public  LetterOfCredit getByLetterOfCreditId(Long id) {
		if (id == null)
			return null;
		return em.find(LetterOfCredit.class, id);
	}
	
	
	
	
	@Transactional
	public void createLetterOfCredit(LetterOfCredit credit) {	
		em.persist(credit);				    
	}
	
	@Override
	public TypedQuery<LetterOfCredit> getLCListByPoKey(String poKey)  {
		 {
			if (poKey == null || poKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<LetterOfCredit> q = em.createQuery("SELECT o FROM LetterOfCredit AS o WHERE o.poKey = :poKey",LetterOfCredit.class);
			q.setParameter("poKey", poKey);
			return q;
		}
           }
	
	

}
