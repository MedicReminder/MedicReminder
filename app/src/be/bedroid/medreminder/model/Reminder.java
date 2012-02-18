package be.bedroid.medreminder.model;

import android.database.sqlite.SQLiteDatabase;

public class Reminder {

	private Integer id;
	private Integer medicineId;
	private Medicine medicine;
	private String time;

	public Reminder(){}

	public Reminder(Integer id, Medicine medicine, String time) {
		this.id = id;
		this.medicine = medicine;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}
	public Medicine getMedicine() {
		return medicine;
	}
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	/*
	 * Database Specific Stuff
	 */

	public static final String TABLE_NAME = "reminder";   
	public static final String ID = "_id";
	public static final String MEDICINE_ID = "medicine_id";
	public static final String TIME = "time"; 
	private static final String TABLE_CREATE =
			"CREATE TABLE " + TABLE_NAME + " (" +
					ID + " integer primary key autoincrement, " +
					MEDICINE_ID + " INTEGER, " +
					TIME + " TEXT);";

	public static void onCreate(SQLiteDatabase database) {
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		database.execSQL(TABLE_CREATE);
	}
}
