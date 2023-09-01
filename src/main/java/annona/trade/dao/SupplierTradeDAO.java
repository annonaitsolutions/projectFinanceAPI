package annona.trade.dao;

import java.util.Collection;

import javax.persistence.TypedQuery;

import annona.trade.domain.SupplierTrade;



public interface SupplierTradeDAO {

	public void createUser(SupplierTrade supplierTrade);

	public Collection<SupplierTrade> getList();

	public SupplierTrade findId(Long id);

	public void update(SupplierTrade supplierTrade);

	public TypedQuery<SupplierTrade> findByName(String name);

	public TypedQuery<SupplierTrade> getByPending();

	public TypedQuery<SupplierTrade> getBySupplier();

	public TypedQuery<SupplierTrade> findByCustomerHeadName(String customerHeadName);

	public TypedQuery<SupplierTrade> findByNameAndStatus(String name);

	public TypedQuery<SupplierTrade> findByCustomerHeadNameAndStatus(String customerHeadName);

	public TypedQuery<SupplierTrade> getForApproval();

	public TypedQuery<SupplierTrade> getBySupplierPending(String customerName);

	public TypedQuery<SupplierTrade> getForcApproval();

}
