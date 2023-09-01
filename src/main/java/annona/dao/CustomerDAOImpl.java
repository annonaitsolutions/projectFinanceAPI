package annona.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.ClientAppMng;
import annona.domain.CustomerBranch;
import annona.domain.CustomerHead;
import annona.domain.CustomerSubsidiary;
import annona.form.ClientAppMngForm;
import annona.form.CustomerHeadForm;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new CustomerDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Override
	public TypedQuery<CustomerHead> getList() {

		TypedQuery<CustomerHead> q = em.createQuery(
				"SELECT o FROM CustomerHead o ", CustomerHead.class);
		return q;

	}

	public List<CustomerHeadForm> getCustByIdAndStatus() {
		
		Query query = em
				.createQuery("SELECT T1.id, T1.name, T1.customerPrefix, T2.companyName, T2.address, T1.status FROM CustomerHead T1, Company T2 where T1.companyId= T2.id and T1.status='Pending'" );

		List<Object[]> customerList = query.getResultList();
		List<CustomerHeadForm> autoCustomerHeadForm = new ArrayList<CustomerHeadForm>();
		
		if (customerList != null) {
			for (int i = 0; i < customerList.size(); i++) {
				CustomerHeadForm customerHeadForm = new CustomerHeadForm();
				customerHeadForm.setId(Long.parseLong(customerList.get(i)[0].toString()));
				customerHeadForm.setName(customerList.get(i)[1].toString());
				customerHeadForm.setCustomerPrefix(customerList.get(i)[2].toString());
				customerHeadForm.setCompanyName((customerList.get(i)[3].toString()));
				customerHeadForm.setAddress(customerList.get(i)[4].toString());
				customerHeadForm.setStatus(customerList.get(i)[5].toString());
				autoCustomerHeadForm.add(customerHeadForm);
			}
			return autoCustomerHeadForm;
		} else {
			return null;
		}
	}
	
	public TypedQuery<CustomerHead> getCustByIdAndaStatus() {
		TypedQuery<CustomerHead> q = em.createQuery(
				"SELECT o FROM CustomerHead o where o.aStatus='Pending'",
				CustomerHead.class);

		return q;
	}
	
	

