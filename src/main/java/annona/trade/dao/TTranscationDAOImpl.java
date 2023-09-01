package annona.trade.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import annona.domain.Transaction;
import annona.trade.domain.TTransaction;

@Repository
public class TTranscationDAOImpl implements TTransactionDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void insertTransaction(TTransaction transaction) {
		em.persist(transaction);
	}

	@SuppressWarnings("unchecked")
	public Collection<TTransaction> getList() {

		Query query = em.createQuery("SELECT o FROM TTransaction o");

		return (Collection<TTransaction>) query.getResultList();
	}
}
