package annona.trade.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import annona.trade.domain.TRepayment;

public interface TRepaymentDAO {
	
	public TypedQuery<TRepayment> getRepaymentBYMAsterKey(String masterKey);
	
	public void createRepay(TRepayment repayment);
	
	public TypedQuery<TRepayment> getMasterPlanForRepayment(String customer) ;
	
	public TypedQuery<TRepayment> getRepayByIdAndStatus();
	
	public  TRepayment getByRepaymentId(Long id);

	public void updateRepayment(TRepayment repay);
	
	public TypedQuery<TRepayment> getList();
	
	public TypedQuery<TRepayment> getRepayByAccept();
	
	public TypedQuery<TRepayment> getRepayByIdAndStatusBank();
	
	public TypedQuery<TRepayment> getRepayByAppMngStatus();
	
	public TypedQuery<TRepayment> getMasterPlanForRepaymentAndAppStatus(String customer);
	
	public TypedQuery<TRepayment> getRepayFullList();
	
	/**
	 * Method to get trade repayment list based on accept 
	 */
	List<TRepayment> getTRepaymentListByAccept(String accept);
}
