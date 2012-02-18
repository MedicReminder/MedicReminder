package be.bedroid.medreminder.model;

import android.database.sqlite.SQLiteDatabase;

public class Medicine {
	private Integer id;
	private String name;
	private String method;
	
	public Medicine(Integer id, String name, String method) {
		this.id = id;
		this.name = name;
		this.method = method;
	}	
	
	public Medicine() {}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	/*
	 * Database Specific Stuff
	 */
	
	public static final String TABLE_NAME = "medicine";   
	public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String METHOD = "method"; 
    private static final String MEDICINE_TABLE_CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " integer primary key autoincrement, " +
                NAME + " TEXT, " +
                METHOD + " TEXT);";
	
	public static void onCreate(SQLiteDatabase database) {
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		database.execSQL(MEDICINE_TABLE_CREATE);
	}	
	
}