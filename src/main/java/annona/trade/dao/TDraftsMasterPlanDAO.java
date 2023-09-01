package annona.trade.dao;

import javax.persistence.TypedQuery;

import annona.domain.DraftsMasterPlan;
import annona.trade.domain.TDraftsMasterPlan;

public interface TDraftsMasterPlanDAO {

	public TDraftsMasterPlan createDrafts(TDraftsMasterPlan drafts);
	
	public  TDraftsMasterPlan getByMasterPlanId(Long id);
	
	public TypedQuery<TDraftsMasterPlan> getByCustomerAndFlag(String customer) ;
	
	public void updateMasterPlan(TDraftsMasterPlan drafts);
	
	 public void deleteRow(Long id);

  
}
