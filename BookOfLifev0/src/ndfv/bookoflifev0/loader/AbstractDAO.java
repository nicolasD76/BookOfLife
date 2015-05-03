package ndfv.bookoflifev0.loader;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractDAO {
	// Champs de la base de données
		protected SQLiteDatabase database;
		protected MySQLiteHelper dbHelper;
		
		public AbstractDAO(Context context){
			dbHelper = new MySQLiteHelper(context);
		}

		protected void open() throws SQLException {
			database = dbHelper.getWritableDatabase();
		}

		public void close() {
			dbHelper.close();
		}
}
