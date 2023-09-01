package annona.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import annona.dao.EndUserDAO;
import annona.dao.FullAmountDAO;
import annona.dao.HalfYearlyDAO;
import annona.dao.QuarterlyDAO;
import annona.dao.RepaymentDAO;
import annona.domain.EndUser;
import annona.domain.FullAmount;
import annona.domain.HalfYearly;
import annona.domain.Quarterly;
import annona.domain.Repayment;
import annona.services.DateService;
import annona.trade.dao.TFullAmountDAO;
import annona.trade.dao.THalfYearlyDAO;
import annona.trade.dao.TQuarterlyDAO;
import annona.trade.dao.TRepaymentDAO;
import annona.trade.domain.TFullAmount;
import annona.trade.domain.THalfYearly;
import annona.trade.domain.TQuarterly;
import annona.trade.domain.TRepayment;
import annona.utility.Constants;


public class NotificationsScheduler {
	
	@Autowired
	EndUserDAO endUserDAO;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	RepaymentDAO repaymentDAO;
	
	@Autowired
	QuarterlyDAO quarterlyDAO;
	
	@Autowired
	HalfYearlyDAO halfYearlyDAO;
	
	@Autowired
	FullAmountDAO fullAmountDAO;
	
	@Autowired
	TRepaymentDAO tRepaymentDAO;
	
	@Autowired
	TQuarterlyDAO tQuarterlyDAO;
	
	@Autowired
	THalfYearlyDAO tHalfYearlyDAO;
	
	@Autowired
	TFullAmountDAO tFullAmountDAO;

	@Autowired
	DateService dateService;
	
	
	/**
	 *Method to send notifications to Users two days prior to account 
	 *expiry
	 **/	
    public void accExpiryNotifications() {
      
        List<EndUser> usersList = endUserDAO.getUsersExceptAdmin();
        Date notificationDate = DateService.currentDateTwoDaysAhead();
        for(EndUser user : usersList) { 
        	Date expiryDate = DateService.getDate(user.getAccExpiryDate());
        	if(notificationDate.equals(expiryDate)){
        		SimpleMailMessage email = new SimpleMailMessage();
        		email.setTo(user.getEmail());
        		email.setSubject("Account Expiry Notification!!!!");
        		email.setText("Hello "
        				+ user.getUserName()
        				+ ",\n\n Your account will expire in two days. "        				
        				+ "\n\n Please contact admin to renew your account"
        				+ "\n\n\nRegards,\nBank");
        		
        		mailSender.send(email);
        	}
        }
    }
    
