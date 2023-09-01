package annona.dao;

import java.util.Collection;

import javax.persistence.TypedQuery;

import annona.domain.SellerDiffBankEvent;

public interface SellerDiffBankEventDAO {
	
	 public void insertDiffBankEvents(SellerDiffBankEvent sellerDiffBankEvent);
	
	 public TypedQuery<SellerDiffBankEvent> findAllEvents(String masterKey);
	 
	 public TypedQuery<SellerDiffBankEvent> checkMasterKeySellerDiffrent(String masterKey);
	 
	 public SellerDiffBankEvent findEvent(Long id);
	 
	 public void updateSellerDiffBankEvent(SellerDiffBankEvent sellerDiffBankEvent);
	 
	 public TypedQuery<SellerDiffBankEvent> findDetails(String customer,String buyer,String masterKey,String goods);
	 
	 public TypedQuery<SellerDiffBankEvent> findByUsername(String username);

	 public TypedQuery<SellerDiffBankEvent> findMasterKey(String masterKey);
	 
	 public Collection<SellerDiffBankEvent> findAllupdateEvents();
	 
	   public TypedQuery<SellerDiffBankEvent> findInvoiceKey(String invoiceKey);
		
	    public TypedQuery<SellerDiffBankEvent> findUpdateEvents(String name);
	    
	    public TypedQuery<SellerDiffBankEvent> findCheckStatus();
		
		public TypedQuery<SellerDiffBankEvent> findCheckStatus(String name);
		
		public TypedQuery<SellerDiffBankEvent> findRejStatus(String name);
		
		public TypedQuery<SellerDiffBankEvent> findStatus(String name);

}
