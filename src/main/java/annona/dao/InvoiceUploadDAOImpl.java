package annona.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.InvoiceUpload;
import annona.domain.MasterPlan;
import annona.domain.NewBuyer;
import annona.domain.PoUpload;
import annona.domain.PurchaseOrder;




@Repository
public class InvoiceUploadDAOImpl implements InvoiceUploadDAO {

	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new InvoiceUploadDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	
	
	@Transactional
	public void createUser(InvoiceUpload invoiceupload) {
		em.persist(invoiceupload);
	}

	@SuppressWarnings("unchecked")
	public Collection<InvoiceUpload> getList() {

		Query query = em.createQuery("SELECT o FROM InvoiceUpload o");

		return (Collection<InvoiceUpload>) query.getResultList();
	}

	public InvoiceUpload findId(Long id) {

		return em.find(InvoiceUpload.class, id);

	}

	public TypedQuery<InvoiceUpload> findByName(String customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<InvoiceUpload> q = em.createQuery(
				"SELECT o FROM InvoiceUpload AS o WHERE o.customerName = :customerName",
				InvoiceUpload.class);
		q.setParameter("customerName", customerName);
		return q;
	}
	


	@Transactional
	public void update(InvoiceUpload invoiceupload) {

		em.merge(invoiceupload);

		em.flush();
	}


	
	public TypedQuery<InvoiceUpload> getByPending() {

		TypedQuery<InvoiceUpload> q = em.createQuery(
				"SELECT o FROM InvoiceUpload o where o.status = 'Pending'",
				InvoiceUpload.class);
		return q;

	}
	
	
	
	public TypedQuery<InvoiceUpload> findPoKey(String poKey) {
		if (poKey == null || poKey.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<InvoiceUpload> q = em.createQuery(
				"SELECT o FROM InvoiceUpload AS o WHERE o.poKey = :poKey",
				InvoiceUpload.class);
		q.setParameter("poKey", poKey);
		return q;
	}
}