    /**
     * Method to send notifications two days prior to repayment
     */
	public void repaymentNotifications() {
		System.out.println("--------- repaymentNotifications() started ");
		List<Repayment> repaymentList = repaymentDAO.getRepaymentListByAccept(Constants.YES);
		Date todayDate=dateService.getCurrentSavedLoginDate();
		Date notificationDate = DateService.addDays(todayDate,1);
		Date notificationDate2 = DateService.addDays(todayDate,2);


		for(Repayment repayment : repaymentList) {
			switch (repayment.getPayOption()) {
				case Constants.QUARTERLY:
					List<Quarterly> quarterlyList = quarterlyDAO.getQuarterlyListByCustomer(repayment.getCustomer());
					for(Quarterly quarterly : quarterlyList) {
						String[] loandates = quarterly.getLoanDates().split(",");
						for(String date: loandates) {
							String pattern = "E MMM dd HH:mm:ss z yyyy";
							SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
							dateFormat.setTimeZone(TimeZone.getTimeZone("IST")); // Set the time zone to IST

							try {
								Date dateO = dateFormat.parse(date);
								if (notificationDate.equals(DateService.getDate(dateO)) || notificationDate2.equals(DateService.getDate(dateO))|| notificationDate2.equals(todayDate)) {
									sendRepaymentNotification(quarterly.getCustomer(), quarterly.getCustomerEmail(),dateO);
								}
							}catch(Exception e){
								e.printStackTrace();
							}

						}
					}
					break;

				case Constants.HALFYEARLY:
					List<HalfYearly> halfYearlyList = halfYearlyDAO.getHalfYearlyListByCustomer(repayment.getCustomer());
					for(HalfYearly halfYearly : halfYearlyList) {
						String[] loandates = halfYearly.getLoanDates().split(",");
						for(String date: loandates) {
							String pattern = "E MMM dd HH:mm:ss z yyyy";
							SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
							dateFormat.setTimeZone(TimeZone.getTimeZone("IST")); // Set the time zone to IST

							try {
								Date dateO = dateFormat.parse(date);
								if (notificationDate.equals(DateService.getDate(dateO)) || notificationDate2.equals(DateService.getDate(dateO))|| notificationDate2.equals(todayDate)) {
									sendRepaymentNotification(halfYearly.getCustomer(), halfYearly.getCustomerEmail(),dateO);
								}
							}catch(Exception e){
								e.printStackTrace();
							}

						}
					}
					break;

				case Constants.FULL:
					List<FullAmount> fullYearList = fullAmountDAO.getFullAmountListByCustomer(repayment.getCustomer());
					for(FullAmount fullAmount : fullYearList) {
						if(notificationDate.equals(DateService.getDate(fullAmount.getLoanDate()))) {

							sendRepaymentNotification(fullAmount.getCustomer(),fullAmount.getCustomerEmail(),null);
						}
					}
					break;

				default:
					break;
			}
		}
		System.out.println("--------- repaymentNotifications() ended ");
	}
    
    
    /**
     * Method to send notifications two days prior to trade repayment
     */
    public void tradeRepaymentNotifications() {
    	List<TRepayment> tRepaymentList = tRepaymentDAO.getTRepaymentListByAccept(Constants.YES);
    	Date notificationDate = DateService.currentDateTwoDaysAhead();
    	for(TRepayment tRepayment : tRepaymentList) {
    		switch (tRepayment.getPayOption()) {
			case Constants.QUARTERLY:
						List<TQuarterly> tQuarterlyList = tQuarterlyDAO.getTQuarterlyListByCustomer(tRepayment.getCustomer());
						for(TQuarterly tQuarterly : tQuarterlyList) {
							if(notificationDate.equals(DateService.getDate(tQuarterly.getLoanDate()))  || 
							   notificationDate.equals(DateService.getDate(tQuarterly.getLoanDate1())) ||
							   notificationDate.equals(DateService.getDate(tQuarterly.getLoanDate2())) ||
							   notificationDate.equals(DateService.getDate(tQuarterly.getLoanDate3()))) {
								
								sendRepaymentNotification(tQuarterly.getCustomer(),tQuarterly.getCustomerEmail(),null);
							}
						}
						break;
				
			case Constants.HALFYEARLY:
						List<THalfYearly> tHalfYearlyList = tHalfYearlyDAO.getTHalfYearlyListByCustomer(tRepayment.getCustomer());
						for(THalfYearly tHalfYearly : tHalfYearlyList) {
							if(notificationDate.equals(DateService.getDate(tHalfYearly.getLoanDate()))  || 
							   notificationDate.equals(DateService.getDate(tHalfYearly.getLoanDate1()))  ) {
								
								sendRepaymentNotification(tHalfYearly.getCustomer(),tHalfYearly.getCustomerEmail(),null);
							}
						}
						break;

			case Constants.FULLYEARLY:
						List<TFullAmount> tFullYearList = tFullAmountDAO.getTFullAmountListByCustomer(tRepayment.getCustomer());
						for(TFullAmount tFullAmount : tFullYearList) {
							if(notificationDate.equals(DateService.getDate(tFullAmount.getLoanDate()))) {
								
								sendRepaymentNotification(tFullAmount.getCustomer(),tFullAmount.getCustomerEmail(),null);
							}
						}
						break;
				
			default:
				break;
			}
    	}
    }
    
    /**
     * Method to send notification mail two days prior to repayment 
     */
    public void sendRepaymentNotification(String username, String emailId, Date date) {

    	SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(emailId);
		email.setSubject("Repayment Notification!!!!");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(date);
		if(date!=null) {
			email.setText("Hello "
					+ username
					+ ",\n\n Your repayment due is on " + dateString
					+ "\n\n Please make the payment"
					+ "\n\n\nRegards,\nBank");
		}else{
			email.setText("Hello "
					+ username
					+ ",\n\n Your repayment due is in two days"
					+ "\n\n Please make the payment"
					+ "\n\n\nRegards,\nBank");
		}
		
		mailSender.send(email);
    }

}
