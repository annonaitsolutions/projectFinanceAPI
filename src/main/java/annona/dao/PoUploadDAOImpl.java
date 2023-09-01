package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.LimitBurst;
import annona.domain.MasterPlan;
import annona.domain.NewBuyer;
import annona.domain.PoUpload;
import annona.domain.PurchaseOrder;




@Repository
public class PoUploadDAOImpl implements PoUploadDAO {

	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new PoUploadDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	
	
	@Transactional
	public void createUser(PoUpload poupload) {
		em.persist(poupload);
	}

	@SuppressWarnings("unchecked")
	public Collection<PoUpload> getList() {

		Query query = em.createQuery("SELECT o FROM PoUpload o");

		return (Collection<PoUpload>) query.getResultList();
	}

	public PoUpload findId(Long id) {

		return em.find(PoUpload.class, id);

	}

	public TypedQuery<PoUpload> findByName(String customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<PoUpload> q = em.createQuery(
				"SELECT o FROM PoUpload AS o WHERE o.customerName = :customerName",
				PoUpload.class);
		q.setParameter("customerName", customerName);
		return q;
	}
	


	@Transactional
	public void update(PoUpload poupload) {

		em.merge(poupload);

		em.flush();
	}


	
	public TypedQuery<PoUpload> getByPending() {

		TypedQuery<PoUpload> q = em.createQuery(
				"SELECT o FROM PoUpload o where o.status = 'Pending'",
				PoUpload.class);
		return q;

	}


	/**
	 * Method to get PoUpload based on poKey
	 * @param poKey
	 * @return
	 */
	@Override
	public List<PoUpload> getPoUploadByPOKey(String poKey) {
		TypedQuery<PoUpload> q = em.createQuery(
									"SELECT o FROM PoUpload As o where o.poKey =:poKey",
									PoUpload.class);
		q.setParameter("poKey", poKey);
		return q.getResultList();
	}
	
}
