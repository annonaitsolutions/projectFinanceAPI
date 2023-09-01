package annona.dao;



import javax.persistence.TypedQuery;

import annona.domain.WareHouse;

public interface WareHouseDAO {
	
	public WareHouse createMasterPlan(WareHouse wareHouse);
	
	public TypedQuery<WareHouse> getByPending(String customerName);
	
	public WareHouse getByWareHouseId(Long id);
	
	public TypedQuery<WareHouse> getWareHouseList(String wareHouseName);
	
	public TypedQuery<WareHouse> getWareHouseFullList(String username);
	
	public void updateWareHouse(WareHouse wareHouse);
	
	public TypedQuery<WareHouse> getByAppPending();
	
	public TypedQuery<WareHouse> getList();
	
	public TypedQuery<WareHouse> getWareHouseandStatusList(String username);
	
	public TypedQuery<WareHouse> getWareHouseNameList(String wareHouseName);


}
