package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Company;

@Repository
public class CompanyDAOImpl implements CompanyDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Company getByCompanyId(Long id) {
		// TODO Auto-generated method stub
		TypedQuery<Company> q = em.createQuery("SELECT o FROM Company o WHERE o.id = :id", Company.class);
		q.setParameter("id", id);
		return q.getSingleResult();
	}
	
	@Override
	public Company getByCompanyName(String companyName) {
		// TODO Auto-generated method stub
		TypedQuery<Company> q = em.createQuery("SELECT o FROM Company o WHERE o.companyName = :companyName", Company.class);
		q.setParameter("companyName", companyName);
		return q.getSingleResult();
	}

	@Override
	@Transactional
	public void insertCompany(Company company) {
		// TODO Auto-generated method stub
		em.persist(company);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Company> findAllCompany() {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT o FROM Company o");
		return (Collection<Company>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanyByStatuss(String status) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT o FROM Company o WHERE o.status = :status", Company.class);
		q.setParameter("status", status);
		return (List<Company>) q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanyList() {
		List detail;
		
		Query query = em.createQuery("SELECT o FROM Company o ");
		try {
			 detail = query.getResultList();
		} catch (Exception ex) {
			return null;
		}
		
		return detail;
	}

	@Override
	@Transactional
	public void updateCompany(Company company) {
		// TODO Auto-generated method stub
		em.merge(company);
		em.flush();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanyByStatusAndIsForTrading(String status, Boolean isForTrading) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT o FROM Company o WHERE o.status = :status and (o.isForTrading=:isForTrading or o.isForTrading is null)", Company.class);
		q.setParameter("status", status);
		q.setParameter("isForTrading", isForTrading);
		return (List<Company>) q.getResultList();
	}

}