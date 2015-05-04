package ndfv.bookoflifev0.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
	private static SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat hourDateFormat = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat dateDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public static SimpleDateFormat getFullDateFormat() {
		return fullDateFormat;
	}

	public static void setFullDateFormat(SimpleDateFormat fullDateFormat) {
		DateTool.fullDateFormat = fullDateFormat;
	}

	public static Date createFullDateByString(String format){
		Date fullDate = null;
		try {
			fullDate = fullDateFormat.parse(format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fullDate;
	}
	
	public static Date createHourDateByString(String format){
		Date hourDate = null;
		try {
			hourDate = hourDateFormat.parse(format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hourDate;
	}
	
	public static Date createDateByString(String format){
		Date dateDate = null;
		try {
			dateDate = dateDateFormat.parse(format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateDate;
	}
	
	public static String getStringFullDate(Date date){ 
		return fullDateFormat.format(date);
	}
	
	public static String getStringhourDate(Date date){ 
		return hourDateFormat.format(date);
	}
	
	public static String getStringDate(Date date){
		return dateDateFormat.format(date);
	}

	public static int getDayNumber(Date date){
		return Integer.parseInt(getStringDate(date).split("-")[2]);
	}
	
	
}
