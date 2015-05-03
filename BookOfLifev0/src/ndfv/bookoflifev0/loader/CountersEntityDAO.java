package ndfv.bookoflifev0.loader;

import java.util.ArrayList;

import ndfv.bookoflifev0.entity.CounterEntity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class CountersEntityDAO extends AbstractDAO implements ICountersDAO{
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_VALUE,
			MySQLiteHelper.COLUMN_CHECKED, MySQLiteHelper.COLUMN_ISWIDGET, MySQLiteHelper.COLUMN_CREATION_DATE,
			MySQLiteHelper.COLUMN_LAST_UPDATE_DATE, MySQLiteHelper.COLUMN_LAST_UPDATE_DATE };

	public CountersEntityDAO(Context context) {
		super(context);
	}

	private CounterEntity cursorToCounterEntity(Cursor cursor) {
		CounterEntity counterEntity = new CounterEntity();
		System.out.println("#debug " + cursor.getLong(4));
		System.out.println("#debug ----------------");


		counterEntity.setId(cursor.getLong(0));
		counterEntity.setName(cursor.getString(1));
		counterEntity.setValue(cursor.getInt(2));
		counterEntity.setSelectedByInt(cursor.getInt(3));
		counterEntity.setWidgetCounterByInt(cursor.getInt(4));
		counterEntity.setCreationDateByString(cursor.getString(5));
		counterEntity.setLastUpdateDateByString(cursor.getString(6));
		return counterEntity;
	}

	@Override
	public void insertCounter(CounterEntity entity) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, entity.getName());
		values.put(MySQLiteHelper.COLUMN_VALUE, 0);
		values.put(MySQLiteHelper.COLUMN_CHECKED, entity.isSelected());
		values.put(MySQLiteHelper.COLUMN_CREATION_DATE, entity.getStringCreationDate());
		values.put(MySQLiteHelper.COLUMN_LAST_UPDATE_DATE, entity.getStringLastUpdateDate());
		values.put(MySQLiteHelper.COLUMN_ISWIDGET, entity.isWidgetCounter());

		
	    open();
	    long insertId = database.insert(MySQLiteHelper.TABLE_COUNTERS, null,
	        values);
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_COUNTERS,
	        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();

	    CounterEntity newCounterEntity = cursorToCounterEntity(cursor);
	    cursor.close();
	    close();
	}

	@Override
	public void deleteCounter(CounterEntity entity) {
		  open();
		    long id = entity.getId();
		    System.out.println("Comment deleted with id: " + id);
		    database.delete(MySQLiteHelper.TABLE_COUNTERS, MySQLiteHelper.COLUMN_ID
		        + " = " + id, null);
		    close();
	}

	@Override
	public ArrayList<CounterEntity> getCountersFromDataBase() {
		open();
		ArrayList<CounterEntity> counterEntityList = new ArrayList<CounterEntity>();
	    database = dbHelper.getWritableDatabase();

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_COUNTERS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      CounterEntity counterEntity = cursorToCounterEntity(cursor);
	      counterEntityList.add(counterEntity);
	      cursor.moveToNext();
	    }
	    // assurez-vous de la fermeture du curseur
	    cursor.close();
	    close();
	    return counterEntityList;
	}

	@Override
	public void updateCounter(CounterEntity entity) {
		open();
		database = dbHelper.getWritableDatabase();
		long id = entity.getId();
		ContentValues cv = new ContentValues();


		cv.put(MySQLiteHelper.COLUMN_NAME, entity.getName());
		cv.put(MySQLiteHelper.COLUMN_VALUE, entity.getValue());
		cv.put(MySQLiteHelper.COLUMN_CHECKED, entity.isSelected());
		cv.put(MySQLiteHelper.COLUMN_ISWIDGET, entity.isWidgetCounter());
		cv.put(MySQLiteHelper.COLUMN_LAST_UPDATE_DATE, entity.getStringLastUpdateDate());
		database.update(MySQLiteHelper.TABLE_COUNTERS, cv, "_id " + "=" + id,
				null);	
		close();

	}
	
	@Override
	public CounterEntity getCounterByName(CounterEntity entity) {
		open();
		CounterEntity counterEntityMatch = new CounterEntity();
	    database = dbHelper.getWritableDatabase();

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_COUNTERS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      CounterEntity counterEntity = cursorToCounterEntity(cursor);
	      if(entity.getName().equals(counterEntity.getName())){
	    	  counterEntityMatch  = counterEntity;
	      }
	      cursor.moveToNext();
	    }
	    // assurez-vous de la fermeture du curseur
	    cursor.close();
	    close();
	    return counterEntityMatch;
	    
	}
}