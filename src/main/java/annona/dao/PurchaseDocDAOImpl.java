package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.PurchaseDoc;

@Repository
public class PurchaseDocDAOImpl implements PurchaseDocDAO {
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public PurchaseDoc createDoc(PurchaseDoc purchaseDoc) {
		em.persist(purchaseDoc);
		return purchaseDoc;
	}

	public TypedQuery<PurchaseDoc> findPoKey(String poKey) {
		
		TypedQuery<PurchaseDoc> q = em
				.createQuery(
						"SELECT o FROM PurchaseDoc AS o WHERE  o.poKey=:poKey",PurchaseDoc.class);
		q.setParameter("poKey", poKey);
		return q;
	}
}
