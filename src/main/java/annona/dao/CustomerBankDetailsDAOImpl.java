package annona.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.BankDetails;
import annona.domain.CustomerBankDetails;

@Repository
public class CustomerBankDetailsDAOImpl implements CustomerBankDetailsDAO {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public void insertCustBankDetails(CustomerBankDetails customerBankDetails) {

		em.persist(customerBankDetails);

	}

	@SuppressWarnings("unchecked")
	public Collection<CustomerBankDetails> getList() {
		Query query = em.createQuery("SELECT o FROM CustomerBankDetails o");

		return (Collection<CustomerBankDetails>) query.getResultList();
	}

	@Transactional
	public void update(CustomerBankDetails customerBankDetails) {

		em.merge(customerBankDetails);

		em.flush();
	}

	public CustomerBankDetails findId(Long id) {

		return em.find(CustomerBankDetails.class, id);
	}
	
	public TypedQuery<CustomerBankDetails> getBankDetails() {
		TypedQuery<CustomerBankDetails> q = em.createQuery(
				"SELECT o FROM CustomerBankDetails o",
				CustomerBankDetails.class);

		return q;
	}

	
}
