package annona.dao;

import java.util.Collection;

import javax.persistence.TypedQuery;

import annona.domain.BankDetails;

    public interface BankDetailsDAO {

	public void insertBankDetails(BankDetails bankDetails);

	public Collection<BankDetails> getList();

	public void update(BankDetails bankDetails);

	public BankDetails findId(Long id);
	
	TypedQuery<BankDetails> getBankDetails();
}
