package be.bedroid.medreminder.content.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import be.bedroid.medreminder.model.Medicine;
import be.bedroid.medreminder.model.Reminder;

public class MedReminderOpenHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "medicinereminder";

	public MedReminderOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Medicine.onCreate(db);
		Reminder.onCreate(db);
//		ContentValues values = new ContentValues(); 

//		values.put(Medicine.NAME, "Test Medicine 1");
//		values.put(Medicine.METHOD, "Injection");
//		db.insert(Medicine.TABLE_NAME, null, values);
//
//		values = new ContentValues(); 
//		values.put(Medicine.NAME, "Test Medicine 2");
//		values.put(Medicine.METHOD, "Injection");
//		db.insert(Medicine.TABLE_NAME, null, values);
//
//		values = new ContentValues(); 
//		values.put(Medicine.NAME, "Test Medicine 3");
//		values.put(Medicine.METHOD, "Injection");
//		Long id = db.insert(Medicine.TABLE_NAME, null, values);
//
//		values = new ContentValues(); 
//		values.put(Reminder.MEDICINE_ID, id);
//		values.put(Reminder.TIME, "Test Time");
//		id = db.insert(Reminder.TABLE_NAME, null, values);
//
//		values = new ContentValues(); 
//		values.put(Reminder.MEDICINE_ID, id);
//		values.put(Reminder.TIME, "Test Time 2");
//		id = db.insert(Reminder.TABLE_NAME, null, values);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}

}
