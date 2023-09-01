package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.SellerBuyingCost;

public interface SellerBuyingCostDAO {

	
	public TypedQuery<SellerBuyingCost> getSellerBuyingCostList(String masterKey);
	
	public SellerBuyingCost createSellerBuyingCost(SellerBuyingCost buyerCost);
	
	public TypedQuery<SellerBuyingCost> getSellerBuyingCostbygoodsList(String masterKey,String goods );
	
	public SellerBuyingCost getSellerBuyingCostBuyerName(String buyerName , String masterKey);
}

