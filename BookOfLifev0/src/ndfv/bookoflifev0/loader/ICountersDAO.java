package ndfv.bookoflifev0.loader;

import java.util.ArrayList;

import ndfv.bookoflifev0.entity.CounterEntity;

public interface ICountersDAO {
	void insertCounter(CounterEntity entity);
	void deleteCounter(CounterEntity entity);
	void updateCounter(CounterEntity entity);
	ArrayList<CounterEntity> getCountersFromDataBase();
	CounterEntity getCounterByName(CounterEntity entity);
}
