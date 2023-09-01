package annona.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.Quarterly;

public interface QuarterlyDAO {
	
	public TypedQuery<Quarterly> getList();
	
	public void updateQuarterly(Quarterly repay);
	
	public  Quarterly getByQuarterlyId(Long id);
	
	public void createRepay(Quarterly repayment);
	
	public TypedQuery<Quarterly> getByTransIdList(String transactionId);
	
	/**
	 * Method to get quarterly records based on customer name
	 */
	List<Quarterly> getQuarterlyListByCustomer(String customer);

}
