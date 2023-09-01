package annona.trade.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import annona.trade.domain.THalfYearly;

public interface THalfYearlyDAO {
	
	public TypedQuery<THalfYearly> getList();
	
	public  THalfYearly getByHalfYearlyId(Long id);
	
	public void updateHalfYearly(THalfYearly repay);
	
	public void createRepay(THalfYearly repayment);
	
	public TypedQuery<THalfYearly> getByTransIdList(String transactionId) ;
	
	/**
	 * Method to get trade HalfYearly records based on customer name
	 */
	List<THalfYearly> getTHalfYearlyListByCustomer(String customer);

}
