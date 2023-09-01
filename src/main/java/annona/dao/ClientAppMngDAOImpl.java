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

import annona.domain.ClientAdmin;
import annona.domain.ClientAppMng;
import annona.form.ClientAppMngForm;

@Repository
public class ClientAppMngDAOImpl implements ClientAppMngDAO{
	
	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new ClientAppMngDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Override
	public TypedQuery<ClientAppMng> getList() {

		TypedQuery<ClientAppMng> q = em.createQuery(
				"SELECT o FROM ClientAppMng o ", ClientAppMng.class);
		return q;

	}

	public List<ClientAppMngForm> getClientAppMngByIdAndStatus() {
		Query query = em
				.createQuery("SELECT T1.id, T1.name, T1.customerPrefix, T2.companyName, T2.address, T1.status FROM ClientAppMng T1, Company T2 where T1.companyId= T2.id and T1.status='Pending'" );

		List<Object[]> customerList = query.getResultList();
		List<ClientAppMngForm> autoclientAppMngForm = new ArrayList<ClientAppMngForm>();
		
		if (customerList != null) {
			for (int i = 0; i < customerList.size(); i++) {
				ClientAppMngForm clientAppMngForm = new ClientAppMngForm();
				clientAppMngForm.setId(Long.parseLong(customerList.get(i)[0].toString()));
				clientAppMngForm.setName(customerList.get(i)[1].toString());
				clientAppMngForm.setCustomerPrefix(customerList.get(i)[2].toString());
				clientAppMngForm.setCompanyName((customerList.get(i)[3].toString()));
				clientAppMngForm.setAddress(customerList.get(i)[4].toString());
				clientAppMngForm.setStatus(customerList.get(i)[5].toString());
				autoclientAppMngForm.add(clientAppMngForm);
			}
			return autoclientAppMngForm;
		} else {
			return null;
		}
	}
	
	
	@Override
	public TypedQuery<ClientAppMng> getByPending() {

		TypedQuery<ClientAppMng> q = em
				.createQuery(
						"SELECT o FROM ClientAppMng o where o.status = 'Pending' or o.status = 'Rejected'",
						ClientAppMng.class);
		return q;

	}
	
	@Override
	public TypedQuery<ClientAppMng> getClientAppMngList(String username) {
		{
			if (username == null || username.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<ClientAppMng> q = em.createQuery(
					"SELECT o FROM ClientAppMng AS o WHERE o.name = :username",
					ClientAppMng.class);
			q.setParameter("username", username);
			return q;
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<ClientAppMngForm> findAllClientAppMngs() {

		Query query = em
				.createQuery("SELECT T1.id, T1.name, T1.customerPrefix, T2.companyName, T2.address, T1.status FROM ClientAppMng T1, Company T2 where T1.companyId= T2.id" );

		List<Object[]> customerList = query.getResultList();
		List<ClientAppMngForm> autoclientAppMngForm = new ArrayList<ClientAppMngForm>();
		
		if (customerList != null) {
			for (int i = 0; i < customerList.size(); i++) {
				ClientAppMngForm clientAppMngForm = new ClientAppMngForm();
				clientAppMngForm.setId(Long.parseLong(customerList.get(i)[0].toString()));
				clientAppMngForm.setName(customerList.get(i)[1].toString());
				clientAppMngForm.setCustomerPrefix(customerList.get(i)[2].toString());
				clientAppMngForm.setCompanyName((customerList.get(i)[3].toString()));
				clientAppMngForm.setAddress(customerList.get(i)[4].toString());
				clientAppMngForm.setStatus(customerList.get(i)[5].toString());
				autoclientAppMngForm.add(clientAppMngForm);
			}
			return autoclientAppMngForm;
		} else {
			return null;
		}
	}


	@Override
	public ClientAppMng getByClientAppMngId(Long id) {
		if (id == null)
			return null;
		return em.find(ClientAppMng.class, id);
	}
	
	@Override
	public List<ClientAppMngForm> getCustAppMngByIdAndAStatusComp(Long companyId) {
		
		Query query = em
				.createQuery("SELECT T1.id, T1.name, T1.transactionId, T1.customerPrefix, T2.companyName, T2.address, T1.status FROM ClientAppMng T1, Company T2 where T1.companyId= T2.id and T1.status='Pending' and T2.id=" + companyId);

		List<Object[]> customerList = query.getResultList();
		List<ClientAppMngForm> autoCustomer = new ArrayList<ClientAppMngForm>();
		
		if (customerList != null) {
			for (int i = 0; i < customerList.size(); i++) {
				ClientAppMngForm clientAppMngForm = new ClientAppMngForm();
				clientAppMngForm.setId(Long.parseLong(customerList.get(i)[0].toString()));
				clientAppMngForm.setName(customerList.get(i)[1].toString());
				clientAppMngForm.setTransactionId(customerList.get(i)[2].toString());
				clientAppMngForm.setCustomerPrefix(customerList.get(i)[3].toString());
				clientAppMngForm.setCompanyName((customerList.get(i)[4].toString()));
				clientAppMngForm.setAddress(customerList.get(i)[5].toString());
				clientAppMngForm.setStatus(customerList.get(i)[6].toString());
				autoCustomer.add(clientAppMngForm);
			}
			return autoCustomer;
		} else {
			return null;
		}
		
		}

	@Transactional
	public void updateUser(ClientAppMng client) {

		em.merge(client);
		em.flush();

	}

	@Transactional
	public void insertCustomer(ClientAppMng client) {

		em.persist(client);

	}
	
public List<ClientAppMngForm> getCustAppMngByStatusCompIDAndIsForTrading(Long companyId, Boolean isForTrading) {
		
//	Query query = em.createQuery(
//			"SELECT T1.id, T1.name, T1.transactionId, T1.customerPrefix, T2.companyName, T2.address, T1.status FROM ClientAppMng T1, Company T2 where T1.companyId= T2.id and T1.status='Pending' and (T1.isForTrading=:isForTrading or T1.isForTrading is null) and T2.id="
//					+ companyId);
	Query query = em.createQuery(
			"SELECT T1.id, T1.name, T1.transactionId, T1.customerPrefix, T2.companyName, T2.address, T1.status FROM ClientAppMng T1, Company T2 where T1.companyId= T2.id and (T1.isForTrading=:isForTrading or T1.isForTrading is null) and T2.id="
					+ companyId); //fetching irrespective of status
	query.setParameter("isForTrading", isForTrading);
	List<Object[]> customerList = query.getResultList();
	List<ClientAppMngForm> autoCustomer = new ArrayList<ClientAppMngForm>();

	if (customerList != null) {
		for (int i = 0; i < customerList.size(); i++) {
			ClientAppMngForm clientAppMngForm = new ClientAppMngForm();
			clientAppMngForm.setId(Long.parseLong(customerList.get(i)[0].toString()));
			clientAppMngForm.setName(customerList.get(i)[1].toString());
			clientAppMngForm.setTransactionId(customerList.get(i)[2].toString());
			clientAppMngForm.setCustomerPrefix(customerList.get(i)[3].toString());
			clientAppMngForm.setCompanyName((customerList.get(i)[4].toString()));
			clientAppMngForm.setAddress(customerList.get(i)[5].toString());
			clientAppMngForm.setStatus(customerList.get(i)[6].toString());
			autoCustomer.add(clientAppMngForm);
		}
		return autoCustomer;
		} else {
			return null;
		}
		
		}

}
