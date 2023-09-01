package annona.dao;

import java.util.Date;

import javax.persistence.TypedQuery;

import annona.domain.RestrictedLicense;

public interface RestrictedLicenseDAO {
	
	public TypedQuery<RestrictedLicense> getList();
	
	public void updateRestrictedLicense(RestrictedLicense license);
	
	public  RestrictedLicense getByRestrictedLicenseId(Long id);
	
	public void createRestrictedLicense(RestrictedLicense license);
	
	public TypedQuery<RestrictedLicense> getRestrictedLicenseOnCustList(String customer);
	
	public TypedQuery<RestrictedLicense> getInsByStatus();
	
	public TypedQuery<RestrictedLicense> getRestrictedGoodsList(String customer,String category,Date purchaseDate,Float quantity);
	

}
