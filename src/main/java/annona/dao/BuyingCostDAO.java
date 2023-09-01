package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.BuyingCost;

import java.util.List;

public interface BuyingCostDAO {

	public BuyingCost createMasterPlan(BuyingCost buyerCost);
	
	public TypedQuery<BuyingCost> getBuyingCostBYMAsterKey(String masterKey);
	
	public BuyingCost getBuyingCostByCustomerName(String supplierName , String masterKey);

    public List<String> getMaterials(String supplierName);

    TypedQuery<BuyingCost> getById(Long id);
}
