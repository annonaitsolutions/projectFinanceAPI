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

import annona.domain.NewBuyer;
import annona.domain.Supplier;
import annona.form.NewBuyerForm;
import annona.form.SupplierForm;

@Repository
public class NewBuyerDAOImpl implements NewBuyerDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void createUser(NewBuyer newBuyer) {
		em.persist(newBuyer);
	}

	@SuppressWarnings("unchecked")
	public Collection<NewBuyer> getList() {

		Query query = em.createQuery("SELECT o FROM NewBuyer o");

		return (Collection<NewBuyer>) query.getResultList();
	}

	public NewBuyer findId(Long id) {

		return em.find(NewBuyer.class, id);

	}

	@Transactional
	public void update(NewBuyer newBuyer) {

		em.merge(newBuyer);

		em.flush();
	}

	public TypedQuery<NewBuyer> findByName(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<NewBuyer> q = em.createQuery(
				"SELECT o FROM NewBuyer AS o WHERE o.name = :name and status='Approved'",
				NewBuyer.class);
		q.setParameter("name", name);
		return q;
	}

	@Override
	public TypedQuery<NewBuyer> getByPending() {

		TypedQuery<NewBuyer> q = em
				.createQuery(
						"SELECT o FROM NewBuyer o where o.status = 'Pending' or o.status = 'Rejected'",
						NewBuyer.class);
		return q;

	}
	
	@Override
	public TypedQuery<NewBuyer> getByCustAppPending(String customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");
		
		TypedQuery<NewBuyer> q = em
				.createQuery(
						"SELECT o FROM NewBuyer o where o.name= :customerName and (o.status = 'Pending' or o.status = 'Approved')",
						NewBuyer.class);
		q.setParameter("customerName", customerName);
		return q;

	}

	public TypedQuery<NewBuyer> getByBuyer() {

		TypedQuery<NewBuyer> q = em.createQuery(
				"SELECT o FROM NewBuyer o where o.status = 'Pending'",
				NewBuyer.class);
		return q;

	}
	
	public TypedQuery<NewBuyer> findByCustomerHeadName(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<NewBuyer> q = em.createQuery(
				"SELECT o FROM NewBuyer AS o WHERE o.name = :name",
				NewBuyer.class);
		q.setParameter("name", name);
		return q;
	}
	
	public TypedQuery<NewBuyer> findByNameAndStatus(String name) {
		if (name == null || name.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<NewBuyer> q = em.createQuery(
				"SELECT o FROM NewBuyer AS o WHERE o.name = :name and o.status='Approved'",
				NewBuyer.class);
		q.setParameter("name", name);
		return q;
	}
	
	public List<NewBuyerForm> getBuyerForBusinessPlan(String masterKey) {

		Query query = em.createQuery(
				"SELECT T1.id,T1.buyerName,T1.companyName,T2.finalPro,T1.bank ,T1.contactNum,T1.email,T1.status,T1.rate FROM NewBuyer AS T1, SellerBuyingCost As T2  where T1.buyerName = T2.buyerName and T2.masterKey=:masterKey and T1.status='Approved'");

		query.setParameter("masterKey", masterKey);
		List<Object[]> customerList = query.getResultList();
		List<NewBuyerForm> autoBuyer = new ArrayList<NewBuyerForm>();
		if (customerList != null) {
			for (int i = 0; i < customerList.size(); i++) {
				NewBuyerForm buyerForm = new NewBuyerForm();
				buyerForm.setId(Long.parseLong(customerList.get(i)[0].toString()));
				buyerForm.setBuyerName(customerList.get(i)[1].toString());
				buyerForm.setCompanyName(customerList.get(i)[2].toString());
				buyerForm.setFinalPro(customerList.get(i)[3].toString());
				buyerForm.setBank(customerList.get(i)[4].toString());
				buyerForm.setContactNum(customerList.get(i)[5].toString());
				buyerForm.setEmail(customerList.get(i)[6].toString());
				buyerForm.setStatus(customerList.get(i)[7].toString());
				buyerForm.setRate(Float.parseFloat(customerList.get(i)[8].toString()));
				autoBuyer.add(buyerForm);
			}
			return autoBuyer;
		} else {
			return null;
		}

	}
	
	public TypedQuery<NewBuyer> findByCustomerHeadNameAndStatus(String customerHeadName) {
		if (customerHeadName == null || customerHeadName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<NewBuyer> q = em.createQuery(
				"SELECT o FROM NewBuyer AS o WHERE o.name = :customerHeadName and o.status ='Approved'",
				NewBuyer.class);
		q.setParameter("customerHeadName", customerHeadName);
		return q;
	}
	
	@Override
	public TypedQuery<NewBuyer> getByPending(String customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");
		
		TypedQuery<NewBuyer> q = em
				.createQuery(
						"SELECT o FROM NewBuyer o where o.bName= :customerName and (o.cStatus = 'Pending' or o.cStatus = 'Rejected')",
						NewBuyer.class);
		q.setParameter("customerName", customerName);
		return q;

	}
	
	public TypedQuery<NewBuyer> getForApproval() {

		TypedQuery<NewBuyer> q = em.createQuery(
				"SELECT o FROM NewBuyer o where o.cStatus = 'Pending' or o.cStatus = 'Rejected'",
				NewBuyer.class);
		return q;

	}
	
	public TypedQuery<NewBuyer> getForcApproval() {

		TypedQuery<NewBuyer> q = em.createQuery(
				"SELECT o FROM NewBuyer o where o.cStatus = 'Pending'",
				NewBuyer.class);
		return q;

	}
	
	@Override
	public TypedQuery<NewBuyer> getForcApprovalByCompanyId(Long companyId) {

		TypedQuery<NewBuyer> q = em.createQuery(
				"SELECT o FROM NewBuyer o where o.companyId = :companyId and o.cStatus = 'Pending'",
				NewBuyer.class);
		q.setParameter("companyId", companyId);
		return q;

	}
	
	public TypedQuery<NewBuyer> getForRating() {

		TypedQuery<NewBuyer> q = em.createQuery(
				"SELECT o FROM NewBuyer o where o.status = 'Approved'",
				NewBuyer.class);
		return q;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NewBuyer> getbuyerListByCompanyId(Long companyId) {
		Query q = em.createQuery("SELECT o FROM NewBuyer o WHERE o.status = 'Approved' and o.companyId = :companyId ", NewBuyer.class);
		q.setParameter("companyId", companyId);
		return (List<NewBuyer>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public NewBuyer findBuyerByNameAndCompId(String buyerName , Long companyId ) {
		TypedQuery<NewBuyer> q = em.createQuery(
								"SELECT o FROM NewBuyer o where o.buyerName =:buyerName and o.companyId  =:companyId " ,
								NewBuyer.class);
		q.setParameter("buyerName", buyerName);
		q.setParameter("companyId", companyId );
		try {
			return q.getSingleResult();

		} catch (NoResultException e) {

			return null;
		}

	}

	/**
	 * Method to find Buyer by buyerName
	 * @param buyerName
	 * @return
	 */
	@Override
	public NewBuyer findByBuyerName(String buyerName) {
		TypedQuery<NewBuyer> q = em.createQuery(
								"SELECT o FROM NewBuyer o where o.buyerName =:buyerName",
								NewBuyer.class);
		q.setParameter("buyerName", buyerName);
		
		return q.getSingleResult();

	}
}
