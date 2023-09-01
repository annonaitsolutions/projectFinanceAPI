package annona.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.MasterPlan;

@Repository
public class MasterPlanDAOImpl implements MasterPlanDAO {

	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new MasterPlanDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public MasterPlan createMasterPlan(MasterPlan masterPlan) {

		em.persist(masterPlan);
		return masterPlan;
	}

	@Override
	public MasterPlan getByMasterPlanId(Long id) {
		if (id == null)
			return null;
		return em.find(MasterPlan.class, id);
	}

	@Transactional
	public void updatePlan(MasterPlan master) {

		em.merge(master);
		em.flush();

	}

	@Override
	public TypedQuery<MasterPlan> getByStatusAndCust(String customer) {
		{
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException("The username argument is required");

			TypedQuery<MasterPlan> q = em.createQuery(
					"SELECT o FROM MasterPlan AS o WHERE o.customer = :customer and o.aStatus ='pending'",
					MasterPlan.class);
			q.setParameter("customer", customer);
			return q;
		}
	}

	public TypedQuery<MasterPlan> getMasterKey(String customer) {

		TypedQuery<MasterPlan> q = em.createQuery(
				"SELECT o FROM MasterPlan AS o WHERE o.customer =:customer and status='Approved'", MasterPlan.class);

		q.setParameter("customer", customer);

		return q;
	}

	@Override
	public TypedQuery<MasterPlan> getMasterPlanFullList(String customer) {
		{
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException("The username argument is required");

			TypedQuery<MasterPlan> q = em.createQuery("SELECT o FROM MasterPlan AS o WHERE o.customer = :customer ORDER BY id DESC",
					MasterPlan.class);
			q.setParameter("customer", customer);
			return q;
		}
	}

	public TypedQuery<MasterPlan> getMasterPlanByPenStatus() {
		TypedQuery<MasterPlan> q = em.createQuery("SELECT o FROM MasterPlan o where o.status='pending'",
				MasterPlan.class);

		return q;
	}

	public TypedQuery<MasterPlan> getMasterPlanByClientPenStatus() {
		TypedQuery<MasterPlan> q = em.createQuery("SELECT o FROM MasterPlan o where o.aStatus='pending'",
				MasterPlan.class);
//		"SELECT o, c.companyName FROM MasterPlan o, Company c where o.companyId = c.id and o.aStatus='pending'",
		return q;
	}

	public TypedQuery<MasterPlan> getMasterPlanByAppStatus() {
		TypedQuery<MasterPlan> q = em.createQuery(
				"SELECT o FROM MasterPlan o where o.status='Approved' and (o.ApprovalSent='No' or o.managerStatus='Rejected')",
				MasterPlan.class);

		return q;
	}

	public TypedQuery<MasterPlan> getAllMasterPlans() {
		TypedQuery<MasterPlan> q = em.createQuery("SELECT o FROM MasterPlan o ", MasterPlan.class);
		return q;
	}

	public TypedQuery<MasterPlan> getMasterPlanByMng() {
		TypedQuery<MasterPlan> q = em.createQuery(
				"SELECT o FROM MasterPlan o where o.managerStatus='Pending' and o.ApprovalSent='Yes'",
				MasterPlan.class);

		return q;
	}

	@Override
	public TypedQuery<MasterPlan> getMasterPlanForAccept(String customer) {
		{
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException("The username argument is required");

			TypedQuery<MasterPlan> q = em.createQuery(
					"SELECT o FROM MasterPlan AS o WHERE o.customer = :customer and o.managerStatus='Approved' and o.accept='None'",
					MasterPlan.class);
			q.setParameter("customer", customer);
			return q;
		}
	}

	@Override
	public TypedQuery<MasterPlan> getMasterPlanForFunds(String customer) {
		{
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException("The username argument is required");

			TypedQuery<MasterPlan> q = em.createQuery(
					"SELECT o FROM MasterPlan AS o WHERE o.customer = :customer and o.accept ='Yes'", MasterPlan.class);
			q.setParameter("customer", customer);
			return q;
		}
	}

	@Override
	public TypedQuery<MasterPlan> getMasterPlanByMasterKey(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException("The username argument is required");

			TypedQuery<MasterPlan> q = em.createQuery("SELECT o FROM MasterPlan AS o WHERE o.masterKey = :masterKey ",
					MasterPlan.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}

	public TypedQuery<MasterPlan> getApprovedMasterKey() {

		TypedQuery<MasterPlan> q = em.createQuery("SELECT o FROM MasterPlan AS o WHERE status='Approved' ",
				MasterPlan.class);

		return q;
	}

	public TypedQuery<MasterPlan> getAmount(String userName, String masterKey) {

		if (masterKey == null || masterKey.length() == 0)
			throw new IllegalArgumentException("The username argument is required");

		TypedQuery<MasterPlan> q = em.createQuery(
				"SELECT o FROM MasterPlan AS o WHERE o.customer = :userName and o.masterKey = :masterKey and status='Approved' ",
				MasterPlan.class);
		q.setParameter("userName", userName);
		q.setParameter("masterKey", masterKey);
		return q;
	}

	@Override
	public List<MasterPlan> getMasterPlanForApprovalByClientPenStatus() throws ParseException {
		Query q = em.createQuery(
				"SELECT o.id, o.customer, o.masterKey, o.masterPlanDate, o.status, c.companyName FROM MasterPlan o, Company c where o.companyId = c.id and o.aStatus='pending' ORDER BY o.id DESC");

		List<Object[]> list = q.getResultList();
		List<MasterPlan> masterPlanList = new ArrayList<MasterPlan>();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				MasterPlan mp = new MasterPlan();
				mp.setId(Long.parseLong(list.get(i)[0].toString()));
				mp.setCustomer(list.get(i)[1].toString());
				mp.setMasterKey(list.get(i)[2].toString());
				mp.setMasterPlanDate(
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse((list.get(i)[3].toString())));
				if (list.get(i)[4] != null)
					mp.setStatus(list.get(i)[4].toString());
				else
					mp.setStatus("");
				mp.setCompanyName(list.get(i)[5].toString());
				masterPlanList.add(mp);
			}
			return masterPlanList;
		} else {
			return null;
		}
	}

	@Override
	public List<MasterPlan> getAllMasterPlanWithCompanyName() throws ParseException {
		Query q = em.createQuery(
				"SELECT o.id, o.customer, o.masterKey, o.masterPlanDate, o.status, c.companyName , o.product FROM MasterPlan o, Company c where  o.companyId = c.id");

		List<Object[]> list = q.getResultList();
		List<MasterPlan> masterPlanList = new ArrayList<MasterPlan>();

		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				MasterPlan mp = new MasterPlan();
				mp.setId(Long.parseLong(list.get(i)[0].toString()));
				mp.setCustomer(list.get(i)[1].toString());
				mp.setMasterKey(list.get(i)[2].toString());
				mp.setMasterPlanDate(
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse((list.get(i)[3].toString())));
				if (list.get(i)[4] != null)
					mp.setStatus(list.get(i)[4].toString());
				else
					mp.setStatus("");
				mp.setCompanyName(list.get(i)[5].toString());
				mp.setProduct(list.get(i)[6].toString());
				masterPlanList.add(mp);
			}
			return masterPlanList;
		} else {
			return null;
		}
	}

}
