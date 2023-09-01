package annona.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import annona.domain.Dispute;
import annona.domain.MasterPlan;
import annona.domain.Quarterly;

@Repository
public class DisputeDAOImpl implements DisputeDAO{
	
	@PersistenceContext
	private EntityManager em;
	
	
	public  EntityManager entityManager() {
		EntityManager em = new DisputeDAOImpl().em;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}
	
	@Override
	public TypedQuery<Dispute> getList() {

		TypedQuery<Dispute> q = em.createQuery(
				"SELECT o FROM Dispute o ", Dispute.class);
		return q;

	}
	
	
	@Transactional
	public void updateDispute(Dispute dispute) {

		em.merge(dispute);
		em.flush();

	}
	
	@Override
	public  Dispute getByDisputeId(Long id) {
		if (id == null)
			return null;
		return em.find(Dispute.class, id);
	}
	
	
	
	
	@Transactional
	public void createDispute(Dispute dispute) {	
		em.persist(dispute);				    
	}
	
	@Override
	public TypedQuery<Dispute> getDisputeList(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Dispute> q = em.createQuery("SELECT o FROM Dispute AS o WHERE o.customerName = :customer and o.status='Approved'",Dispute.class);
			q.setParameter("customer", customer);
			return q;
		}
           }
	
	@Override
	public TypedQuery<Dispute> getDisputeSupplierList(String customer)  {
		 {
			if (customer == null || customer.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Dispute> q = em.createQuery("SELECT o FROM Dispute AS o WHERE o.supplierName = :customer",Dispute.class);
			q.setParameter("customer", customer);
			return q;
		}
           }

	public TypedQuery<Dispute> getPoKey(String poKey)  {
		 {
			if (poKey == null || poKey.length() == 0)
				throw new IllegalArgumentException(
						"The poKey argument is required");
			
			TypedQuery<Dispute> q = em.createQuery("SELECT o FROM Dispute AS o WHERE o.poKey = :poKey",Dispute.class);
			q.setParameter("poKey", poKey);
			return q;
		}
         }
	
	@Override
	public TypedQuery<Dispute> getDisputeMngList(String wareHousrMng)  {
		 {
			if (wareHousrMng == null || wareHousrMng.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Dispute> q = em.createQuery("SELECT o FROM Dispute AS o WHERE o.wareHousrMng = :wareHousrMng and o.status='Pending'",Dispute.class);
			q.setParameter("wareHousrMng", wareHousrMng);
			return q;
		}
           }


	@Override
	public TypedQuery<Dispute> getDisputeMngFullList(String wareHousrMng)  {
		 {
			if (wareHousrMng == null || wareHousrMng.length() == 0)
				throw new IllegalArgumentException(
						"The username argument is required");
			
			TypedQuery<Dispute> q = em.createQuery("SELECT o FROM Dispute AS o WHERE o.wareHousrMng = :wareHousrMng",Dispute.class);
			q.setParameter("wareHousrMng", wareHousrMng);
			return q;
		}
           }

}
