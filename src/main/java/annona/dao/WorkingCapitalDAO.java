package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.WorkingCapital;

public interface WorkingCapitalDAO {
	
	public TypedQuery<WorkingCapital> getWCBYMAsterKey(String masterKey);
	
	public WorkingCapital createMasterPlan(WorkingCapital wc);

}
