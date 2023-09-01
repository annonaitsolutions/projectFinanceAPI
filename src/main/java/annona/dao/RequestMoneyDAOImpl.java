package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.CustomerHead;
import annona.domain.RequestMoney;

@Repository
public class RequestMoneyDAOImpl implements RequestMoneyDAO {
	
	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new RequestMoneyDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Transactional
	public void insertRequest(RequestMoney money) {

		em.persist(money);

	}
	
	@Override
	public RequestMoney getRequestMoneyId(Long id) {
		if (id == null)
			return null;
		return em.find(RequestMoney.class, id);
	}
	
	@Override
	public TypedQuery<RequestMoney> getRequestSentList(String customerName) {
		{
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<RequestMoney> q = em.createQuery(
					"SELECT o FROM RequestMoney AS o WHERE o.requestedBy = :customerName",
					RequestMoney.class);
			q.setParameter("customerName", customerName);
			return q;
		}
	}
	
	@Override
	public TypedQuery<RequestMoney> getRequestRecievedList(String customerName) {
		{
			if (customerName == null || customerName.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<RequestMoney> q = em.createQuery(
					"SELECT o FROM RequestMoney AS o WHERE o.requestedFrom = :customerName",
					RequestMoney.class);
			q.setParameter("customerName", customerName);
			return q;
		}
	}

}
