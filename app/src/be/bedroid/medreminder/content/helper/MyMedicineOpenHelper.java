package be.bedroid.medreminder.content.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyMedicineOpenHelper extends SQLiteOpenHelper {

	private static final String DBNAME = "MyMedicines";
	private static final String SQL_CREATE_MAIN = "CREATE TABLE MYMEDICINE (_ID INTEGER PRIMARY KEY, NAME TEXT)";

	public MyMedicineOpenHelper(Context context) {
		super(context, DBNAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_MAIN);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
