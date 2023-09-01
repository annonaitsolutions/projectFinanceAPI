package annona.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.PoStock;

public interface PoStockDAO {
	
	public void insertPoStock(PoStock poStock);
	
	public PoStock getByPoStockId(Long id);
	
	public void updatePoStock(PoStock poStock);
	
	public TypedQuery<PoStock> getPoStockList(String username);
	
	public TypedQuery<PoStock> getPoStockGoodsName(String goodsName, String userName);
	
	public TypedQuery<PoStock> getList();
	/**
	 * Method to get PO stock list based on customer and warehouse
	 * @param userName
	 * @param warehouseName
	 * @return
	 */
	List<PoStock> getPoStockByCustomerNWarehouse(String userName, String warehouseName);
	
	/**
	 * Method to get POStock by warehouse Name 
	 */
	List<PoStock> getPoStockByWarehouse(String warehouseName);

}
