package ndfv.bookoflifev0.entity;

public class CounterEntity {

	private long id = -1;
	private String name = null;
	private boolean isSelected = false;
	private double value;

	public CounterEntity(String name, boolean isSelected, long id) {
		super();
		this.name = name;
		this.isSelected = isSelected;
		this.id = id;
		this.value = 1;
	}

	public CounterEntity() {
		super();
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
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
	
	public void setSelected(int isSelected) {
		if(isSelected == 1){
			this.isSelected = true;
		} else {
			this.isSelected = true;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
