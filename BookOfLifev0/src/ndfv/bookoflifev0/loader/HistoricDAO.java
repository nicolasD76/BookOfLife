package ndfv.bookoflifev0.loader;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import ndfv.bookoflifev0.entity.CounterEntity;
import ndfv.bookoflifev0.entity.HistoricDay;

public class HistoricDAO extends AbstractDAO implements IHistoricDAO {
	private String[] allColumns = { 
			MySQLiteHelper.COLUMN_ID_HISTORIC, MySQLiteHelper.COLUMN_ID_COUNTER,
			MySQLiteHelper.COLUMN_DAY, MySQLiteHelper.COLUMN_VALUE_COUNTER 
			};
	
	public HistoricDAO(Context context) {
		super(context);
	}
	
	private HistoricDay cursorToCounterEntity(Cursor cursor) {
		HistoricDay historicDay = new HistoricDay();
		historicDay.set_id(cursor.getLong(0));
		historicDay.setCounter_id(cursor.getLong(1));
		historicDay.setDayByString(cursor.getString(2));
		historicDay.setCounter_value(cursor.getInt(3));
		return historicDay;
	}
	
	@Override
	public long insertHistoricDay(CounterEntity entity) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_ID_COUNTER, entity.getId());
		values.put(MySQLiteHelper.COLUMN_DAY, entity.getStringLastUpdateDate().split(" ")[0]);
		values.put(MySQLiteHelper.COLUMN_VALUE_COUNTER, entity.getValue());
		
	    open();
	    long insertId = database.insert(MySQLiteHelper.TABLE_HISTORIC, null,
	        values);
	    
	    close();
	    
	    return insertId;
	}

	@Override
	public ArrayList<HistoricDay> getHistoricDaysByCounterId(long counter_id) {
		open();
		ArrayList<HistoricDay> historicDaysList = new ArrayList<HistoricDay>();
	    database = dbHelper.getWritableDatabase();

	    Cursor cursor = database.query(
	    	MySQLiteHelper.TABLE_HISTORIC,
	        allColumns, //SELECT * FROM Table_Historic
	        MySQLiteHelper.COLUMN_ID_COUNTER + " = " + counter_id, //Where id_counter = counter_id (variavle en paramètre)
	        null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      HistoricDay historicDay = cursorToCounterEntity(cursor);
	      historicDaysList.add(historicDay);
	      cursor.moveToNext();
	    }
	    // assurez-vous de la fermeture du curseur
	    cursor.close();
	    close();
	    return historicDaysList;
	}

}
