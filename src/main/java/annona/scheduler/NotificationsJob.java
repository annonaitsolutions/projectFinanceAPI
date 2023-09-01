package annona.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class NotificationsJob extends QuartzJobBean {

	private NotificationsScheduler notificationsScheduler;

	public void setNotificationsScheduler(NotificationsScheduler notificationsScheduler) {
		this.notificationsScheduler = notificationsScheduler;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {

		System.out.println("notificaiton Job executeExternal()");

		try {
			notificationsScheduler.accExpiryNotifications();                     //Method for Account expiry notifications
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			notificationsScheduler.repaymentNotifications();                    //Method for Repayment notifications
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
//			notificationsScheduler.tradeRepaymentNotifications();                //Method for Trade repayment notifications
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
