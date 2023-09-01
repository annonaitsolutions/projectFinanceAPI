package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.FundsDistribute;

public interface FundsDistributeDAO {
	
	public void insertFunds(FundsDistribute funds);
	
	public FundsDistribute getByFundsId(Long id);
	
	public void updateFunds(FundsDistribute funds);
	
	public TypedQuery<FundsDistribute> getCustomerList(String username ,String masterKey );
	
	public TypedQuery<FundsDistribute> getFundsList(String masterKey);
	
	public TypedQuery<FundsDistribute> getFundsList(String masterKey , String customerName) ;
	
	public TypedQuery<FundsDistribute> getFundsListByCustomerName(String customerName);
	
	public TypedQuery<FundsDistribute> getFundsListByKeyAndName(String masterKey , String customerName);
	
	public TypedQuery<FundsDistribute> getFundsMasterKey(String masterKey);

}
