package annona.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.Invoice;
import annona.domain.PurchaseOrder;
import annona.form.InventoryForm;

public interface InvoiceDAO {
	
	public Invoice getByInvoiceId(Long id);
	
	public void updateInvoice(Invoice invoice);
	
	public void insertInvoice(Invoice invoice);
	
	public TypedQuery<Invoice> getInvoiceListByMasterKey(String masterKey,String customerHeadName);
	
	public TypedQuery<Invoice> getInvoiceByStatus();

	public TypedQuery<Invoice> getList();
	
	public TypedQuery<Invoice> getPoListBycustomerName(String customerName);

    TypedQuery<Invoice> getInvoiceListForBuyer(String customerName);
	
	public TypedQuery<Invoice> getInvoiceListBycustomerName(String customerName);
	
	public TypedQuery<Invoice> getInvoiceListByMasterKeyAndName(String masterKey, String customerName);
	
	public TypedQuery<Invoice> getInvoiceListByBranchName(String customerName);
	
	public TypedQuery<Invoice> getInvoiceListForRates();
	
	public TypedQuery<Invoice> getInvoiceListForRatesApproval();
	
	public TypedQuery<Invoice> getInvoiceByMasterKeyList(String masterKey);
	
	public TypedQuery<Invoice> getInvoiceListForMnyRequest(String customerName);
	
	public TypedQuery<Invoice> getInvoiceListForSetRates();
	
	public TypedQuery<Invoice> getInvoiceListForAppMng();
	
	public TypedQuery<Invoice> getInvoiceListForClearence();
	
	public TypedQuery<Invoice> getInvoiceListForMnyRequestAndAccept(String customerName) ;

	TypedQuery<Invoice> getInvoiceListByBuyerName(String customerName);

	public TypedQuery<Invoice> getInvoiceClear();
	
	public TypedQuery<Invoice> getInvoiceListForClosing(String customerName);
	
	public List<Invoice> getInvoiceByCategoryAndDate(String goodsCategory, Date fromDate, Date toDate);
	
	public TypedQuery<Invoice> getInvoiceForEvents(String name);
	
	public TypedQuery<Invoice> getInvoiceData(String invoiceKey);
	
	public TypedQuery<Invoice> getInvoiceListForGoods(String customerName);
	
	public TypedQuery<Invoice> getInvoiceListBuyerForGoods(String customerName);
	
	public TypedQuery<Invoice> getInvoiceListBuyer(String customerName);

	public TypedQuery<Invoice> getInvoiceByInvoiceKey(String poKey);
	
	public TypedQuery<Invoice> getInoviceListBycustomerNameAndStatus(String customerName);
	
	public List<Invoice> getInvoiceReports(String userName,String goodsCategory, Date fromDate, Date toDate);
	
	public String getModeByQuantity(String userName, String goodsCategory, Date fromDate, Date toDate);
	
	public TypedQuery<Invoice> getInvoiceListForDisReports(String buyerName);

	String getModeByQuantity(String goodsCategory, Date fromDate, Date toDate);
	
	public List<InventoryForm> getInoviceListBycustomerName(String customerName);
}


