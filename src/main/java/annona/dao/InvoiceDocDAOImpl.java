package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.InvoiceDoc;
@Repository
public class InvoiceDocDAOImpl implements InvoiceDocDAO {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public InvoiceDoc createDoc(InvoiceDoc invoiceDoc) {
		em.persist(invoiceDoc);
		return invoiceDoc;
	}

	@Override
	public TypedQuery<InvoiceDoc> findPoKey(String invoiceKey) {
		TypedQuery<InvoiceDoc> q = em
				.createQuery(
						"SELECT o FROM InvoiceDoc AS o WHERE  o.invoiceKey=:invoiceKey",InvoiceDoc.class);
		q.setParameter("invoiceKey", invoiceKey);
		return q;
	}

}
