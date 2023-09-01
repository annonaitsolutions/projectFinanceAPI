package annona.form;

import java.util.List;

import org.springframework.stereotype.Component;
import annona.domain.BuyerDiffBankEvent;
import annona.domain.BuyerSameBankEvent;
import annona.domain.Invoice;
import annona.domain.PurchaseOrder;
import annona.domain.SellerDiffBankEvent;
import annona.domain.SellerSameBankEvent;

@Component
public class NewEventForm {

	private String customerBank;

	private String supplierName;

	private String supplierBank;

	private String buyerName;

	private String buyerBank;

	private String masterKey;

	private String goods;
	
	private String userName;

	private String poKey;
	
	private String invoiceKey;
	
	private List<PurchaseOrder> purchaseOrderList;
	
	private List<Invoice> invoiceList;
	
	private List<BuyerSameBankEvent> buyerSameBankEvents;
	
	private List<BuyerDiffBankEvent> buyerDiffBankEvents;
	
	private List<SellerSameBankEvent> sellerSameBankEvents;
	
	private List<SellerDiffBankEvent> sellerDiffBankEvents;
	
	
	
	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
	
	

	public List<BuyerSameBankEvent> getBuyerSameBankEvents() {
		return buyerSameBankEvents;
	}

	public void setBuyerSameBankEvents(List<BuyerSameBankEvent> buyerSameBankEvents) {
		this.buyerSameBankEvents = buyerSameBankEvents;
	}

	public List<BuyerDiffBankEvent> getBuyerDiffBankEvents() {
		return buyerDiffBankEvents;
	}

	public void setBuyerDiffBankEvents(List<BuyerDiffBankEvent> buyerDiffBankEvents) {
		this.buyerDiffBankEvents = buyerDiffBankEvents;
	}

	public List<SellerSameBankEvent> getSellerSameBankEvents() {
		return sellerSameBankEvents;
	}

	public void setSellerSameBankEvents(
			List<SellerSameBankEvent> sellerSameBankEvents) {
		this.sellerSameBankEvents = sellerSameBankEvents;
	}

	public List<SellerDiffBankEvent> getSellerDiffBankEvents() {
		return sellerDiffBankEvents;
	}

	public void setSellerDiffBankEvents(
			List<SellerDiffBankEvent> sellerDiffBankEvents) {
		this.sellerDiffBankEvents = sellerDiffBankEvents;
	}

	public String getPoKey() {
		return poKey;
	}

	public void setPoKey(String poKey) {
		this.poKey = poKey;
	}

	public String getInvoiceKey() {
		return invoiceKey;
	}

	public void setInvoiceKey(String invoiceKey) {
		this.invoiceKey = invoiceKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	

	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	public String getCustomerBank() {
		return customerBank;
	}

	public void setCustomerBank(String customerBank) {
		this.customerBank = customerBank;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierBank() {
		return supplierBank;
	}

	public void setSupplierBank(String supplierBank) {
		this.supplierBank = supplierBank;
	}

	public String getMasterKey() {
		return masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerBank() {
		return buyerBank;
	}

	public void setBuyerBank(String buyerBank) {
		this.buyerBank = buyerBank;
	}

	

}
