package ndfv.bookoflifev0.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;

public class CounterEntity {

	private long id = -1;
	private String name = null;
	private boolean isSelected = false;
	private double value;
	private Date creationDate;
	private Date lastUpdateDate;
	private SimpleDateFormat dateFormat;

	public CounterEntity(String name, boolean isSelected, long id) {
		super();
		this.name = name;
		this.isSelected = isSelected;
		this.id = id;
		this.value = 1;
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.creationDate = new Date();
		this.lastUpdateDate = new Date();
	}

	public CounterEntity() {
		this("unknows", false, -1);
	}

	public String getStringCreationDate(){ 
		return dateFormat.format(creationDate);
	}
	
	public void setCreationDateByString(String format){
		try {
			creationDate = dateFormat.parse(format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getStringLastUpdateDate(){ 
		return dateFormat.format(lastUpdateDate);
	}
	
	public void setLastUpdateDateByString(String format){
		try {
			lastUpdateDate = dateFormat.parse(format);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	public void updateLastUpdateDate(){
		this.lastUpdateDate = new Date();
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
		updateLastUpdateDate();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public void setSelectedByInt(int isSelected) {
		if(isSelected != 0){
			this.isSelected = true;
		} else {
			this.isSelected = false;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
