package com.adnan.utility;

import com.adnan.Repository.UserRepository;
import com.adnan.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Component
public class AppUtility {

	@Autowired
	private UserRepository userRepository;
	
	public User getCurrentUser() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
		if (null != auth) {
			if(auth.getName().equals("anonymousUser"))
				return null;
			Long userId =  Long.parseLong(auth.getName()) ;
			return userRepository.findById(userId).orElse(null);
		}
		return null;
    }
	
	public static String get4OTP() {
		String otp= new DecimalFormat("0000").format(new Random().nextInt(9999));
		System.out.println(otp);
		return otp;
	}
	
	public static String get6OTP() {
		String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));
		System.out.println(otp);
		return otp;
		//return "000000";
	}
	
	public static int diffInMonths(Date date) {
		if(date==null)
			return 23;
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date);
		
		int m1 = cal1.get(Calendar.MONTH);
		int m2 = cal2.get(Calendar.MONTH);
		
		int y1 = cal1.get(Calendar.YEAR);
		int y2 = cal2.get(Calendar.YEAR);
		
		int d1 = cal1.get(Calendar.DAY_OF_MONTH);
		int d2 = cal2.get(Calendar.DAY_OF_MONTH);
		
		int add = (d1<=d2)?1:0;
		
		return m2-m1 + (y2-y1)*12 + add;
	}
	
   public static String getYear() {
	   Calendar calendar = Calendar.getInstance();
	   String y = calendar.get( Calendar.YEAR)+"";
	   return  y.substring(2);
   }
	
	public static Date getStartDateOfWeek() {
		Calendar  cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -cal.get(Calendar.DAY_OF_WEEK)+2);
		return cal.getTime();
	}
	
	public static Date getStartDateOfMonth(int month) {
		Calendar  cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	public static Date getStartDateOfCurrentMonth() {
		Calendar  cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	public static Date getTodayDate() {
		Calendar  cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		return cal.getTime();
	}
	
	public static Date getStartDateOfYear() {
		Calendar  cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}
	
	public static String getMonth0Index(int n) {
		n++;
		switch (n) {
		case 1:
			return "Jan";
		case 2:
        	return "Feb";
		case 3:
			return "Mar";
		case 4:
        	return "Apr";
		case 5:
			return "May";
		case 6:
        	return "Jun";
		case 7:
			return "Jul";
		case 8:
        	return "Aug";
		case 9:
			return "Sep";
		case 10:
        	return "Oct";
		case 11:
			return "Nov";
		case 12:
        	return "Dec";			
		default:
			return "Jan";
		}
	}
	
	public static String getMonth1Index(int n) {
		
		switch (n) {
		case 1:
			return "Jan";
		case 2:
        	return "Feb";
		case 3:
			return "Mar";
		case 4:
        	return "Apr";
		case 5:
			return "May";
		case 6:
        	return "Jun";
		case 7:
			return "Jul";
		case 8:
        	return "Aug";
		case 9:
			return "Sep";
		case 10:
        	return "Oct";
		case 11:
			return "Nov";
		case 12:
        	return "Dec";			
		default:
			return "Jan";
		}
	}
	
	public static int yearDiff(Date date) {
		if(date==null)
			return 22;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar cal2 = Calendar.getInstance();
		return cal2.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
	}
	
	
	
}
