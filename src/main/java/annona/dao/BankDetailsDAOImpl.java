package annona.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.BankDetails;
import annona.domain.PurchaseOrder;

@Repository
public class BankDetailsDAOImpl implements BankDetailsDAO {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public void insertBankDetails(BankDetails bankDetails) {

		em.persist(bankDetails);

	}

	@SuppressWarnings("unchecked")
	public Collection<BankDetails> getList() {
		Query query = em.createQuery("SELECT o FROM BankDetails o");

		return (Collection<BankDetails>) query.getResultList();
	}

	@Transactional
	public void update(BankDetails bankDetails) {

		em.merge(bankDetails);

		em.flush();
	}

	public BankDetails findId(Long id) {

		return em.find(BankDetails.class, id);
	}
	
	public TypedQuery<BankDetails> getBankDetails() {
		TypedQuery<BankDetails> q = em.createQuery(
				"SELECT o FROM BankDetails o",
				BankDetails.class);

		return q;
	}

}
