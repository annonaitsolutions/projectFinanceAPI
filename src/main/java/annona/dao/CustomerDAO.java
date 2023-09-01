package annona.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.TypedQuery;

import annona.domain.CustomerBranch;
import annona.domain.CustomerHead;
import annona.domain.CustomerSubsidiary;
import annona.form.CustomerHeadForm;

public interface CustomerDAO {

	public TypedQuery<CustomerHead> getApproved();

	public void insertCustomerBranch(CustomerBranch customerBranch);

	public CustomerBranch getByCustomerBranchId(Long id);

	public void updateSubsidiary(CustomerSubsidiary customer);

	public void insertCustomerSubsidiary(CustomerSubsidiary customerSubsidiary);

	public Collection<CustomerSubsidiary> findAllCustomerSubsidiary();

	public TypedQuery<CustomerSubsidiary> getCustSubsByIdAndStatus();

	public CustomerSubsidiary getByCustomerSubsidiaryId(Long id);

	public TypedQuery<CustomerSubsidiary> getByCustomerHeadKeyAndStatus(
			String customerHeadKey);

	public TypedQuery<CustomerBranch> getByCustomerKeyAndStatus(
			String customerHeadKey);

	public void updateBranch(CustomerBranch customer);
	
	public TypedQuery<CustomerBranch> getCustBranchByIdAndaStatus();
	
	public TypedQuery<CustomerSubsidiary> getCustSubsByIdAndaStatus();

	public TypedQuery<CustomerBranch> getCustBranchByIdAndStatus();

	public Collection<CustomerBranch> findAllCustomerBranch();
	
	public TypedQuery<CustomerHead> getCustByIdAndaStatus();
	
	public List<CustomerHeadForm> getCustByIdAndAStatusComp(Long companyId);

	public TypedQuery<CustomerHead> getList();

	public TypedQuery<CustomerHead> getByPending();

	public CustomerHead getRowById(Long id);

	public CustomerHead getByCustomerId(Long id);

	public TypedQuery<CustomerHead> getCustomerDetailsList(String username);

	public void updateUser(CustomerHead customer);

	public void insertCustomer(CustomerHead customerHead);

	public List<CustomerHeadForm> findAllCustomers();

	public List<CustomerHeadForm> getCustByIdAndStatus();
	
	public List<CustomerHeadForm> getCustByStatusCompIDAndIsForTrading(Long companyId, Boolean isForTrading);

	public CustomerHead findCustomers(Long id);

	public Collection<CustomerBranch> findAllCustomerBranchApprovedList();

	public TypedQuery<CustomerHead> findCustomer(String customer);
	
	public TypedQuery<CustomerBranch> getByCustomerHeadAndStatus(String customerName);
	
	public TypedQuery<CustomerSubsidiary> getByCustomerHeadAndStatusFoeSub(String customerName);
	
	public TypedQuery<CustomerBranch> getByCustomerNameForEmail(String customerName);
	
	public TypedQuery<CustomerSubsidiary> getBySubCustomerNameForEmail(String customerName);
	
	public TypedQuery<CustomerHead> getByHeadCustomerNameForEmail(String customerName);
	
	public TypedQuery<CustomerBranch> getBranchCustomerDetailsList(String username);
	
	public TypedQuery<CustomerSubsidiary> getSubsCustomerDetailsList(String username);
	
	public TypedQuery<CustomerHead> getAllCustomerHead();

}
