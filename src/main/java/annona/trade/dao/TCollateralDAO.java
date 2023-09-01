package annona.trade.dao;

import javax.persistence.TypedQuery;

import annona.domain.Collateral;
import annona.trade.domain.TCollateral;

public interface TCollateralDAO {
	
	public void createUser(TCollateral collateral);
	
	public TypedQuery<TCollateral> getCollateralBYMAsterKey(String masterKey);

}
