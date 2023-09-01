package annona.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import annona.domain.Transaction;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

	@PersistenceContext
	private EntityManager em;
	
	public EntityManager entityManager() {
		EntityManager em = new TransactionDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void insertTransaction(Transaction transaction) {
		em.persist(transaction);
	}

	@SuppressWarnings("unchecked")
	public Collection<Transaction> getList() {

		Query query = em.createQuery("SELECT o FROM Transaction o");

		return (Collection<Transaction>) query.getResultList();
	}
}
