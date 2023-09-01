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
import annona.trade.domain.TUploadedFile;
import annona.domain.VendorUploadedFile;



@Repository
public class VendorUploadDAOImpl implements VendorUploadDAO {

	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new VendorUploadDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	
	
	@Transactional
	public void createUser(VendorUploadedFile vendorUploadedFile) {
		em.persist(vendorUploadedFile);
	}

	@SuppressWarnings("unchecked")
	public Collection<VendorUploadedFile> getList() {

		Query query = em.createQuery("SELECT o FROM VendorUploadedFile o");

		return (Collection<VendorUploadedFile>) query.getResultList();
	}

	public VendorUploadedFile findId(Long id) {

		return em.find(VendorUploadedFile.class, id);

	}

	public TypedQuery<VendorUploadedFile> findByName(String customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<VendorUploadedFile> q = em.createQuery(
				"SELECT o FROM VendorUploadedFile AS o WHERE o.customerName = :customerName",
				VendorUploadedFile.class);
		q.setParameter("customerName", customerName);
		return q;
	}
	


	@Transactional
	public void update(VendorUploadedFile vendorUploadedFile) {

		em.merge(vendorUploadedFile);

		em.flush();
	}



	public TypedQuery<VendorUploadedFile> getByPending() {

		TypedQuery<VendorUploadedFile> q = em.createQuery(
				"SELECT o FROM VendorUploadedFile o where o.status = 'Pending'",
				VendorUploadedFile.class);
		return q;

	}
	
	
	public TypedQuery<VendorUploadedFile> findPokey(String poKey) {
		if (poKey == null || poKey.length() == 0)
			throw new IllegalArgumentException(
					"The poKey argument is required");

		TypedQuery<VendorUploadedFile> q = em.createQuery(
				"SELECT o FROM VendorUploadedFile AS o WHERE o.poKey = :poKey",
				VendorUploadedFile.class);
		q.setParameter("poKey", poKey);
		return q;
	}
	
	


}
