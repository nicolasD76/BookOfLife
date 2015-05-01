package ndfv.bookoflifev0.entity;

public class CounterEntity {

	private String name = null;
	private boolean isSelected = false;
	
	public CounterEntity(String name, boolean isSelected) {
		super();
		this.name = name;
		this.isSelected = isSelected;
	}
	public CounterEntity() {
		super();
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
	
	
}
