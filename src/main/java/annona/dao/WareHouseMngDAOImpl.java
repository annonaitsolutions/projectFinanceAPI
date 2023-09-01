package annona.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.ClientAdmin;
import annona.domain.WareHouse;
import annona.domain.WareHouseMng;

@Repository
public class WareHouseMngDAOImpl implements WareHouseMngDAO{
	
	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new WareHouseMngDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Override
	public TypedQuery<WareHouseMng> getList() {

		TypedQuery<WareHouseMng> q = em.createQuery(
				"SELECT o FROM WareHouseMng o ", WareHouseMng.class);
		return q;

	}

	public TypedQuery<WareHouseMng> getClientAdminByIdAndStatus() {
		TypedQuery<WareHouseMng> q = em.createQuery(
				"SELECT o FROM WareHouseMng o where o.status='Pending'",
				WareHouseMng.class);

		return q;
	}
	
	public TypedQuery<WareHouseMng> getBankAppByIdAndStatus() {
		TypedQuery<WareHouseMng> q = em.createQuery(
				"SELECT o FROM WareHouseMng o where o.bStatus='Pending'",
				WareHouseMng.class);

		return q;
	}
	
	@Override
	public TypedQuery<WareHouseMng> getByPending() {

		TypedQuery<WareHouseMng> q = em
				.createQuery(
						"SELECT o FROM WareHouseMng o where o.status = 'Pending' or o.status = 'Approved'",
						WareHouseMng.class);
		return q;

	}

	
	public TypedQuery<WareHouseMng> getCustByIdAndStatus() {
		TypedQuery<WareHouseMng> q = em.createQuery(
				"SELECT o FROM WareHouseMng o where o.status='Pending'",
				WareHouseMng.class);

		return q;
	}
	
	@Override
	public TypedQuery<WareHouseMng> getByPending(String customerName) {
		{
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

		TypedQuery<WareHouseMng> q = em
				.createQuery(
						"SELECT o FROM WareHouseMng o where o.customerName = :customerName and( o.status = 'Pending' or o.status = 'Rejected')",
						WareHouseMng.class);
		q.setParameter("customerName", customerName);
		return q;
		}

	}
	
	@Override
	public TypedQuery<WareHouseMng> getWareHouseMngList(String username) {
		{
			if (username == null || username.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<WareHouseMng> q = em.createQuery(
					"SELECT o FROM WareHouseMng AS o WHERE o.mngName = :username",
					WareHouseMng.class);
			q.setParameter("username", username);
			return q;
		}
	}
		@Override
		public TypedQuery<WareHouseMng> getWareHouseMngFullList(String username) {
			{
				if (username == null || username.length() == 0)
					throw new IllegalArgumentException(
							"The username argument is required");

				TypedQuery<WareHouseMng> q = em.createQuery(
						"SELECT o FROM WareHouseMng AS o WHERE o.customerName = :username",
						WareHouseMng.class);
				q.setParameter("username", username);
				return q;
			}
	}


	@Override
	public WareHouseMng getByWareHouseMngId(Long id) {
		if (id == null)
			return null;
		return em.find(WareHouseMng.class, id);
	}

	@Transactional
	public void updateUser(WareHouseMng mng) {

		em.merge(mng);
		em.flush();

	}

	@Transactional
	public void insertWareHouseMng(WareHouseMng mng) {

		em.persist(mng);

	}

}
