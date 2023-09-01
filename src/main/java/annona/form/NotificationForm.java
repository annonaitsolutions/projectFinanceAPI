package annona.form;

import org.springframework.stereotype.Component;

@Component
public class NotificationForm {
	
	private Long id;

	private String notificationDate;

	private String customerName;

	private String notificationDescription;

	private String notificationType;
	
	private  String notificationAcc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getNotificationDescription() {
		return notificationDescription;
	}

	public void setNotificationDescription(String notificationDescription) {
		this.notificationDescription = notificationDescription;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getNotificationAcc() {
		return notificationAcc;
	}

	public void setNotificationAcc(String notificationAcc) {
		this.notificationAcc = notificationAcc;
	}


}
