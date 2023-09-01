package annona.dao;

import java.util.Collection;

import javax.persistence.TypedQuery;

import annona.domain.BankDetails;
import annona.domain.CustomerBankDetails;

public interface CustomerBankDetailsDAO {
	public void insertCustBankDetails(CustomerBankDetails customerBankDetails);

	public Collection<CustomerBankDetails> getList();

	public void update(CustomerBankDetails customerBankDetails);

	public CustomerBankDetails findId(Long id);
	
	TypedQuery<CustomerBankDetails> getBankDetails();

}
