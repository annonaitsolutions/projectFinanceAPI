package annona.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.RestrictedLicense;


@Repository
public class RestrictedLicenseDAOImpl implements RestrictedLicenseDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new RestrictedLicenseDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	
	
	@Transactional
	public void updateRestrictedLicense(RestrictedLicense license) {

		em.merge(license);
		em.flush();

	}
	
	@Override
	public  RestrictedLicense getByRestrictedLicenseId(Long id) {
		if (id == null)
			return null;
		return em.find(RestrictedLicense.class, id);
	}
	
	@Override
	public TypedQuery<RestrictedLicense> getList() {

		TypedQuery<RestrictedLicense> q = em.createQuery(
				"SELECT o FROM RestrictedLicense o ", RestrictedLicense.class);
		return q;

	}
	
	
	@Transactional
	public void createRestrictedLicense(RestrictedLicense license) {	
		em.persist(license);				    
	}
	

	
	@Override
	public TypedQuery<RestrictedLicense> getRestrictedLicenseOnCustList(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<RestrictedLicense> q = em.createQuery("SELECT o FROM RestrictedLicense AS o WHERE o.customer = :customer",RestrictedLicense.class);
			q.setParameter("customer", customer);
			return q;
		}
           }

	
	public TypedQuery<RestrictedLicense> getInsByStatus() {
		TypedQuery<RestrictedLicense> q = em.createQuery(
				"SELECT o FROM RestrictedLicense o where o.status='Pending'",
				RestrictedLicense.class);

		return q;
	}

	@Override
	public TypedQuery<RestrictedLicense> getRestrictedGoodsList(String customer,String category,Date purchaseDate,Float quantity)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<RestrictedLicense> q = em.createQuery("SELECT o FROM RestrictedLicense AS o WHERE o.customer = :customer and o.goodsName =:category and"
					+ " status='Approved' and o.endDate > :purchaseDate and o.totalQty >= :quantity ",RestrictedLicense.class);
			q.setParameter("customer", customer);
			q.setParameter("category", category);
			q.setParameter("purchaseDate", purchaseDate);
			q.setParameter("quantity", quantity);
			return q;
		}
           }
	


}
