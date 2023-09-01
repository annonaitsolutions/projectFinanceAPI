package annona.dao;



import java.util.Collection;

import javax.persistence.TypedQuery;

import annona.domain.Notification;

public interface NotificationDAO {

	public void insertNotification(Notification notification);
	
	public TypedQuery<Notification> findByCustomerName(String name);
	
	
	public Collection<Notification> getList();

	
}
