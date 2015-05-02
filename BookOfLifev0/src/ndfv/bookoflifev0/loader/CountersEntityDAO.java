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

public class CountersEntityDAO {

	  // Champs de la base de données
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
	      MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_VALUE };

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
	    counterEntity.setValue(2);
	    values.put(MySQLiteHelper.COLUMN_NAME, counterEntity.getName());
	    values.put(MySQLiteHelper.COLUMN_VALUE, 3);
	    long insertId = database.insert(MySQLiteHelper.TABLE_COUNTERS, null,
	        values);
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_COUNTERS,
	        allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();

	    CounterEntity newCounterEntity = cursorToCounterEntity(cursor);
	    Log.d("d", "newCounterEntity name: " + newCounterEntity.getName() +" id: " + newCounterEntity.getId() +" value: " + newCounterEntity.getValue());
	    cursor.close();
	    return newCounterEntity;
	  }
 
	  public void deleteComment(CounterEntity counterEntity) {
	    long id = counterEntity.getId();
	    System.out.println("Comment deleted with id: " + id);
	    database.delete(MySQLiteHelper.TABLE_COUNTERS, MySQLiteHelper.COLUMN_ID
	        + " = " + id, null);
	  }

	  public List<CounterEntity> getAllCounterEntity() {
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
	    return counterEntityList;
	  }

	  private CounterEntity cursorToCounterEntity (Cursor cursor) {
		  CounterEntity counterEntity = new CounterEntity();
	    counterEntity.setId(cursor.getLong(0));
	    counterEntity.setName(cursor.getString(1));
	    counterEntity.setValue(cursor.getInt(2));
	    return counterEntity;
	  }
	}