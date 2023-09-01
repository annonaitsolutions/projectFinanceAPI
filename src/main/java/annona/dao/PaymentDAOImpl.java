package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Payment;
import annona.domain.PurchaseOrder;

@Repository
public class PaymentDAOImpl  implements PaymentDAO {
	
	@PersistenceContext
	private EntityManager em;

	public EntityManager entityManager() {
		EntityManager em = new PaymentDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	
	@Transactional
	public void updatePayment(Payment payment) {

		em.merge(payment);
		em.flush();

	}

	@Transactional
	public void insertPayment(Payment payment) {

		em.persist(payment);

	}
}