public List<CustomerHeadForm> getCustByIdAndAStatusComp(Long companyId) {
	
	Query query = em
			.createQuery("SELECT T1.id, T1.name, T1.transactionId, T1.customerPrefix, T2.companyName, T2.address, T1.aStatus FROM CustomerHead T1, Company T2 where T1.companyId= T2.id and T1.aStatus='Pending' and T2.id=" + companyId);

	List<Object[]> customerList = query.getResultList();
	List<CustomerHeadForm> autoCustomer = new ArrayList<CustomerHeadForm>();
	
	if (customerList != null) {
		for (int i = 0; i < customerList.size(); i++) {
			CustomerHeadForm customerHeadForm = new CustomerHeadForm();
			customerHeadForm.setId(Long.parseLong(customerList.get(i)[0].toString()));
			customerHeadForm.setName(customerList.get(i)[1].toString());
			customerHeadForm.setTransactionId(customerList.get(i)[2].toString());
			customerHeadForm.setCustomerPrefix(customerList.get(i)[3].toString());
			customerHeadForm.setCompanyName((customerList.get(i)[4].toString()));
			customerHeadForm.setAddress(customerList.get(i)[5].toString());
			customerHeadForm.setStatus(customerList.get(i)[6].toString());
			autoCustomer.add(customerHeadForm);
		}
		return autoCustomer;
	} else {
		return null;
	}
	
	}

	public CustomerHead getRowById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerHead getByCustomerId(Long id) {
		if (id == null)
			return null;
		return em.find(CustomerHead.class, id);
	}

	@Transactional
	public void updateUser(CustomerHead customer) {

		em.merge(customer);
		em.flush();

	}

	@Transactional
	public void insertCustomer(CustomerHead customerHead) {

		em.persist(customerHead);

	}

	@SuppressWarnings("unchecked")
	public List<CustomerHeadForm> findAllCustomers() {

		Query query = em
				.createQuery("SELECT T1.id, T1.name, T1.customerPrefix, T2.companyName, T2.address, T1.status FROM CustomerHead T1, Company T2 where T1.companyId= T2.id" );

		List<Object[]> customerList = query.getResultList();
		List<CustomerHeadForm> autoCustomerHeadForm = new ArrayList<CustomerHeadForm>();
		
		if (customerList != null) {
			for (int i = 0; i < customerList.size(); i++) {
				CustomerHeadForm customerHeadForm = new CustomerHeadForm();
				customerHeadForm.setId(Long.parseLong(customerList.get(i)[0].toString()));
				customerHeadForm.setName(customerList.get(i)[1].toString());
				customerHeadForm.setCustomerPrefix(customerList.get(i)[2].toString());
				customerHeadForm.setCompanyName((customerList.get(i)[3].toString()));
				customerHeadForm.setAddress(customerList.get(i)[4].toString());
				customerHeadForm.setStatus(customerList.get(i)[5].toString());
				autoCustomerHeadForm.add(customerHeadForm);
			}
			return autoCustomerHeadForm;
		} else {
			return null;
		}
	}

	public CustomerHead findCustomers(Long id) {

		return em.find(CustomerHead.class, id);
	}

	@Override
	public TypedQuery<CustomerHead> getCustomerDetailsList(String username) {
		{
			if (username == null || username.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<CustomerHead> q = em.createQuery(
					"SELECT o FROM CustomerHead AS o WHERE o.name = :username",
					CustomerHead.class);
			q.setParameter("username", username);
			return q;
		}
	}
	
	@Override
	public TypedQuery<CustomerBranch> getBranchCustomerDetailsList(String username) {
		{
			if (username == null || username.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<CustomerBranch> q = em.createQuery(
					"SELECT o FROM CustomerBranch AS o WHERE o.name = :username",
					CustomerBranch.class);
			q.setParameter("username", username);
			return q;
		}
	}
	
	@Override
	public TypedQuery<CustomerSubsidiary> getSubsCustomerDetailsList(String username) {
		{
			if (username == null || username.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<CustomerSubsidiary> q = em.createQuery(
					"SELECT o FROM CustomerSubsidiary AS o WHERE o.name = :username",
					CustomerSubsidiary.class);
			q.setParameter("username", username);
			return q;
		}
	}

	public TypedQuery<CustomerHead> findCustomer(String customer) {
		TypedQuery<CustomerHead> q = em.createQuery(
				"SELECT o FROM CustomerHead AS o WHERE o.name = :customer",
				CustomerHead.class);
		q.setParameter("customer", customer);
		return q;
	}

	@Override
	public TypedQuery<CustomerHead> getByPending() {

		TypedQuery<CustomerHead> q = em
				.createQuery(
						"SELECT o FROM CustomerHead o where o.status = 'Pending' or o.status = 'Rejected'",
						CustomerHead.class);
		return q;

	}

	@Override
	public TypedQuery<CustomerHead> getApproved() {

		TypedQuery<CustomerHead> q = em.createQuery(
				"SELECT o FROM CustomerHead o where o.status = 'Approved'",
				CustomerHead.class);
		return q;

	}

	@Override
	public CustomerBranch getByCustomerBranchId(Long id) {
		if (id == null)
			return null;
		return em.find(CustomerBranch.class, id);
	}

	@Transactional
	public void insertCustomerBranch(CustomerBranch customerBranch) {

		em.persist(customerBranch);

	}

	@Override
	public TypedQuery<CustomerBranch> getByCustomerKeyAndStatus(
			String customerHeadKey) {
		{
			if (customerHeadKey == null || customerHeadKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			TypedQuery<CustomerBranch> q = em
					.createQuery(
							"SELECT o FROM CustomerBranch o where o.customerHeadKey = :customerHeadKey and (o.status = 'Pending' or o.status = 'Rejected')",
							CustomerBranch.class);
			q.setParameter("customerHeadKey", customerHeadKey);
			return q;
		}
	}

	@Transactional
	public void updateBranch(CustomerBranch customer) {

		em.merge(customer);
		em.flush();

	}

	@SuppressWarnings("unchecked")
	public Collection<CustomerBranch> findAllCustomerBranch() {

		Query query = em.createQuery("SELECT o FROM CustomerBranch o");

		return (Collection<CustomerBranch>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TypedQuery<CustomerHead> getAllCustomerHead() {

		TypedQuery<CustomerHead> query = em.createQuery("SELECT o FROM CustomerHead o", CustomerHead.class);

		return  query;
	}

	@SuppressWarnings("unchecked")
	public Collection<CustomerBranch> findAllCustomerBranchApprovedList() {

		Query query = em
				.createQuery("SELECT o FROM CustomerBranch o where o.status='Approved'");

		return (Collection<CustomerBranch>) query.getResultList();
	}

	public TypedQuery<CustomerBranch> getCustBranchByIdAndStatus() {
		TypedQuery<CustomerBranch> q = em.createQuery(
				"SELECT o FROM CustomerBranch o where o.status='Pending'",
				CustomerBranch.class);

		return q;
	}
	
	public TypedQuery<CustomerBranch> getCustBranchByIdAndaStatus() {
		TypedQuery<CustomerBranch> q = em.createQuery(
				"SELECT o FROM CustomerBranch o where o.aStatus='Pending'",
				CustomerBranch.class);

		return q;
	}

	@Override
	public TypedQuery<CustomerSubsidiary> getByCustomerHeadKeyAndStatus(
			String customerHeadKey) {
		{
			if (customerHeadKey == null || customerHeadKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			TypedQuery<CustomerSubsidiary> q = em
					.createQuery(
							"SELECT o FROM CustomerSubsidiary o where o.customerHeadKey = :customerHeadKey and (o.status = 'Pending' or o.status = 'Rejected')",
							CustomerSubsidiary.class);
			q.setParameter("customerHeadKey", customerHeadKey);
			return q;
		}
	}

	@Transactional
	public void insertCustomerSubsidiary(CustomerSubsidiary customerSubsidiary) {

		em.persist(customerSubsidiary);

	}

	@Override
	public CustomerSubsidiary getByCustomerSubsidiaryId(Long id) {
		if (id == null)
			return null;
		return em.find(CustomerSubsidiary.class, id);
	}

	@Transactional
	public void updateSubsidiary(CustomerSubsidiary customer) {

		em.merge(customer);
		em.flush();

	}

	@SuppressWarnings("unchecked")
	public Collection<CustomerSubsidiary> findAllCustomerSubsidiary() {

		Query query = em.createQuery("SELECT o FROM CustomerSubsidiary o");

		return (Collection<CustomerSubsidiary>) query.getResultList();
	}

	public TypedQuery<CustomerSubsidiary> getCustSubsByIdAndStatus() {
		TypedQuery<CustomerSubsidiary> q = em.createQuery(
				"SELECT o FROM CustomerSubsidiary o where o.status='Pending'",
				CustomerSubsidiary.class);

		return q;
	}
	
	public TypedQuery<CustomerSubsidiary> getCustSubsByIdAndaStatus() {
		TypedQuery<CustomerSubsidiary> q = em.createQuery(
				"SELECT o FROM CustomerSubsidiary o where o.aStatus='Pending'",
				CustomerSubsidiary.class);

		return q;
	}
	
	@Override
	public TypedQuery<CustomerBranch> getByCustomerHeadAndStatus(
			String customerName) {
		{
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			TypedQuery<CustomerBranch> q = em
					.createQuery(
							"SELECT o FROM CustomerBranch o where o.customerHeadName = :customerName and o.status = 'Approved')",
							CustomerBranch.class);
			q.setParameter("customerName", customerName);
			return q;
		}
	}
	
	@Override
	public TypedQuery<CustomerSubsidiary> getByCustomerHeadAndStatusFoeSub(
			String customerName) {
		{
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			TypedQuery<CustomerSubsidiary> q = em
					.createQuery(
							"SELECT o FROM CustomerSubsidiary o where o.customerHeadName = :customerName and o.status = 'Approved')",
							CustomerSubsidiary.class);
			q.setParameter("customerName", customerName);
			return q;
		}
	}
	
	@Override
	public TypedQuery<CustomerBranch> getByCustomerNameForEmail(
			String customerName) {
		{
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			TypedQuery<CustomerBranch> q = em
					.createQuery(
							"SELECT o FROM CustomerBranch o where o.name = :customerName)",
							CustomerBranch.class);
			q.setParameter("customerName", customerName);
			return q;
		}
	}
	
	@Override
	public TypedQuery<CustomerSubsidiary> getBySubCustomerNameForEmail(
			String customerName) {
		{
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			TypedQuery<CustomerSubsidiary> q = em
					.createQuery(
							"SELECT o FROM CustomerSubsidiary o where o.name = :customerName)",
							CustomerSubsidiary.class);
			q.setParameter("customerName", customerName);
			return q;
		}
	}
	
	@Override
	public TypedQuery<CustomerHead> getByHeadCustomerNameForEmail(
			String customerName) {
		{
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			TypedQuery<CustomerHead> q = em
					.createQuery(
							"SELECT o FROM CustomerHead o where o.name = :customerName)",
							CustomerHead.class);
			q.setParameter("customerName", customerName);
			return q;
		}
	}
	

public List<CustomerHeadForm> getCustByStatusCompIDAndIsForTrading(Long companyId, Boolean isForTrading) {
	
//	Query query = em.createQuery(
//			"SELECT T1.id, T1.name, T1.transactionId, T1.customerPrefix, T2.companyName, T2.address, T1.status FROM CustomerHead T1, Company T2 where T1.companyId= T2.id and T1.status='Pending' and (T1.isForTrading=:isForTrading or T1.isForTrading is null) and T2.id="
//					+ companyId);
	Query query = em.createQuery(
			"SELECT T1.id, T1.name, T1.transactionId, T1.customerPrefix, T2.companyName, T2.address, T1.status FROM CustomerHead T1, Company T2 where T1.companyId= T2.id and (T1.isForTrading=:isForTrading or T1.isForTrading is null) and T2.id="
					+ companyId); //fetching all irespective of status
	query.setParameter("isForTrading", isForTrading);
	List<Object[]> customerList = query.getResultList();
	List<CustomerHeadForm> autoCustomer = new ArrayList<CustomerHeadForm>();

	if (customerList != null) {
		for (int i = 0; i < customerList.size(); i++) {
			CustomerHeadForm customerHeadForm = new CustomerHeadForm();
			customerHeadForm.setId(Long.parseLong(customerList.get(i)[0].toString()));
			customerHeadForm.setName(customerList.get(i)[1].toString());
			customerHeadForm.setTransactionId(customerList.get(i)[2].toString());
			customerHeadForm.setCustomerPrefix(customerList.get(i)[3].toString());
			customerHeadForm.setCompanyName((customerList.get(i)[4].toString()));
			customerHeadForm.setAddress(customerList.get(i)[5].toString());
			customerHeadForm.setStatus(customerList.get(i)[6].toString());
			autoCustomer.add(customerHeadForm);
		}
		return autoCustomer;
		} else {
			return null;
		}
		
		}

}
