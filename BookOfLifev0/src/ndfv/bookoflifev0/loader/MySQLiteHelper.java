package ndfv.bookoflifev0.loader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public static final String TABLE_COUNTERS = "counters";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_NAME = "name";
  public static final String COLUMN_VALUE = "value";
  public static final String COLUMN_CHECKED = "checked";


  private static final String DATABASE_NAME = "counters.db";
  private static final int DATABASE_VERSION = 7;

  // Commande sql pour la création de la base de données
  private static final String DATABASE_CREATE = "create table "
      + TABLE_COUNTERS 
      + "(" + COLUMN_ID + " integer primary key autoincrement, " 
      + COLUMN_NAME + " text not null, " 
      + COLUMN_VALUE + " INTEGER NOT NULL, " + COLUMN_CHECKED +" INTEGER NOT NULL);";

  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }
 
  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTERS);
    onCreate(db);
  }
}
