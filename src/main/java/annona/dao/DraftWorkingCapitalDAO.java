package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.DraftWorkingCapital;
import annona.domain.WorkingCapital;

public interface DraftWorkingCapitalDAO {

	TypedQuery<DraftWorkingCapital> getDWCBYMAsterKey(String masterKey);

	DraftWorkingCapital createDraftMasterPlan(DraftWorkingCapital wc);
	
	

}
