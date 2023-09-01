package annona.trade.dao;

import java.util.Collection;

import annona.domain.Transaction;
import annona.trade.domain.TTransaction;

public interface TTransactionDAO {

	public void insertTransaction(TTransaction transaction);
	
	public Collection<TTransaction> getList();

}
