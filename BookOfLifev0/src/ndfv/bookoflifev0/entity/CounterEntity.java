package ndfv.bookoflifev0.entity;

public class CounterEntity {

	private long id = -1;
	private String name = null;
	private boolean isSelected = false;

	public CounterEntity(String name, boolean isSelected, long id) {
		super();
		this.name = name;
		this.isSelected = isSelected;
		this.id = id;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
