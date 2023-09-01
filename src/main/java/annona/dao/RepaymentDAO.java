package annona.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.Repayment;

public interface RepaymentDAO {
	
	public TypedQuery<Repayment> getRepaymentBYMAsterKey(String masterKey);
	
	public void createRepay(Repayment repayment);
	
	public TypedQuery<Repayment> getMasterPlanForRepayment(String customer) ;
	
	public TypedQuery<Repayment> getRepayByIdAndStatus();
	
	public  Repayment getByRepaymentId(Long id);

	public void updateRepayment(Repayment repay);
	
	public TypedQuery<Repayment> getList();
	
	public TypedQuery<Repayment> getRepayByAccept();
	
	public TypedQuery<Repayment> getRepayByIdAndStatusBank();
	
	public TypedQuery<Repayment> getRepayByAppMngStatus();
	
	public TypedQuery<Repayment> getMasterPlanForRepaymentAndAppStatus(String customer);
	
	public TypedQuery<Repayment> getRepayFullList();
	
	/**
	 * Method to get repayment list based on accept 
	 */
	List<Repayment> getRepaymentListByAccept(String accept);
}
