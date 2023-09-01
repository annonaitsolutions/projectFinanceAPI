package annona.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.FullAmount;

public interface FullAmountDAO {
	
	public TypedQuery<FullAmount> getList();
	
	public void updateFullAmount(FullAmount repay);
	
	public  FullAmount getByFullAmountId(Long id);
	
	public void createRepay(FullAmount repayment);
	
	public TypedQuery<FullAmount> getByTransIdList(String transactionId);
	
	/**
	 * Method to get FullAmount records based on customer name
	 */
	List<FullAmount> getFullAmountListByCustomer(String customer);

}
