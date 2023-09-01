package annona.dao;

import java.util.Collection;

import javax.persistence.TypedQuery;

import annona.domain.BuyerDiffBankEvent;

public interface BuyerDiffBankEventDAO {
	
	 public void insertDiffBankEvents(BuyerDiffBankEvent buyerDiffBankEvent);
		
	 public TypedQuery<BuyerDiffBankEvent> findAllEvents(String masterKey);
	 
	 public BuyerDiffBankEvent findEvent(Long id);
	 
	 public void updateBuyerDiffBankEvent(BuyerDiffBankEvent buyerDiffBankEvent);
	 
	 public TypedQuery<BuyerDiffBankEvent> findDetails(String customer,String supplier,String masterKey,String goods);
	 
	 public TypedQuery<BuyerDiffBankEvent> checkMasterKeyBuyerDiffrent(String masterKey) ;
	
	 public Collection<BuyerDiffBankEvent> findAllupdateEvents();

	public TypedQuery<BuyerDiffBankEvent> findPoKey(String poKey);
	
    public TypedQuery<BuyerDiffBankEvent> findUpdateEvents(String name);
    
    public TypedQuery<BuyerDiffBankEvent> findCheckStatus();
	
	public TypedQuery<BuyerDiffBankEvent> findCheckStatus(String name);
	
	public TypedQuery<BuyerDiffBankEvent> findRejStatus(String name);
	
	public TypedQuery<BuyerDiffBankEvent> findStatus(String name);

}
