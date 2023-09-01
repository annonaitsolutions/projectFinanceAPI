package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.Supplier;
import annona.form.SupplierForm;

public interface SupplierDAO {

	public void createUser(Supplier supplier);

	public Collection<Supplier> getList();

	public Supplier findId(Long id);

	public void update(Supplier supplier);

	public TypedQuery<Supplier> findByName(String name);

	public TypedQuery<Supplier> getByPending();
	
	public TypedQuery<Supplier> getByCustPending(String customerName);
	
	public Supplier findSupplierByNameAndCompId(String buyerName , Long companyId );

	public TypedQuery<Supplier> getBySupplier();
	
	public TypedQuery<Supplier> findByCustomerHeadName(String name);
	
	public TypedQuery<Supplier> findByNameAndStatus(String name) ;
	
	public List<SupplierForm> getSupplierForBusinessPlan( String masterKey);
	
	public List<Supplier> getSupplierListByCompanyId(Long id);
	
	public TypedQuery<Supplier> findByCustomerHeadNameAndStatus(String customerHeadName);
	
	public TypedQuery<Supplier> getForApproval();
	
	public TypedQuery<Supplier> getBySupplierPending(String customerName);
	
	public TypedQuery<Supplier> getForcApproval();
	
	public TypedQuery<Supplier> getForRating();
	
	/**
	 * Method to find Supplier by SupplierName
	 * @param supplierName
	 * @return
	 */
	Supplier findBySupplierName(String supplierName);

	TypedQuery<Supplier> getForcApprovalByCompany(Long companyId);
	
	

}
