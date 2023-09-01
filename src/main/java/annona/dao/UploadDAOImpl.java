package annona.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.NewBuyer;
import annona.domain.UploadedFile;



@Repository
public class UploadDAOImpl implements UploadDAO {

	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new UploadDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	
	
	@Transactional
	public void createUser(UploadedFile uploadFile) {
		em.persist(uploadFile);
	}

	@SuppressWarnings("unchecked")
	public Collection<UploadedFile> getList() {

		Query query = em.createQuery("SELECT o FROM UploadedFile o");

		return (Collection<UploadedFile>) query.getResultList();
	}

	public UploadedFile findId(Long id) {

		return em.find(UploadedFile.class, id);

	}

	public TypedQuery<UploadedFile> findByName(String customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<UploadedFile> q = em.createQuery(
				"SELECT o FROM UploadedFile AS o WHERE o.customerName = :customerName",
				UploadedFile.class);
		q.setParameter("customerName", customerName);
		return q;
	}
	
	public TypedQuery<UploadedFile> findPokey(String poKey) {
		
		TypedQuery<UploadedFile> q = em.createQuery(
				"SELECT o FROM UploadedFile AS o WHERE o.uploadComment = :poKey",
				UploadedFile.class);
		q.setParameter("poKey", poKey);
		return q;
	}
	


	@Transactional
	public void update(UploadedFile uploadFile) {

		em.merge(uploadFile);

		em.flush();
	}



	public TypedQuery<UploadedFile> getByPending() {

		TypedQuery<UploadedFile> q = em.createQuery(
				"SELECT o FROM UploadedFile o where o.status = 'Pending'",
				UploadedFile.class);
		return q;

	}
}
