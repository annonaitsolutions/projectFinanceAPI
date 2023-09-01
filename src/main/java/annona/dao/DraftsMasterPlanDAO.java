package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.DraftsMasterPlan;

public interface DraftsMasterPlanDAO {

	public DraftsMasterPlan createDrafts(DraftsMasterPlan drafts);
	
	public  DraftsMasterPlan getByMasterPlanId(Long id);
	
	public TypedQuery<DraftsMasterPlan> getByCustomerAndFlag(String customer) ;
	
	public void updateMasterPlan(DraftsMasterPlan drafts);
	
	 public void deleteRow(Long id);

  
}
