package annona.dao;

import javax.persistence.TypedQuery;

import annona.domain.DisputeReports;

public interface DisputeReportsDAO {
	
	public TypedQuery<DisputeReports> getList();
	
	public void updateDisputeReports(DisputeReports dispute);
	
	public  DisputeReports getByDisputeReportsId(Long id);
	
	public void createDisputeReports(DisputeReports dispute);
	
	public TypedQuery<DisputeReports> getDisputeReportsList(String disputekey);
	
	public TypedQuery<DisputeReports> getDisputeReportsOnCustList(String customerName);
	
	public TypedQuery<DisputeReports> getDisputeReportsOnCustAndAcceptList(String supplierName);
	
	public TypedQuery<DisputeReports> getDisputeReportsVendorList(String supplierName);
	
	public TypedQuery<DisputeReports> getInsByStatus();

}
