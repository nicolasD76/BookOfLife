package ndfv.bookoflifev0.entity;

import java.util.ArrayList;

public class ModeleCounterEntity {
	private static ModeleCounterEntity instance;
	
	private ArrayList<CounterEntity> countersList;
	
	private ModeleCounterEntity(){
		countersList = new ArrayList<CounterEntity>();
	} 
	
	public static ModeleCounterEntity getInstance(){
		if(instance == null){
			instance = new ModeleCounterEntity();
		}
		
		return instance;
	}

	public ArrayList<CounterEntity> getCountersList() {
		return countersList;
	}

	public void setCountersList(ArrayList<CounterEntity> countersList) {
		this.countersList = countersList;
	}
	
	
}
