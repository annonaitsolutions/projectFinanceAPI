package annona.services;

import annona.dao.LoginDateDao;
import annona.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
/**
 * Class to manipulate Dates
 * @author goutame
 *
 */
@Service
public class DateService {

	@Autowired
	public LoginDateDao loginDateDao;

	public static Date loginDate;


	/**
	 * Method to generate Date for Password expiry.
	 * Add two months to current Date.
	 * @return Date
	 */
	public Date generatePasswordExpiryDate() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.MONTH,2);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);


		return currentDate.getTime();
	}

	/**
	 * Method to generate Current Date without time
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar currentDate = Calendar.getInstance();

		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);

		return currentDate.getTime();
	}

	/**
	 * Method to add two days to current date
	 */
	public static Date currentDateTwoDaysAhead() {
		Calendar currentDate = Calendar.getInstance();
		currentDate.add(Calendar.DAY_OF_MONTH,2);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.MILLISECOND, 0);

		return currentDate.getTime();
	}

	/**
	 * Method to get only date truncating time
	 */
	public static Date getDate(Date givenDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(givenDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * Method to get no of days between two dates
	 */
	public static Integer getDaysBetweenTwoDates(Date fromDate, Date toDate) {

		Integer days = null;
		if(fromDate != null && toDate != null) {
			fromDate = DateService.getDate(fromDate);
			toDate = DateService.getDate(toDate);
			long diff = toDate.getTime() - fromDate.getTime();
			days = (int)(diff/(1000 * 60 * 60 * 24));
		}
		return days;
	}

	/**
 * 	Method to get date details of buyer same bank event
 */
 public static Date getBuyerSameEventDate(BuyerSameBankEvent buyerSameBankEvent,int i) {
	 Date date = null;
	 switch(i) {
		 case 1: date = buyerSameBankEvent.getDate1();
			 break;
		 case 2: date = buyerSameBankEvent.getDate2();
             break;
		 case 3: date = buyerSameBankEvent.getDate3();
             break;
		 case 4: date = buyerSameBankEvent.getDate4();
             break;
		 case 5: date = buyerSameBankEvent.getDate5();
             break;
		 case 6: date = buyerSameBankEvent.getDate6();
             break;
		 case 7: date = buyerSameBankEvent.getDate7();
             break;
		 case 8: date = buyerSameBankEvent.getDate8();
             break;
		 case 9: date = buyerSameBankEvent.getDate9();
             break;
		 case 10: date = buyerSameBankEvent.getDate10();
             break;
		 case 11: date = buyerSameBankEvent.getDate11();
             break;
	 }
	 return date;
 }


/**
 * 	Method to get date details of buyer Different bank event
 */
 public static Date getBuyerDiffEventDate(BuyerDiffBankEvent buyerDiffBankEvent,int i) {
	 Date date = null;
	 switch(i) {
		 case 1: date = buyerDiffBankEvent.getDate1();
			 break;
		 case 2: date = buyerDiffBankEvent.getDate2();
             break;
		 case 3: date = buyerDiffBankEvent.getDate3();
             break;
		 case 4: date = buyerDiffBankEvent.getDate4();
             break;
		 case 5: date = buyerDiffBankEvent.getDate5();
             break;
		 case 6: date = buyerDiffBankEvent.getDate6();
             break;
		 case 7: date = buyerDiffBankEvent.getDate7();
             break;
		 case 8: date = buyerDiffBankEvent.getDate8();
             break;
		 case 9: date = buyerDiffBankEvent.getDate9();
             break;
		 case 10: date = buyerDiffBankEvent.getDate10();
             break;
		 case 11: date = buyerDiffBankEvent.getDate11();
             break;
		 case 12: date = buyerDiffBankEvent.getDate12();
             break;
		 case 13: date = buyerDiffBankEvent.getDate13();
             break;
	 }
	 return date;
 }


 /**
  * 	Method to get date details of Seller same bank event
  */
  public static Date getSellerSameEventDate(SellerSameBankEvent sellerSameBankEvent,int i) {
	  Date date = null;
	  switch(i) {
		  case 1: date = sellerSameBankEvent.getDate1();
			  break;
		  case 2: date = sellerSameBankEvent.getDate2();
              break;
		  case 3: date = sellerSameBankEvent.getDate3();
              break;
		  case 4: date = sellerSameBankEvent.getDate4();
              break;
		  case 5: date = sellerSameBankEvent.getDate5();
              break;
		  case 6: date = sellerSameBankEvent.getDate6();
              break;
		  case 7: date = sellerSameBankEvent.getDate7();
              break;
		  case 8: date = sellerSameBankEvent.getDate8();
              break;
		  case 9: date = sellerSameBankEvent.getDate9();
              break;
		  case 10: date = sellerSameBankEvent.getDate10();
              break;
		  case 11: date = sellerSameBankEvent.getDate11();
              break;
		  case 12: date = sellerSameBankEvent.getDate12();
              break;
		  case 13: date = sellerSameBankEvent.getDate13();
              break;
	  }
	  return date;
  }

  /**
   * 	Method to get date details of Seller Different bank event
   */
   public static Date getSellerDiffEventDate(SellerDiffBankEvent sellerDiffBankEvent,int i) {
	   Date date = null;
	   switch(i) {
		   case 1: date = sellerDiffBankEvent.getDate1();
			   break;
		   case 2: date = sellerDiffBankEvent.getDate2();
               break;
		   case 3: date = sellerDiffBankEvent.getDate3();
               break;
		   case 4: date = sellerDiffBankEvent.getDate4();
               break;
		   case 5: date = sellerDiffBankEvent.getDate5();
               break;
		   case 6: date = sellerDiffBankEvent.getDate6();
               break;
		   case 7: date = sellerDiffBankEvent.getDate7();
               break;
		   case 8: date = sellerDiffBankEvent.getDate8();
               break;
		   case 9: date = sellerDiffBankEvent.getDate9();
               break;
		   case 10: date = sellerDiffBankEvent.getDate10();
               break;
		   case 11: date = sellerDiffBankEvent.getDate11();
               break;
		   case 12: date = sellerDiffBankEvent.getDate12();
               break;
		   case 13: date = sellerDiffBankEvent.getDate13();
               break;
     case 14: date = sellerDiffBankEvent.getDate14();
               break;
     case 15: date = sellerDiffBankEvent.getDate15();
               break;
     case 16: date = sellerDiffBankEvent.getDate16();
               break;
	   }
	   return date;
   }

 /**
  * 	Method to get repeated days
  */

 public static int getModeElement(ArrayList<Integer> al)
 {
   int count = 1, tempCount;
   int popular = al.get(0);
   int temp = 0;
   for (int i = 0; i < al.size(); i++)
   {
     temp = al.get(i);
     tempCount = 0;
     for (int j = 1; j < al.size(); j++)
     {
       if (temp == al.get(j))
         tempCount++;
     }
     if (tempCount > count)
     {
       popular = temp;
       count = tempCount;
     }
   }
   return popular;
 }

	public Date getCurrentSavedLoginDate() {
		LoginDate loginDate = loginDateDao.getLoginDate();
		return loginDate == null ? getDate(new Date()) : loginDate.getLoginDate();
	}

	public static Date addMonths(Date date, int t) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(date);
		currentDate.add(Calendar.MONTH, t);
		return currentDate.getTime();
	}

	public static Date addDays(Date date, int t) {
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(date);
		currentDate.add(Calendar.DAY_OF_YEAR, t);
		return currentDate.getTime();
	}

}
