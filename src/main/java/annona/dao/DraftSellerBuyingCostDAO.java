package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.DraftSellerBuyingCost;

public interface DraftSellerBuyingCostDAO {

	public TypedQuery<DraftSellerBuyingCost> getDraftSellerBuyingCostList(String masterKey);

	public DraftSellerBuyingCost createDraftSellerBuyingCost(DraftSellerBuyingCost buyerCost);

	public TypedQuery<DraftSellerBuyingCost> getDraftSellerBuyingCostbygoodsList(String masterKey, String goods);

	public TypedQuery<DraftSellerBuyingCost> getDraftSellerBuyingCostByMasterKey(String masterKey);
}
