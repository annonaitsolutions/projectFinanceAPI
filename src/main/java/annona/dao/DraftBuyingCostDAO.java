package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.DraftBuyingCost;

public interface DraftBuyingCostDAO {

	TypedQuery<DraftBuyingCost> getDraftBuyingCostBYMAsterKey(String masterKey);

	DraftBuyingCost createDraftMasterPlan(DraftBuyingCost draftBuyerCost);

}
