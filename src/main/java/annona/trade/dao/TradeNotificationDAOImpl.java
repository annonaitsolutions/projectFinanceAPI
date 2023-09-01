package annona.trade.dao;



import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.trade.domain.TradeNotification;








@Repository
public class TradeNotificationDAOImpl implements TradeNotificationDAO {

	@PersistenceContext
	EntityManager em;
	
	public EntityManager entityManager() {
		EntityManager em = new TradeNotificationDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void insertNotification(TradeNotification tradeNotification){

		em.persist(tradeNotification);

	}

	public TypedQuery<TradeNotification> findByCustomerName(String  customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<TradeNotification> q = em.createQuery(
				"SELECT o FROM TradeNotification AS o WHERE o.customerName=:customerName and notificationAcc='Trade'",TradeNotification.class);
		q.setParameter("customerName", customerName);
		return q;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<TradeNotification> getList() {

		Query query = em.createQuery("SELECT o FROM TradeNotification o");

		return (Collection<TradeNotification>) query.getResultList();
	}

}
