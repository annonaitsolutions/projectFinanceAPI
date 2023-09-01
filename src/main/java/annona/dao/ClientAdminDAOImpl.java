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
import annona.domain.CustomerHead;
import annona.form.ClientAdminForm;

@Repository
public class ClientAdminDAOImpl implements ClientAdminDAO {

	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new ClientAdminDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Override
	public TypedQuery<ClientAdmin> getList() {

		TypedQuery<ClientAdmin> q = em.createQuery("SELECT o FROM ClientAdmin o ", ClientAdmin.class);
		return q;

	}

	public TypedQuery<ClientAdmin> getClientAdminByIdAndStatus() {
		TypedQuery<ClientAdmin> q = em.createQuery("SELECT o FROM ClientAdmin o where o.status='Pending'",
				ClientAdmin.class);

		return q;
	}

	public TypedQuery<ClientAdmin> getClientAdminByStatusAndTradeFlag(Boolean isForTrading) {
		TypedQuery<ClientAdmin> q;

		if (isForTrading) {
			q = em.createQuery("SELECT o FROM ClientAdmin o where o.status='Pending' and isForTrading=:isForTrading",
					ClientAdmin.class);
		} else {
			q = em.createQuery(
					"SELECT o FROM ClientAdmin o where o.status='Pending' and (isForTrading=:isForTrading or isForTrading is null)",
					ClientAdmin.class);
		}
		q.setParameter("isForTrading", isForTrading);

		return q;
	}

	public List<ClientAdminForm> getClientAdminByIdAndAStatusCompAndIsForTrading(Boolean isForTrading) {

		// T1.isForTrading is null is added because whole table is not having value for
		// flag isForTrading, once all tables will get updated then we can remove is
		// null condition
		Query query = em.createQuery(
				"SELECT T1.id, T1.name, T1.customerPrefix, T2.companyName, T2.address, T1.status FROM ClientAdmin T1, Company T2 where T1.companyId= T2.id and T1.status='Pending' and (T1.isForTrading=:isForTrading OR T1.isForTrading is null)");
		query.setParameter("isForTrading", isForTrading);
		List<Object[]> customerList = query.getResultList();
		List<ClientAdminForm> autoClientAdminForm = new ArrayList<ClientAdminForm>();

		if (customerList != null) {
			for (int i = 0; i < customerList.size(); i++) {
				ClientAdminForm clientAdminForm = new ClientAdminForm();
				clientAdminForm.setId(Long.parseLong(customerList.get(i)[0].toString()));
				clientAdminForm.setName(customerList.get(i)[1].toString());
				clientAdminForm.setCustomerPrefix(customerList.get(i)[2].toString());
				clientAdminForm.setCompanyName((customerList.get(i)[3].toString()));
				clientAdminForm.setAddress(customerList.get(i)[4].toString());
				clientAdminForm.setStatus(customerList.get(i)[5].toString());
				autoClientAdminForm.add(clientAdminForm);
			}
			return autoClientAdminForm;
		} else {
			return null;
		}

	}

	@Override
	public TypedQuery<ClientAdmin> getByPending() {

		TypedQuery<ClientAdmin> q = em.createQuery(
				"SELECT o FROM ClientAdmin o where o.status = 'Pending' or o.status = 'Rejected'", ClientAdmin.class);
		return q;

	}

	@Override
	public TypedQuery<ClientAdminForm> getCustByIdAndStatusComp(Long userId) {

		TypedQuery<ClientAdminForm> q = em.createQuery(
				"SELECT T1.id, T1.name, T1.customerPrefix, T1.status, T2.companyName, T2.address FROM CustomerDetails As T1, Company AS T2 where T1.companyId= T2.id and T1.status='Pending' and T1.id="
						+ userId,
				ClientAdminForm.class);

		return q;
	}

	@Override
	public TypedQuery<ClientAdmin> getClientAdminList(String username) {
		{
			if (username == null || username.length() == 0)
				throw new IllegalArgumentException("The username argument is required");

			TypedQuery<ClientAdmin> q = em.createQuery("SELECT o FROM ClientAdmin AS o WHERE o.name = :username",
					ClientAdmin.class);
			q.setParameter("username", username);
			return q;
		}
	}

	public TypedQuery<ClientAdmin> getCustByIdAndStatus() {
		TypedQuery<ClientAdmin> q = em.createQuery("SELECT o FROM ClientAdmin o where o.status='Pending'",
				ClientAdmin.class);

		return q;
	}

	@SuppressWarnings("unchecked")
	public List<ClientAdminForm> findAllClientAdmins() {

		Query query = em.createQuery(
				"SELECT T1.id, T1.name, T1.customerPrefix, T2.companyName, T2.address, T1.status FROM ClientAdmin T1, Company T2 where T1.companyId= T2.id");

		List<Object[]> customerList = query.getResultList();
		List<ClientAdminForm> autoClientAdminForm = new ArrayList<ClientAdminForm>();

		if (customerList != null) {
			for (int i = 0; i < customerList.size(); i++) {
				ClientAdminForm clientAdminForm = new ClientAdminForm();
				clientAdminForm.setId(Long.parseLong(customerList.get(i)[0].toString()));
				clientAdminForm.setName(customerList.get(i)[1].toString());
				clientAdminForm.setCustomerPrefix(customerList.get(i)[2].toString());
				clientAdminForm.setCompanyName((customerList.get(i)[3].toString()));
				clientAdminForm.setAddress(customerList.get(i)[4].toString());
				clientAdminForm.setStatus(customerList.get(i)[5].toString());
				autoClientAdminForm.add(clientAdminForm);
			}
			return autoClientAdminForm;
		} else {
			return null;
		}
	}
	
	@Override
	public TypedQuery<ClientAdmin> getAllClientAdmin() {
		TypedQuery<ClientAdmin> q = em.createQuery("SELECT o FROM ClientAdmin o",
					ClientAdmin.class);
			
			return q;
	}


	@Override
	public ClientAdmin getByClientAdminId(Long id) {
		if (id == null)
			return null;
		return em.find(ClientAdmin.class, id);
	}

	@Transactional
	public void updateUser(ClientAdmin client) {

		em.merge(client);
		em.flush();

	}

	@Transactional
	public void insertCustomer(ClientAdmin client) {

		em.persist(client);

	}

}
