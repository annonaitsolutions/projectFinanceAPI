package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;




import annona.domain.InvoiceInventory;



public interface InvoiceInventoryDAO {
	
	public void insertInvoiceInventory(InvoiceInventory invoiceInventory);
	
	public void updateInvoiceInventory(InvoiceInventory invoiceInventory);
	
	public InvoiceInventory getByInvoiceInventoryId(Long id);
	
	public TypedQuery<InvoiceInventory> getInventoryByKeyList(String poKey, String customerName);
	
	public TypedQuery<InvoiceInventory> getInvoiceInventoryBycustomerName(String customerName);

	public Collection<InvoiceInventory> getList();
	/**
	 * Method  to fetch InvoiceInventory list based on customer and warehouseName
	 * @param customerName
	 * @param warehouseName
	 * @return
	 */
	List<InvoiceInventory> getInvoiceInventoryByCustomerNWarehouse(String customerName, String warehouseName);
	/**
	 * Method to get Invoice inventory by warehouse
	 * @param warehouseName
	 * @return
	 */
	List<InvoiceInventory> getInvoiceInventoryByWarehouse(String warehouseName);

}
