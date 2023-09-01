package annona.trade.dao;

import java.util.Collection;

import javax.persistence.TypedQuery;
import annona.trade.domain.BuyerTrade;

public interface BuyerTradeDAO {

	public void createUser(BuyerTrade buyerTrade);

	public Collection<BuyerTrade> getList();

	public BuyerTrade findId(Long id);

	public void update(BuyerTrade buyerTrade);

	public TypedQuery<BuyerTrade> findByName(String name);

	public TypedQuery<BuyerTrade> getByPending();

	public TypedQuery<BuyerTrade> getByBuyer();
	
	public TypedQuery<BuyerTrade> findByCustomerHeadName(String customerHeadName);
	
	public TypedQuery<BuyerTrade> findByNameAndStatus(String name);
	
	public TypedQuery<BuyerTrade> findByCustomerHeadNameAndStatus(String customerHeadName);
	
	public TypedQuery<BuyerTrade> getByPending(String customerName);
	
	public TypedQuery<BuyerTrade> getForApproval();
	
	public TypedQuery<BuyerTrade> getForcApproval();

}
