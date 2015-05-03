package ndfv.bookoflifev0.loader;

import java.util.ArrayList;
import java.util.List;

import ndfv.bookoflifev0.entity.CounterEntity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CountersEntityDAO implements ICountersDAO{

	// Champs de la base de données
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_VALUE,
			MySQLiteHelper.COLUMN_CHECKED };

	public CountersEntityDAO(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public CounterEntity createCounterEntity(CounterEntity counterEntity) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, counterEntity.getName());
		values.put(MySQLiteHelper.COLUMN_VALUE, 0);
		values.put(MySQLiteHelper.COLUMN_CHECKED, 0);
	    open();
		long insertId = database.insert(MySQLiteHelper.TABLE_COUNTERS, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COUNTERS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();

		CounterEntity newCounterEntity = cursorToCounterEntity(cursor);
		Log.d("d", "newCounterEntity name: " + newCounterEntity.getName()
				+ " id: " + newCounterEntity.getId() + " value: "
				+ newCounterEntity.getValue());
		cursor.close();
	    close();
		return newCounterEntity;
	}

	public void deleteComment(CounterEntity counterEntity) {
		  open();
		long id = counterEntity.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_COUNTERS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	    close();
	}

	public List<CounterEntity> getAllCounterEntity() {
		  open();
		List<CounterEntity> counterEntityList = new ArrayList<CounterEntity>();
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

//	public void upDateData(CounterEntity counterEntity) {
//		database = dbHelper.getWritableDatabase();
//		long id = counterEntity.getId();
//		ContentValues cv = new ContentValues();
//		// if(counterEntity.isSelected()){
//		cv.put(MySQLiteHelper.COLUMN_CHECKED, counterEntity.isSelected());
//		// }else{
//		// cv.put(MySQLiteHelper.COLUMN_CHECKED, 0);
//		// }
//		cv.put(MySQLiteHelper.COLUMN_NAME, counterEntity.getName());
//		cv.put(MySQLiteHelper.COLUMN_VALUE, counterEntity.getValue());
//		database.update(MySQLiteHelper.TABLE_COUNTERS, cv, "_id " + "=" + id,
//				null);
//	}

	private CounterEntity cursorToCounterEntity(Cursor cursor) {
		CounterEntity counterEntity = new CounterEntity();
		counterEntity.setId(cursor.getLong(0));
		counterEntity.setName(cursor.getString(1));
		counterEntity.setValue(cursor.getInt(2));
		counterEntity.setSelected(cursor.getInt(3));
		return counterEntity;
	}

	@Override
	public void insertCounter(CounterEntity entity) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_NAME, entity.getName());
		values.put(MySQLiteHelper.COLUMN_VALUE, 0);
		values.put(MySQLiteHelper.COLUMN_CHECKED, entity.isSelected());
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
		database.update(MySQLiteHelper.TABLE_COUNTERS, cv, "_id " + "=" + id,
				null);	
		close();

	}
}