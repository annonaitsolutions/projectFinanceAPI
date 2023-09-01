package annona.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.HalfYearly;

public interface HalfYearlyDAO {
	
	public TypedQuery<HalfYearly> getList();
	
	public  HalfYearly getByHalfYearlyId(Long id);
	
	public void updateHalfYearly(HalfYearly repay);
	
	public void createRepay(HalfYearly repayment);
	
	public TypedQuery<HalfYearly> getByTransIdList(String transactionId) ;
	
	/**
	 * Method to get HalfYearly records based on customer name
	 */
	List<HalfYearly> getHalfYearlyListByCustomer(String customer);

}
