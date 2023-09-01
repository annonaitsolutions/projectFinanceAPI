package annona.dao;

import java.util.Collection;
import java.util.List;

import annona.domain.Company;

public interface CompanyDAO {

	public Company getByCompanyId(Long id);
	
	public List<Company> getCompanyList();
	
	public Company getByCompanyName(String companyName);

	public void insertCompany(Company company);

	public Collection<Company> findAllCompany();

	public List<Company> getCompanyByStatuss(String status);

	void updateCompany(Company company);

	List<Company> getCompanyByStatusAndIsForTrading(String status, Boolean isForTrading);

}
