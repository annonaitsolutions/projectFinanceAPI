package annona.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Company;
import annona.domain.Supplier;
import annona.form.SupplierForm;

@Repository
public class SupplierDAOImpl implements SupplierDAO {

	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new SupplierDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void createUser(Supplier supplier) {
		em.persist(supplier);
	}

	@SuppressWarnings("unchecked")
	public Collection<Supplier> getList() {

		Query query = em.createQuery("SELECT o FROM Supplier o");

		return (Collection<Supplier>) query.getResultList();
	}

	public Supplier findId(Long id) {

		return em.find(Supplier.class, id);

	}

	public TypedQuery<Supplier> findByName(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<Supplier> q = em.createQuery(
				"SELECT o FROM Supplier AS o WHERE o.name = :name",
				Supplier.class);
		q.setParameter("name", name);
		return q;
	}

	@Transactional
	public void update(Supplier supplier) {

		em.merge(supplier);

		em.flush();
	}

	@Override
	public TypedQuery<Supplier> getByPending() {

		TypedQuery<Supplier> q = em
				.createQuery(
						"SELECT o FROM Supplier o where o.status = 'Pending' or o.status = 'Rejected'",
						Supplier.class);
		return q;

	}
	
	@Override
	public TypedQuery<Supplier> getByCustPending(String customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");
		
		TypedQuery<Supplier> q = em
				.createQuery(
						"SELECT o FROM Supplier o where o.name= :customerName and (o.status = 'Pending' or o.status = 'Approved')",
						Supplier.class);
		q.setParameter("customerName", customerName);
		return q;

	}

	public TypedQuery<Supplier> getBySupplier() {

		TypedQuery<Supplier> q = em.createQuery(
				"SELECT o FROM Supplier o where o.status = 'Pending'",
				Supplier.class);
		return q;

	}
	
	public TypedQuery<Supplier> findByCustomerHeadName(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<Supplier> q = em.createQuery(
				"SELECT o FROM Supplier AS o WHERE o.name = :name",
				Supplier.class);
		q.setParameter("name", name);
		return q;
	}
	
	public TypedQuery<Supplier> findByNameAndStatus(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<Supplier> q = em.createQuery(
				"SELECT o FROM Supplier AS o WHERE o.name = :name and o.status='Approved'",
				Supplier.class);
		q.setParameter("name", name);
		return q;
	}
	

public List<SupplierForm> getSupplierForBusinessPlan( String masterKey) {

Query query = em.createQuery("SELECT T2.id,T1.supplierName,T1.companyName,T2.material,T1.bank ,T1.contactNum,T1.email,T1.status,T1.rate,T1.id FROM Supplier AS T1, BuyingCost As T2  where T1.supplierName= T2.supplierName and T2.masterKey=:masterKey and T1.status='Approved'");

           query.setParameter("masterKey", masterKey);
		List<Object[]> customerList = query.getResultList();
		List<SupplierForm> autoSupplier = new ArrayList<SupplierForm>();
            if (customerList != null) {
			for (int i = 0; i < customerList.size(); i++) {
				SupplierForm supplierForm = new SupplierForm();
				supplierForm.setId(Long.parseLong(customerList.get(i)[0].toString()));
				supplierForm.setSupplierName(customerList.get(i)[1].toString());
				supplierForm.setCompanyName(customerList.get(i)[2].toString());
				supplierForm.setMaterial(customerList.get(i)[3].toString());
				supplierForm.setBank(customerList.get(i)[4].toString());
				supplierForm.setContactNum(customerList.get(i)[5].toString());
				supplierForm.setEmail(customerList.get(i)[6].toString());
				supplierForm.setStatus(customerList.get(i)[7].toString());
				supplierForm.setRate(Float.parseFloat(customerList.get(i)[8].toString()));
				supplierForm.setSupplierBuyerId(Long.parseLong(customerList.get(i)[9].toString()));
				autoSupplier.add(supplierForm);
			}
			return autoSupplier;
		} else {
			return null;
		}
		
		}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> getSupplierListByCompanyId(Long companyId) {
		Query q = em.createQuery("SELECT o FROM Supplier o WHERE o.status = 'Approved' and o.companyId = :companyId ", Supplier.class);
		q.setParameter("companyId", companyId);
		return (List<Supplier>) q.getResultList();
	}
	
	public TypedQuery<Supplier> findByCustomerHeadNameAndStatus(String customerHeadName) {
		if (customerHeadName == null || customerHeadName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<Supplier> q = em.createQuery(
				"SELECT o FROM Supplier AS o WHERE o.name = :customerHeadName and o.status ='Approved'",
				Supplier.class);
		q.setParameter("customerHeadName", customerHeadName);
		return q;
	}
	
	public TypedQuery<Supplier> getForApproval() {

		TypedQuery<Supplier> q = em.createQuery(
				"SELECT o FROM Supplier o where o.cStatus = 'Pending' or o.cStatus = 'Rejected'",
				Supplier.class);
		return q;

	}
	
	public TypedQuery<Supplier> getForcApproval() {

		TypedQuery<Supplier> q = em.createQuery(
				"SELECT o FROM Supplier o where o.cStatus = 'Pending'",
				Supplier.class);
		return q;

	}
	
	@Override
	public TypedQuery<Supplier> getForcApprovalByCompany(Long companyId) {

		TypedQuery<Supplier> q = em.createQuery(
				"SELECT o FROM Supplier o where o.companyId =:companyId and o.cStatus = 'Pending'",
				Supplier.class);
		q.setParameter("companyId", companyId);
		return q;

	}
	
	@Override
	public TypedQuery<Supplier> getBySupplierPending(String customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");
		
		TypedQuery<Supplier> q = em
				.createQuery(
						"SELECT o FROM Supplier o where o.bName= :customerName and (o.cStatus = 'Pending' or o.cStatus = 'Rejected')",
						Supplier.class);
		q.setParameter("customerName", customerName);
		return q;

	}
	

	public TypedQuery<Supplier> getForRating() {

		TypedQuery<Supplier> q = em.createQuery(
				"SELECT o FROM Supplier o where o.status = 'Approved'",
				Supplier.class);
		return q;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Supplier findSupplierByNameAndCompId(String supplierName , Long companyId ) {
		TypedQuery<Supplier> q = em.createQuery(
								"SELECT o FROM Supplier o where o.supplierName =:supplierName and o.companyId  =:companyId " ,
								Supplier.class);
		q.setParameter("supplierName", supplierName);
		q.setParameter("companyId", companyId );
		try {
			return q.getSingleResult();

		} catch (NoResultException e) {

			return null;
		}

	}

	/**
	 * Method to find Supplier by SupplierName	
	 * @param supplierName
	 * @return
	 */
	@Override
	public Supplier findBySupplierName(String supplierName) {
		TypedQuery<Supplier> q = em.createQuery(
									"SELECT o FROM Supplier o where o.supplierName =:supplierName",
									Supplier.class);
		q.setParameter("supplierName", supplierName);
		
		return q.getSingleResult();
		
	}
}
