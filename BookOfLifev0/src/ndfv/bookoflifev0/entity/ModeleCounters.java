package ndfv.bookoflifev0.entity;

import java.util.ArrayList;

import android.content.Context;

import ndfv.bookoflifev0.exception.MiteException;
import ndfv.bookoflifev0.loader.CountersEntityDAO;
import ndfv.bookoflifev0.loader.ICountersDAO;

public class ModeleCounters implements ICounterModel{
	private static ModeleCounters instance;
	
	private ArrayList<CounterEntity> countersList;
	private ArrayList<CounterEntity> countersListActivated;
	private ICountersDAO counterDAO;
	
	private ModeleCounters(){
		this(null);
	} 
	
	private ModeleCounters(Context context){
		countersList = new ArrayList<CounterEntity>();
		countersListActivated = new ArrayList<CounterEntity>();
		
		counterDAO = new CountersEntityDAO(context);
	} 
	
	public static ModeleCounters getInstance() throws MiteException {
		if(instance == null){
			throw new MiteException("Error !! You have to create the ModeleCounter class with a context. Use getInstance(Context context) before ! ;) ");
		}
		
		return instance;
	}
	
	public static ModeleCounters getInstance(Context context){
		if(instance == null){
			instance = new ModeleCounters(context);
		}
		
		return instance;
	}

	public ArrayList<CounterEntity> getCountersList() {
		return countersList;
	}

	public void setCountersList(ArrayList<CounterEntity> countersList) {
		this.countersList = countersList;
	}
	
	public ArrayList<CounterEntity> getCountersActivatedList() {
		countersListActivated.clear();
		for(int i=0;i<countersList.size();i++){
			if(countersList.get(i).isSelected()){
				countersListActivated.add(countersList.get(i));
			}
		}
		return countersListActivated;
	}

	@Override
	public void insertCounter(CounterEntity entity) {
		counterDAO.insertCounter(entity);
		countersList.add(entity);
	}

	@Override
	public void deleteCounter(CounterEntity entity) {
		counterDAO.deleteCounter(entity);
		countersList.remove(entity);
	}

	@Override
	public ArrayList<CounterEntity> getCounters() {
		countersList = counterDAO.getCounters();
		return countersList;
	}

	@Override
	public void updateCounter(CounterEntity entity) {
		counterDAO.updateCounter(entity);
		countersList.set(countersList.indexOf(entity), entity);
	}
	
}