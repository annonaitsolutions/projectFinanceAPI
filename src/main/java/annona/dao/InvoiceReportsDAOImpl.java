package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.DisputeReports;
import annona.domain.InvoiceReports;


@Repository
public class InvoiceReportsDAOImpl implements InvoiceReportsDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new InvoiceReportsDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<InvoiceReports> getList() {

		TypedQuery<InvoiceReports> q = em.createQuery(
				"SELECT o FROM InvoiceReports o ", InvoiceReports.class);
		return q;

	}
	
	
	@Transactional
	public void updateInvoiceReports(InvoiceReports invoice) {

		em.merge(invoice);
		em.flush();

	}
	
	@Override
	public  InvoiceReports getByInvoiceReportsId(Long id) {
		if (id == null)
			return null;
		return em.find(InvoiceReports.class, id);
	}
	
	
	
	
	@Transactional
	public void createInvoiceReports(InvoiceReports invoice) {	
		em.persist(invoice);				    
	}
	
	@Override
	public TypedQuery<InvoiceReports> getInvoiceReportsList(String invoicekey)  {
		 {
			if (invoicekey == null || invoicekey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<InvoiceReports> q = em.createQuery("SELECT o FROM InvoiceReports AS o WHERE o.invoicekey = :invoicekey",InvoiceReports.class);
			q.setParameter("invoicekey", invoicekey);
			return q;
		}
           }
	
	@Override
	public TypedQuery<InvoiceReports> getInvoiceReportsOnCustList(String buyerName)  {
		 {
			if (buyerName == null || buyerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<InvoiceReports> q = em.createQuery("SELECT o FROM InvoiceReports AS o WHERE o.buyerName = :buyerName",InvoiceReports.class);
			q.setParameter("buyerName", buyerName);
			return q;
		}
           }

	
	public TypedQuery<InvoiceReports> getInsByStatus() {
		TypedQuery<InvoiceReports> q = em.createQuery(
				"SELECT o FROM InvoiceReports o where o.status='Pending'",
				InvoiceReports.class);

		return q;
	}
	
	@Override
	public TypedQuery<InvoiceReports> getInvoiceReportsOnCustAndAcceptList(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<InvoiceReports> q = em.createQuery("SELECT o FROM InvoiceReports AS o WHERE o.customerName = :customerName and(o.custAccept = 'Pending' or o.custAccept = 'NotAccepted')",InvoiceReports.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<InvoiceReports> getInvoiceReportsVendorList(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<InvoiceReports> q = em.createQuery("SELECT o FROM InvoiceReports AS o WHERE o.customerName = :customerName and(o.custAccept = 'NotAccepted' or o.custAccept = 'Accepted')",InvoiceReports.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }

}
