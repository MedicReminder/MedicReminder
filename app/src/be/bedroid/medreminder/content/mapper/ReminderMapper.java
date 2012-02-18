package be.bedroid.medreminder.content.mapper;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import be.bedroid.medreminder.model.Reminder;

public class ReminderMapper {

	public static Reminder mapCursorToReminder(Cursor cursor) {
		cursor.moveToFirst();
		
		Reminder reminder = new Reminder();
		
		reminder.setId(cursor.getInt(cursor.getColumnIndex(Reminder.ID)));
		reminder.setMedicineId(cursor.getInt(cursor.getColumnIndex(Reminder.MEDICINE_ID)));
		reminder.setTime(cursor.getString(cursor.getColumnIndex(Reminder.TIME)));
		
		return reminder;
	}
	
	public static List<Reminder> mapCursorToReminders(Cursor cursor) {
		List<Reminder> reminders = new ArrayList<Reminder>();
		
		while(cursor.moveToNext()) {
			Reminder reminder = new Reminder();
			
			reminder.setId(cursor.getInt(cursor.getColumnIndex(Reminder.ID)));
			reminder.setMedicineId(cursor.getInt(cursor.getColumnIndex(Reminder.MEDICINE_ID)));
			reminder.setTime(cursor.getString(cursor.getColumnIndex(Reminder.TIME)));
			
			reminders.add(reminder);
		}
		
		return reminders;
	}
}
