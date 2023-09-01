package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.EndUser;
import annona.domain.Transaction;

@Repository
public class EndUserDAOImpl implements EndUserDAO {

	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new EndUserDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public TypedQuery<EndUser> findByUsername(String username) {
		if (username == null || username.length() == 0)
			throw new IllegalArgumentException("The username argument is required");

		TypedQuery<EndUser> q = em.createQuery("SELECT o FROM EndUser AS o WHERE o.userName = :username",
				EndUser.class);
		q.setParameter("username", username);
		return q;
	}

	public TypedQuery<EndUser> findByUsernameAndEmail(String username, String email) {
		if (username == null || username.length() == 0)
			throw new IllegalArgumentException("The username argument is required");

		TypedQuery<EndUser> q = em.createQuery(
				"SELECT o FROM EndUser AS o WHERE o.userName = :username and o.email =:email", EndUser.class);
		q.setParameter("username", username);
		q.setParameter("email", email);
		return q;
	}

	@SuppressWarnings("unchecked")
	public Collection<EndUser> getList() {

		Query query = em.createQuery(
				"SELECT o FROM EndUser o where o.currentRole = 'ROLE_BANKEMP' or o.currentRole='ROLE_APPROVALMNG'");

		return (Collection<EndUser>) query.getResultList();
	}

	@Override
	public TypedQuery<EndUser> getByRole() {

		TypedQuery<EndUser> q = em.createQuery(
				"SELECT o FROM EndUser o where o.currentRole = 'ROLE_BANKEMP' and o.status = 'Pending'", EndUser.class);
		return q;

	}

	@Override
	public TypedQuery<EndUser> getByRoleList() {

		TypedQuery<EndUser> q = em.createQuery("SELECT o FROM EndUser o where o.currentRole = 'ROLE_BANKEMP'",
				EndUser.class);
		return q;

	}

	@Override
	public TypedQuery<EndUser> getByBankEmp() {

		TypedQuery<EndUser> q = em.createQuery(
				"SELECT o FROM EndUser o where o.currentRole = 'ROLE_BANKEMP' and o.status = 'Approved'",
				EndUser.class);
		return q;

	}

	@Override
	public TypedQuery<EndUser> getByApprovalMng() {

		TypedQuery<EndUser> q = em.createQuery("SELECT o FROM EndUser o where o.currentRole = 'ROLE_APPROVALMNG' ",
				EndUser.class);
		return q;

	}

	public EndUser findId(Long id) {

		return em.find(EndUser.class, id);

	}

	@Override
	public EndUser getByApprovalId(Long id) {
		if (id == null)
			return null;
		return em.find(EndUser.class, id);
	}

	@Transactional
	public void mergeUser(EndUser endUser) {

		em.merge(endUser);
		em.flush();
	}

	@Transactional
	public EndUser createUser(EndUser user) {

		em.persist(user);
		return user;
	}

	@Transactional
	public void Transaction(Transaction transcation) {
		em.persist(transcation);
	}

	@Transactional
	public void update(EndUser endUser) {

		em.merge(endUser);

		em.flush();
	}

	public EndUser GetUser(String userName) {

		return em.find(EndUser.class, userName);

	}

	public TypedQuery<EndUser> getAllUsers() {

		TypedQuery<EndUser> q = em.createQuery("SELECT o FROM EndUser o where o.currentRole = 'ROLE_USER' ",
				EndUser.class);
		return q;
	}

	/**
	 * Method to get Users for Blocking/Unblocking
	 * 
	 * @return
	 */
	@Override
	public List<EndUser> getUsersForBlockUnblock() {
		TypedQuery<EndUser> q = em.createQuery(
				"SELECT NEW EndUser(o.id,o.userName,o.currentRole,o.accessStatus,o.accExpiryDate,o.accRenewStatus) FROM EndUser o WHERE o.role NOT IN (1,2,3)",
				EndUser.class);
		return q.getResultList();
	}

	/**
	 * Method to get Users for Blocking/Unblocking
	 * 
	 * @return
	 */
	@Override
	public List<EndUser> getUsersForBlockUnblockApproval() {
		TypedQuery<EndUser> q = em.createQuery(
				"SELECT new EndUser(o.id,o.userName,o.currentRole,o.accessStatus,o.accExpiryDate,o.accRenewStatus) FROM EndUser o WHERE o.role NOT IN (1,2,3) and ((o.accessStatus='Block' or o.accessStatus='Unblock') or o.accRenewStatus='Pending')",
				EndUser.class);
		return q.getResultList();
	}

	/**
	 * Method to get BankEmp,ApprovalMng for Blocking/Unblocking/Renewing
	 * 
	 * @return
	 */
	@Override
	public List<EndUser> getUsersForBlockUnblockRenew() {
		TypedQuery<EndUser> q = em.createQuery(
				"SELECT NEW EndUser(o.id,o.userName,o.currentRole,o.accessStatus,o.accExpiryDate,o.accRenewStatus) FROM EndUser o WHERE o.role IN (2,3)",
				EndUser.class);
		return q.getResultList();
	}

	/**
	 * Method to get all users except Admin
	 */
	@Override
	public List<EndUser> getUsersExceptAdmin() {
		TypedQuery<EndUser> q = em.createQuery("SELECT o FROM EndUser o WHERE o.role NOT IN (1)", EndUser.class);
		return q.getResultList();
	}

	@Override
	public TypedQuery<EndUser> getByBankDetails() {

		TypedQuery<EndUser> q = em.createQuery(
				"SELECT o FROM EndUser o where o.currentRole = 'ROLE_BANKEMP' and o.status = 'Approved' ",
				EndUser.class);
		return q;

	}

}
