package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.NewBuyer;
import annona.domain.Supplier;
import annona.form.NewBuyerForm;

public interface NewBuyerDAO {

	public void createUser(NewBuyer newBuyer);

	public Collection<NewBuyer> getList();

	public NewBuyer findId(Long id);
	
	public List<NewBuyer> getbuyerListByCompanyId(Long companyId);

	public void update(NewBuyer newBuyer);

	public TypedQuery<NewBuyer> findByName(String name);

	public TypedQuery<NewBuyer> getByPending();
	
	public TypedQuery<NewBuyer> getByCustAppPending(String customerName);

	public TypedQuery<NewBuyer> getByBuyer();
	
	public TypedQuery<NewBuyer> findByCustomerHeadName(String name);
	
	public TypedQuery<NewBuyer> findByNameAndStatus(String name);
	
	public TypedQuery<NewBuyer> findByCustomerHeadNameAndStatus(String customerHeadName);
	
	public TypedQuery<NewBuyer> getByPending(String customerName);
	
	public TypedQuery<NewBuyer> getForApproval();
	
	public TypedQuery<NewBuyer> getForcApproval();
	
	public NewBuyer findBuyerByNameAndCompId(String buyerName , Long compId );
	
	public List<NewBuyerForm> getBuyerForBusinessPlan(String masterKey);
	
	public TypedQuery<NewBuyer> getForRating();
	/**
	 * Method to find Buyer by buyerName
	 * @param buyerName
	 * @return
	 */
	NewBuyer findByBuyerName(String buyerName);

	public TypedQuery<NewBuyer> getForcApprovalByCompanyId(Long companyId);

}
