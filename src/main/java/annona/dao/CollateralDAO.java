package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.Collateral;

public interface CollateralDAO {
	
	public void createUser(Collateral collateral);
	
	public TypedQuery<Collateral> getCollateralBYMAsterKey(String masterKey);

}
