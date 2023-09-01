package annona.trade.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import annona.trade.domain.TQuarterly;

public interface TQuarterlyDAO {
	
	public TypedQuery<TQuarterly> getList();
	
	public void updateQuarterly(TQuarterly repay);
	
	public  TQuarterly getByQuarterlyId(Long id);
	
	public void createRepay(TQuarterly repayment);
	
	public TypedQuery<TQuarterly> getByTransIdList(String transactionId);
	
	/**
	 * Method to get trade quarterly records based on customer name
	 */
	List<TQuarterly> getTQuarterlyListByCustomer(String customer);

}
