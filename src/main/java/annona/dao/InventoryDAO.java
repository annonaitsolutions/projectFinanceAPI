package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.Inventory;

public interface InventoryDAO {
	
	public void insertInventory(Inventory inventory);
	
	public Inventory getByInventoryId(Long id);
	
	public void updateInventory(Inventory inventory);
	
	public TypedQuery<Inventory> getInventoryByKeyList(String poKey, String customerName);
	
	public Collection<Inventory> getList();
	
	public TypedQuery<Inventory> getInventoryBycustomerName(String customerName);
	
	public TypedQuery<Inventory> getInventoryByGoods(String goods);
	/**
	 * Method to get Inventory list by warehouseName and customerName
	 * @param warehouseName
	 * @return
	 */
	List<Inventory> getInventoryListByWareHouseNCustomerName(String warehouseName, String customerName);
	
	/**
	 * Method to get Inventory list by warehouseName
	 * @param warehouseName
	 * @return
	 */
	List<Inventory> getInventoryListByWareHouse(String wareHouseName);


}
