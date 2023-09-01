package annona.dao;

import java.util.Collection;

import annona.domain.Transaction;

public interface TransactionDAO {

	public void insertTransaction(Transaction transaction);
	
	public Collection<Transaction> getList();

}
