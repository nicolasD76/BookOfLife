package ndfv.bookoflifev0.entity;

import java.util.ArrayList;

import android.content.Context;

import ndfv.bookoflifev0.exception.MiteException;
import ndfv.bookoflifev0.loader.CountersEntityDAO;
import ndfv.bookoflifev0.loader.HistoricDAO;
import ndfv.bookoflifev0.loader.ICountersDAO;
import ndfv.bookoflifev0.loader.IHistoricDAO;

public class ModeleCounters implements ICounterModel{
	private static ModeleCounters instance;
	
	private ArrayList<CounterEntity> countersList;
	private ArrayList<CounterEntity> countersListActivated;
	private ICountersDAO counterDAO;
	private IHistoricDAO historicDAO;
	
	private ModeleCounters(){
		this(null);
	} 
	
	private ModeleCounters(Context context){
		countersList = new ArrayList<CounterEntity>();
		countersListActivated = new ArrayList<CounterEntity>();
		
		counterDAO = new CountersEntityDAO(context);
		historicDAO = new HistoricDAO(context);
		
		//Récupération des compteurs
		countersList = getCountersFromDataBase();
		
		//Récupération des historiques au chargement du modèle
		for (CounterEntity counter : countersList) {
			if(isCreatableHistoric(counter)){
				historicDAO.insertHistoricDay(counter);
			}
			counter.setHistoric(historicDAO.getHistoricDaysByCounterId(counter.getId()));
		}
	}
	
	public boolean isCreatableHistoric(CounterEntity counter){
		boolean isCreatable = false;
		CounterEntity counterBase = counterDAO.getCounterByName(counter);
		if(counterBase.getLastUpdateDate().before(counter.getLastUpdateDate())){
			isCreatable = true;
		}
		
		return isCreatable;
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
		countersList.add(counterDAO.getCounterByName(entity));
	}

	@Override
	public void deleteCounter(CounterEntity entity) {
		counterDAO.deleteCounter(entity);
		countersList.remove(entity);
	}

	@Override
	public ArrayList<CounterEntity> getCountersFromDataBase() {
		countersList = counterDAO.getCountersFromDataBase();
		return countersList;
	}

	@Override
	public void updateCounter(CounterEntity entity) {
		counterDAO.updateCounter(entity);
//		countersList.set(countersList.indexOf(entity), entity);
	}
	
}