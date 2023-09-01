package annona.trade.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.NewBuyer;
import annona.domain.UploadedFile;
import annona.trade.domain.TUploadedFile;



@Repository
public class TUploadDAOImpl implements TUploadDAO {

	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new TUploadDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	
	
	@Transactional
	public void createUser(TUploadedFile uploadFile) {
		em.persist(uploadFile);
	}

	@SuppressWarnings("unchecked")
	public Collection<TUploadedFile> getList() {

		Query query = em.createQuery("SELECT o FROM TUploadedFile o");

		return (Collection<TUploadedFile>) query.getResultList();
	}

	public UploadedFile findId(Long id) {

		return em.find(UploadedFile.class, id);

	}

	public TypedQuery<TUploadedFile> findByName(String customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<TUploadedFile> q = em.createQuery(
				"SELECT o FROM TUploadedFile AS o WHERE o.customerName = :customerName",
				TUploadedFile.class);
		q.setParameter("customerName", customerName);
		return q;
	}
	


	@Transactional
	public void update(TUploadedFile uploadFile) {

		em.merge(uploadFile);

		em.flush();
	}



	public TypedQuery<TUploadedFile> getByPending() {

		TypedQuery<TUploadedFile> q = em.createQuery(
				"SELECT o FROM TUploadedFile o where o.status = 'Pending'",
				TUploadedFile.class);
		return q;

	}
}
