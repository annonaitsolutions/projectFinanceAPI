package annona.dao;

import java.util.Collection;

import javax.persistence.TypedQuery;

import annona.domain.BuyerSameBankEvent;

public interface BuyerSameBankEventDAO {

	public void insertSameBankEvents(BuyerSameBankEvent buyerSameBankEvent);

	public TypedQuery<BuyerSameBankEvent> findAllEvents(String masterKey);

	public BuyerSameBankEvent findEvent(Long id);

	public void updateBuyerSameBankEvent(BuyerSameBankEvent buyerSameBankEvent);

	public TypedQuery<BuyerSameBankEvent> findDetails(String customer,
			String supplier, String masterKey, String goods);


	public Collection<BuyerSameBankEvent> findAllupdateEvents();

	public TypedQuery<BuyerSameBankEvent> findPoKey(String poKey);
	
	public TypedQuery<BuyerSameBankEvent> findUpdateEvents(String name);
	
	public TypedQuery<BuyerSameBankEvent> findCheckStatus();
	
	public TypedQuery<BuyerSameBankEvent> findCheckStatus(String name);
	
	public TypedQuery<BuyerSameBankEvent> findRejStatus(String name);
	
	public TypedQuery<BuyerSameBankEvent> findStatus(String name);
	
	public TypedQuery<BuyerSameBankEvent> checkMasterKeyBuyerSame(String masterKey);

}
