package ndfv.bookoflifev0.loader;

import java.util.ArrayList;

import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.HistoricDay;

public interface IHistoricDAO {
	long insertHistoricDay(CounterEntity entity);
	ArrayList<HistoricDay> getHistoricDaysByCounterId(long counter_id);
	void saveHistoricDayValue(HistoricDay historic);
}
