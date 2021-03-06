package ndfv.bookoflifev0.entity;

import java.util.ArrayList;

public interface ICounterModel {
	void insertCounter(CounterEntity entity);
	void deleteCounter(CounterEntity entity);
	void updateCounter(CounterEntity entity);
	ArrayList<CounterEntity> getCountersFromDataBase();
}
