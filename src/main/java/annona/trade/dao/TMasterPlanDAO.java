package annona.trade.dao;

import javax.persistence.TypedQuery;

import annona.domain.MasterPlan;
import annona.trade.domain.TMasterPlan;

public interface TMasterPlanDAO {

	public TMasterPlan createMasterPlan(TMasterPlan masterPlan);
	
	public  TMasterPlan getByMasterPlanId(Long id) ;
	
	public  TypedQuery<TMasterPlan> getAmount(String userName,String masterKey);
	
	public TypedQuery<TMasterPlan> getByStatusAndCust(String customer); 

	public TypedQuery<TMasterPlan> getMasterKey(String customer);
	
	public TypedQuery<TMasterPlan> getMasterPlanFullList(String customer);
	
	public TypedQuery<TMasterPlan> getMasterPlanByPenStatus();
	
	public TypedQuery<TMasterPlan> getMasterPlanByAppStatus() ;
	
	public void updatePlan(TMasterPlan master);
	
	public TypedQuery<TMasterPlan> getAllMasterPlans();
	
	public TypedQuery<TMasterPlan> getMasterPlanByMng();
	
	public TypedQuery<TMasterPlan> getMasterPlanForAccept(String customer);
	
	public TypedQuery<TMasterPlan> getMasterPlanForFunds(String customer);
	
	public TypedQuery<TMasterPlan> getMasterPlanByMasterKey(String masterKey);
	
	public TypedQuery<TMasterPlan> getMasterPlanByClientPenStatus();
	
	public TypedQuery<TMasterPlan> getApprovedMasterKey();


}
