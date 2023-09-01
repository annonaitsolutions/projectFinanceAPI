package annona.dao;

import java.util.Collection;

import javax.persistence.TypedQuery;

import annona.domain.SellerSameBankEvent;

public interface SellerSameBankEventDAO {
	
	 public void insertSameBankEvents(SellerSameBankEvent sellerSameBankEvent);
	
	 public TypedQuery<SellerSameBankEvent> findAllEvents(String masterKey);
	 
	 public TypedQuery<SellerSameBankEvent> checkMasterKeySellerSame(String masterKey);
	 
	 public SellerSameBankEvent findEvent(Long id);
	 
	 
	 public void updateSellerSameBankEvent(SellerSameBankEvent sellerSameBankEvent);
	 
	 public TypedQuery<SellerSameBankEvent> findDetails(String customer,String buyer,String masterKey,String goods);

	 
	 public TypedQuery<SellerSameBankEvent> findByUsername(String username);

	 
	 public TypedQuery<SellerSameBankEvent> findMasterKey(String masterKey);

	 public Collection<SellerSameBankEvent> findAllupdateEvents();
	 
	    public TypedQuery<SellerSameBankEvent> findInvoiceKey(String invoiceKey);
		
		public TypedQuery<SellerSameBankEvent> findUpdateEvents(String name);
		
		public TypedQuery<SellerSameBankEvent> findCheckStatus();
		
		public TypedQuery<SellerSameBankEvent> findCheckStatus(String name);
		
		public TypedQuery<SellerSameBankEvent> findRejStatus(String name);
		
		public TypedQuery<SellerSameBankEvent> findStatus(String name);
}
