package annona.trade.dao;

import javax.persistence.TypedQuery;

import annona.domain.BuyingCost;
import annona.trade.domain.TBuyingCost;

public interface TBuyingCostDAO {

	public TBuyingCost createMasterPlan(TBuyingCost buyerCost);
	
	public TypedQuery<TBuyingCost> getBuyingCostBYMAsterKey(String masterKey);

}
