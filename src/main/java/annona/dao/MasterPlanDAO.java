package annona.dao;

import java.text.ParseException;
import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.MasterPlan;

public interface MasterPlanDAO {

	public MasterPlan createMasterPlan(MasterPlan masterPlan);

	public MasterPlan getByMasterPlanId(Long id);

	public TypedQuery<MasterPlan> getAmount(String userName, String masterKey);

	public TypedQuery<MasterPlan> getByStatusAndCust(String customer);

	public TypedQuery<MasterPlan> getMasterKey(String customer);

	public TypedQuery<MasterPlan> getMasterPlanFullList(String customer);

	public TypedQuery<MasterPlan> getMasterPlanByPenStatus();

	public TypedQuery<MasterPlan> getMasterPlanByAppStatus();

	public void updatePlan(MasterPlan master);

	public TypedQuery<MasterPlan> getAllMasterPlans();

	public TypedQuery<MasterPlan> getMasterPlanByMng();

	public TypedQuery<MasterPlan> getMasterPlanForAccept(String customer);

	public TypedQuery<MasterPlan> getMasterPlanForFunds(String customer);

	public TypedQuery<MasterPlan> getMasterPlanByMasterKey(String masterKey);

	public TypedQuery<MasterPlan> getMasterPlanByClientPenStatus();

	public TypedQuery<MasterPlan> getApprovedMasterKey();

	List<MasterPlan> getMasterPlanForApprovalByClientPenStatus() throws ParseException;

	List<MasterPlan> getAllMasterPlanWithCompanyName() throws ParseException;

}
