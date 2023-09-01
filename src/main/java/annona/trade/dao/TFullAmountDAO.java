package annona.trade.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import annona.trade.domain.TFullAmount;

public interface TFullAmountDAO {
	
	public TypedQuery<TFullAmount> getList();
	
	public void updateFullAmount(TFullAmount repay);
	
	public  TFullAmount getByFullAmountId(Long id);
	
	public void createRepay(TFullAmount repayment);
	
	public TypedQuery<TFullAmount> getByTransIdList(String transactionId);
	
	/**
	 * Method to get trade FullAmount records based on customer name
	 */
	List<TFullAmount> getTFullAmountListByCustomer(String customer);

}
