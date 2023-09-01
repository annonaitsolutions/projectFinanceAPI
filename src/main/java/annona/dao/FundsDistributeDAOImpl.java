package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.FundsDistribute;


@Repository
public class FundsDistributeDAOImpl implements FundsDistributeDAO{
	
	@PersistenceContext
	EntityManager em;
	
	public EntityManager entityManager() {
		EntityManager em = new EndUserDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void insertFunds(FundsDistribute funds) {

		em.persist(funds);

	}
	
	@Override
	public FundsDistribute getByFundsId(Long id) {
		if (id == null)
			return null;
		return em.find(FundsDistribute.class, id);
	}

	@Transactional
	public void updateFunds(FundsDistribute funds) {

		em.merge(funds);
		em.flush();

	}
	
	@Override
	public TypedQuery<FundsDistribute> getFundsList(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<FundsDistribute> q = em.createQuery(
					"SELECT o FROM FundsDistribute AS o WHERE o.masterKey = :masterKey",
					FundsDistribute.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}
	
	@Override
	public TypedQuery<FundsDistribute> getFundsListByKeyAndName(String masterKey , String customerName) {
		{
			if (masterKey == null || masterKey.length() == 0 || customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<FundsDistribute> q = em.createQuery(
					"SELECT o FROM FundsDistribute AS o WHERE o.masterKey = :masterKey and o.customerName = :customerName",
					FundsDistribute.class);
			q.setParameter("masterKey", masterKey);
			q.setParameter("customerName", customerName);
			return q;
		}
	}
	
	@Override
	public TypedQuery<FundsDistribute> getFundsListByCustomerName(String customerName) {
		{
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<FundsDistribute> q = em.createQuery(
					"SELECT o FROM FundsDistribute AS o WHERE o.customerName = :customerName",
					FundsDistribute.class);
			q.setParameter("customerName", customerName);
			return q;
		}
}
	
	@Override
	public TypedQuery<FundsDistribute> getFundsList(String masterKey , String customerName) {
		{
			if (masterKey == null || masterKey.length() == 0)
				if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<FundsDistribute> q = em.createQuery(
					"SELECT o FROM FundsDistribute AS o WHERE o.masterKey = :masterKey and o.customerName= :customerName"  ,
					FundsDistribute.class);
			q.setParameter("masterKey", masterKey);
			q.setParameter("customerName", customerName);
			return q;
		}
	}
	
	@Override
	public TypedQuery<FundsDistribute> getCustomerList(String username ,String masterKey ) {
		{
			if (username == null || username.length() == 0)
				if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<FundsDistribute> q = em.createQuery(
					"SELECT o FROM FundsDistribute AS o WHERE o.customerName = :username and o.masterKey = :masterKey",
					FundsDistribute.class);
			q.setParameter("username", username);
			q.setParameter("masterKey", masterKey);
			return q;
		}
	}
	
	@Override
	public TypedQuery<FundsDistribute> getFundsMasterKey(String masterKey) {
		{
			if (masterKey == null || masterKey.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<FundsDistribute> q = em.createQuery(
					"SELECT o FROM FundsDistribute AS o WHERE o.masterKey = :masterKey",
					FundsDistribute.class);
			q.setParameter("masterKey", masterKey);
			return q;
		}
}

}
