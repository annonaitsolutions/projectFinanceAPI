package annona.dao;



import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.NewBuyer;
import annona.domain.Notification;



@Repository
public class NotificationDAOImpl implements NotificationDAO {

	@PersistenceContext
	EntityManager em;
	
	public EntityManager entityManager() {
		EntityManager em = new NotificationDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void insertNotification(Notification notification){

		em.persist(notification);

	}

	public TypedQuery<Notification> findByCustomerName(String  customerName) {
		if (customerName == null || customerName.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<Notification> q = em.createQuery(
				"SELECT o FROM Notification AS o WHERE o.customerName=:customerName and notificationAcc='main'",Notification.class);
		q.setParameter("customerName", customerName);
		return q;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Notification> getList() {

		Query query = em.createQuery("SELECT o FROM Notification o");

		return (Collection<Notification>) query.getResultList();
	}

}
