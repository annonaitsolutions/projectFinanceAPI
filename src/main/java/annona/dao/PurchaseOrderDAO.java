package annona.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.PurchaseOrder;

public interface PurchaseOrderDAO {
	
	public PurchaseOrder getByPurchaseId(Long id);
	
	public void updatePo(PurchaseOrder purchase);
	
	public void insertPo(PurchaseOrder purchase);
	
	public TypedQuery<PurchaseOrder> getPoListByMasterKey(String masterKey) ;
	
	public TypedQuery<PurchaseOrder> getPurchaseOrderByStatus();
	
	public TypedQuery<PurchaseOrder> getPurchaseOrderByvStatus(String customerName);
	
	public TypedQuery<PurchaseOrder> getPoListByPoKey(String poKey);
	
	public TypedQuery<PurchaseOrder> getPoListForUpload(String customerName);
	
	public TypedQuery<PurchaseOrder> getPoPaymentCleared(String customerName);

	public TypedQuery<PurchaseOrder> getPurchaseOrderByApproved();
	
	public TypedQuery<PurchaseOrder> getPoByMasterKeyList(String masterKey);
	
	public TypedQuery<PurchaseOrder> getPoListBySupplierNameAndClearence(String customerName);
	
	public TypedQuery<PurchaseOrder> getPoDocument(String customerName) ;
	
	public TypedQuery<PurchaseOrder> getList();
	
	public TypedQuery<PurchaseOrder> getPoListBycustomerName(String customerName);
	
	public TypedQuery<PurchaseOrder> getPoListBycustomerNameAndStatus(String customerName);
	
	public TypedQuery<PurchaseOrder> getPoListByMasterKeyAndName(String masterKey, String customerName);
	
	public TypedQuery<PurchaseOrder> getPoListByBranchName(String customerName);
	
	public TypedQuery<PurchaseOrder> getPoBycustomerNameAndStatus(String customerName);
	
	public TypedQuery<PurchaseOrder> getPoListForAppRatesApproval();
	
	public TypedQuery<PurchaseOrder> getPoListBySupplierName(String customerName);
	
	public TypedQuery<PurchaseOrder> getPoListForRates();
	
	public TypedQuery<PurchaseOrder> getPoListForRatesApproval();
	
	public TypedQuery<PurchaseOrder> getPoListByMasterKeyAndStatus(String masterKey) ;
	
	public TypedQuery<PurchaseOrder> getPoListForPayment(String customerName);

	public TypedQuery<PurchaseOrder> getPoListByDocument(String customerName);

	public TypedQuery<PurchaseOrder> getPoPaymentClearence();

	public TypedQuery<PurchaseOrder> getPoCancel();

	public TypedQuery<PurchaseOrder> getPoForEvents(String name);

	public List<PurchaseOrder> getPOByCategoryAndDate(String goodsCategory, Date fromDate, Date toDate);

	public TypedQuery<PurchaseOrder> getPoData(String poKey);
	
	public TypedQuery<PurchaseOrder> getPoListBySupplierNameAndGoodsStatus(String customerName);
	
	public TypedQuery<PurchaseOrder> getPoListGoodsReceive(String customerName);
	
	public List<PurchaseOrder> getPOByHeadCategoryAndDate(String userName,String goodsCategory, Date fromDate, Date toDate); 
	
	public TypedQuery<PurchaseOrder> getPurchaseOrderForRenewel(String customerName);
	
	/**
	 * Method to fetch mode(frequently occurring quantity)
	 */
	String getModeByQuantity(String userName, String goodsCategory, Date fromDate, Date toDate);
	
	public TypedQuery<PurchaseOrder> getPoMultiplePaymentClearance();
	
	public TypedQuery<PurchaseOrder> getPoMultiplePaymentByTransacrionId(String transactionId);

	String getModeByQuantity(String goodsCategory, Date fromDate, Date toDate);
	
	public List<String> getPurchaseGoodsListByCustomer(String  customerName);
	
	

	

	


}
