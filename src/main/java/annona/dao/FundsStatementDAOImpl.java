package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.CustomerHead;
import annona.domain.FundsStatement;
@Repository
public class FundsStatementDAOImpl implements FundsStatementDAO {
	
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new FundsStatementDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<FundsStatement> getStatementList(String username) {
		{
			if (username == null || username.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");

			TypedQuery<FundsStatement> q = em.createQuery(
					"SELECT o FROM FundsStatement AS o WHERE o.customerHeadName = :username",
					FundsStatement.class);
			q.setParameter("username", username);
			return q;
		}
	}
	
	@Override
	public FundsStatement getByFundsId(Long id) {
		if (id == null)
			return null;
		return em.find(FundsStatement.class, id);
	}

	@Transactional
	public void updateStatement(FundsStatement statement) {

		em.merge(statement);
		em.flush();

	}

	@Transactional
	public void insertStatement(FundsStatement statement) {

		em.persist(statement);

	}

}
