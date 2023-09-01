package annona.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.InvoiceStock;

public interface InvoiceStockDAO {
	
	public void insertInvoiceStock(InvoiceStock invoiceStock);
	
	public InvoiceStock getByInvoiceStockId(Long id);
	
	public void updateInvoiceStock(InvoiceStock invoiceStock);
	
	public TypedQuery<InvoiceStock> getInvoiceStockList(String username);
	
	public TypedQuery<InvoiceStock> getInvoiceStockListByCustHead(String username);
	
	public TypedQuery<InvoiceStock> getInvoiceStockGoodsName(String goodsName, String userName);
	
	public TypedQuery<InvoiceStock> getList();
	/**
	 * Method to get Invoice stock based on customerName and warehouseName
	 * @param userName
	 * @param warehouseName
	 * @return
	 */
	List<InvoiceStock> getInvoiceStockByCustomerNWarehouse(String userName, String warehouseName);
	/**
	 * Method to get Invoice Stock based on warehouseName 
	 * @param warehouseName
	 * @return
	 */
	List<InvoiceStock> getInvoiceStockByWarehouse(String warehouseName);

}
