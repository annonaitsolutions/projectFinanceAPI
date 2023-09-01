package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Regulation;
import annona.domain.Transaction;

@Repository
public class RegulationsDAOImpl implements RegulationsDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void createUser(Regulation regulations) {
		em.persist(regulations);
	}

	@Transactional
	public void insertTransaction(Transaction transaction) {
		em.persist(transaction);
	}

	public TypedQuery<Regulation> findByCountry(String country) {
		if (country == null || country.length() == 0)
			throw new IllegalArgumentException(
					"The country argument is required");

		TypedQuery<Regulation> q = em.createQuery(
				"SELECT o FROM Regulation AS o WHERE o.countryName = :country",
				Regulation.class);
		q.setParameter("country", country);
		return q;
	}

	@SuppressWarnings("unchecked")
	public Collection<Regulation> getList() {

		Query query = em.createQuery("SELECT o FROM Regulation o");

		return (Collection<Regulation>) query.getResultList();
	}

	public Regulation findId(Long id) {

		return em.find(Regulation.class, id);

	}

	@Transactional
	public void update(Regulation regulation) {

		em.merge(regulation);

		em.flush();
	}
	/**
	 * Method to get Regulations list based on country and category
	 */
	public List<Regulation> getRegulationsByCountryNCategory(String country, String category) {	

		TypedQuery<Regulation> q = em.createQuery(
				"SELECT o FROM Regulation AS o WHERE o.countryName =:country and "
				+ "(o.bannedGoods =:category or o.exportedGoods =:category)",
				Regulation.class);
		q.setParameter("country", country);
		q.setParameter("category", category);
		
		return q.getResultList();
	}

}
