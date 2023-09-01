package annona.trade.dao;



import java.util.Collection;

import javax.persistence.TypedQuery;

import annona.trade.domain.TradeNotification;





public interface TradeNotificationDAO {

	public void insertNotification(TradeNotification tradeNotification);
	
	public TypedQuery<TradeNotification> findByCustomerName(String name);
	
	
	public Collection<TradeNotification> getList();

	
}
