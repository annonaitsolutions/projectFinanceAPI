package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.CustomerHead;
import annona.domain.DisputeReports;


@Repository
public class DisputeReportsDAOImpl implements DisputeReportsDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new DisputeReportsDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<DisputeReports> getList() {

		TypedQuery<DisputeReports> q = em.createQuery(
				"SELECT o FROM DisputeReports o ", DisputeReports.class);
		return q;

	}
	
	
	@Transactional
	public void updateDisputeReports(DisputeReports dispute) {

		em.merge(dispute);
		em.flush();

	}
	
	@Override
	public  DisputeReports getByDisputeReportsId(Long id) {
		if (id == null)
			return null;
		return em.find(DisputeReports.class, id);
	}
	
	
	
	
	@Transactional
	public void createDisputeReports(DisputeReports dispute) {	
		em.persist(dispute);				    
	}
	
	@Override
	public TypedQuery<DisputeReports> getDisputeReportsList(String disputekey)  {
		 {
			if (disputekey == null || disputekey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<DisputeReports> q = em.createQuery("SELECT o FROM DisputeReports AS o WHERE o.disputekey = :disputekey",DisputeReports.class);
			q.setParameter("disputekey", disputekey);
			return q;
		}
           }
	
	@Override
	public TypedQuery<DisputeReports> getDisputeReportsOnCustList(String customerName)  {
		 {
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<DisputeReports> q = em.createQuery("SELECT o FROM DisputeReports AS o WHERE o.customerName = :customerName",DisputeReports.class);
			q.setParameter("customerName", customerName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<DisputeReports> getDisputeReportsOnCustAndAcceptList(String supplierName)  {
		 {
			if (supplierName == null || supplierName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<DisputeReports> q = em.createQuery("SELECT o FROM DisputeReports AS o WHERE o.supplierName = :supplierName and o.status = 'Approved'  and(o.accept = 'Pending' or o.accept = 'NotAccepted')",DisputeReports.class);
			q.setParameter("supplierName", supplierName);
			return q;
		}
           }
	
	@Override
	public TypedQuery<DisputeReports> getDisputeReportsVendorList(String supplierName)  {
		 {
			if (supplierName == null || supplierName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<DisputeReports> q = em.createQuery("SELECT o FROM DisputeReports AS o WHERE o.supplierName = :supplierName and(o.accept = 'NotAccepted' or o.accept = 'Accepted')",DisputeReports.class);
			q.setParameter("supplierName", supplierName);
			return q;
		}
           }
	
	public TypedQuery<DisputeReports> getInsByStatus() {
		TypedQuery<DisputeReports> q = em.createQuery(
				"SELECT o FROM DisputeReports o where o.status='Pending'",
				DisputeReports.class);

		return q;
	}

}
