package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.ClientAppMng;
import annona.domain.CustomerHead;
import annona.domain.MasterPlan;
import annona.domain.WareHouse;
import annona.domain.WorkingCapital;

@Repository
public class WareHouseDAOImpl implements WareHouseDAO{
	
	@PersistenceContext
	EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new WareHouseDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}


	@Transactional
	public WareHouse createMasterPlan(WareHouse wareHouse) {

		em.persist(wareHouse);
		return wareHouse;
	}
	
	@Override
	public WareHouse getByWareHouseId(Long id) {
		if (id == null)
			return null;
		return em.find(WareHouse.class, id);
	}

	
	@Override
	public TypedQuery<WareHouse> getByPending(String customerName) {
		{
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

		TypedQuery<WareHouse> q = em
				.createQuery(
						"SELECT o FROM WareHouse o where o.customerName = :customerName and( o.status = 'Pending' or o.status = 'Approved')",
						WareHouse.class);
		q.setParameter("customerName", customerName);
		return q;
		}

	}
	
	@Override
	public TypedQuery<WareHouse> getWareHouseList(String wareHouseName) {
		{
			if (wareHouseName == null || wareHouseName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<WareHouse> q = em.createQuery(
					"SELECT o FROM WareHouse AS o WHERE o.wareHouseName = :wareHouseName",
					WareHouse.class);
			q.setParameter("wareHouseName", wareHouseName);
			return q;
		}
	}
	
	@Override
	public TypedQuery<WareHouse> getWareHouseFullList(String username) {
		{
			if (username == null || username.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<WareHouse> q = em.createQuery(
					"SELECT o FROM WareHouse AS o WHERE o.customerName = :username",
					WareHouse.class);
			q.setParameter("username", username);
			return q;
		}
	}
	@Transactional
	public void updateWareHouse(WareHouse wareHouse) {

		em.merge(wareHouse);
		em.flush();

	}

	
	@Override
	public TypedQuery<WareHouse> getByAppPending() {

		TypedQuery<WareHouse> q = em
				.createQuery(
						"SELECT o FROM WareHouse o where o.status = 'Pending'",
						WareHouse.class);
		return q;

	}
	
	@Override
	public TypedQuery<WareHouse> getList() {

		TypedQuery<WareHouse> q = em.createQuery(
				"SELECT o FROM WareHouse o ", WareHouse.class);
		return q;

	}
	
	@Override
	public TypedQuery<WareHouse> getWareHouseNameList(String wareHouseName) {
		{
			if (wareHouseName == null || wareHouseName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<WareHouse> q = em.createQuery(
					"SELECT o FROM WareHouse AS o WHERE o.wareHouseName = :wareHouseName",
					WareHouse.class);
			q.setParameter("wareHouseName", wareHouseName);
			return q;
		}
	}
	
	@Override
	public TypedQuery<WareHouse> getWareHouseandStatusList(String username) {
		{
			if (username == null || username.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<WareHouse> q = em.createQuery(
					"SELECT o FROM WareHouse AS o WHERE o.customerName = :username and o.status = 'Approved'",
					WareHouse.class);
			q.setParameter("username", username);
			return q;
		}
	}
}
