package ndfv.bookoflifev0.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoricDay {
	private long counter_id;
	private long _id;
	private Date day;
	private double counter_value;
	private SimpleDateFormat dateFormat;
	
	public HistoricDay() {
		this(-1, new Date(), -1);
	}

	public HistoricDay(long counter_id, Date day, double counter_value) {
		super();
		this.counter_id = counter_id;
		this.day = day;
		this.counter_value = counter_value;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}



	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public long getCounter_id() {
		return counter_id;
	}

	public void setCounter_id(long counter_id) {
		this.counter_id = counter_id;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}
	
	public String getStringDay(){ 
		return dateFormat.format(day);
	}
	
	public void setDayByString(String format){
		try {
			day = dateFormat.parse(format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double getCounter_value() {
		return counter_value;
	}

	public void setCounter_value(double counter_value) {
		this.counter_value = counter_value;
	}
	
	
}
