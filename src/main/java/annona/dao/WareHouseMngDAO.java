package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.WareHouse;
import annona.domain.WareHouseMng;

public interface WareHouseMngDAO {
	
	public TypedQuery<WareHouseMng> getList() ;
	
	public TypedQuery<WareHouseMng> getByPending();
	
	public TypedQuery<WareHouseMng> getByPending(String customerName);
	
	public TypedQuery<WareHouseMng> getCustByIdAndStatus();
	
	public WareHouseMng getByWareHouseMngId(Long id);
	
	public void updateUser(WareHouseMng mng);
	
	public void insertWareHouseMng(WareHouseMng mng);
	
	public TypedQuery<WareHouseMng> getWareHouseMngList(String username);
	
	public TypedQuery<WareHouseMng> getWareHouseMngFullList(String username);
	
	public TypedQuery<WareHouseMng> getClientAdminByIdAndStatus();

	public TypedQuery<WareHouseMng> getBankAppByIdAndStatus();
}
