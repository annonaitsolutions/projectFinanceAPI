package annona.domain;

import java.util.List;

import javax.persistence.TypedQuery;

import annona.form.SalesOrderForm;

public interface SalesOrderDAO {
	
	public void insertSalesOrder (SalesOrder  salesOrder );
	
	public TypedQuery<SalesOrder> getSalesOrderByStatus();
	
	public SalesOrder getBySalesOrderId(Long id);
	
	public void updateSalesOrder(SalesOrder salesOrder);
	
	public TypedQuery<SalesOrder> getSalesOrderByCustomer(String customer);

    TypedQuery<SalesOrder> getSalesOrderByCustomerHeadName(String customerHead);
    
    public List<SalesOrderForm>  getSalesOrderByCustomerAndMasterKey(String customerHead );
    
    public TypedQuery<SalesOrder> getList();
}
