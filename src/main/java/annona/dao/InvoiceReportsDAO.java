package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.DisputeReports;
import annona.domain.InvoiceReports;

public interface InvoiceReportsDAO {

	public TypedQuery<InvoiceReports> getList();
	
	public void updateInvoiceReports(InvoiceReports invoice);
	
	public  InvoiceReports getByInvoiceReportsId(Long id);
	
	public void createInvoiceReports(InvoiceReports invoice);
	
	public TypedQuery<InvoiceReports> getInvoiceReportsList(String invoicekey);
	
	public TypedQuery<InvoiceReports> getInvoiceReportsOnCustList(String buyerName);
	
	public TypedQuery<InvoiceReports> getInsByStatus();
	
	public TypedQuery<InvoiceReports> getInvoiceReportsOnCustAndAcceptList(String customerName);
	
	public TypedQuery<InvoiceReports> getInvoiceReportsVendorList(String customerName);
}
