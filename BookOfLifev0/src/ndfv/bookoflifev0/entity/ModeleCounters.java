package ndfv.bookoflifev0.entity;

import java.util.ArrayList;

public class ModeleCounters {
	private static ModeleCounters instance;
	
	private ArrayList<CounterEntity> countersList;
	
	private ModeleCounters(){
		countersList = new ArrayList<CounterEntity>();
	} 
	
	public static ModeleCounters getInstance(){
		if(instance == null){
			instance = new ModeleCounters();
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
