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
	public static final String COLUMN_ISWIDGET = "is_widget";
	public static final String COLUMN_CREATION_DATE = "creation_date";
	public static final String COLUMN_LAST_UPDATE_DATE = "last_update_date";

	public static final String TABLE_HISTORIC = "historic";
	public static final String COLUMN_ID_HISTORIC = "_id";
	public static final String COLUMN_ID_COUNTER = "_id_counter";
	public static final String COLUMN_DAY = "day";
	public static final String COLUMN_VALUE_COUNTER = "value";

	private static final String DATABASE_NAME = "counters.db";
	private static final int DATABASE_VERSION = 13;

	// Commande sql pour la création de la base de données
	private static final String DATABASE_CREATE = "create table " + TABLE_COUNTERS + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_NAME + " text not null, " + COLUMN_VALUE
			+ " INTEGER NOT NULL, " + COLUMN_CHECKED + " INTEGER NOT NULL, " + COLUMN_ISWIDGET + " INTEGER NOT NULL, " + COLUMN_CREATION_DATE + " TEXT NOT NULL, " + COLUMN_LAST_UPDATE_DATE
			+ " TEXT NOT NULL " + "); ";

	private static final String CREATE_T_HISTORIC = "create table " + TABLE_HISTORIC + "(" + COLUMN_ID_HISTORIC + " integer primary key autoincrement, " + COLUMN_ID_COUNTER + " integer, "
			+ COLUMN_DAY + " text not null, " + COLUMN_VALUE_COUNTER + " INTEGER NOT NULL, " + "FOREIGN KEY(" + COLUMN_ID_COUNTER + ") REFERENCES " + TABLE_COUNTERS + "(" + COLUMN_ID + ")" + ");";

	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
		database.execSQL(CREATE_T_HISTORIC);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORIC);
		onCreate(db);
	}
}
