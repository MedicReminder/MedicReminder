package be.bedroid.medreminder;

import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import be.bedroid.medreminder.content.MedicineContentProvider;
import be.bedroid.medreminder.content.ReminderContentProvider;
import be.bedroid.medreminder.content.mapper.MedicineMapper;
import be.bedroid.medreminder.content.mapper.ReminderMapper;
import be.bedroid.medreminder.model.Medicine;
import be.bedroid.medreminder.model.Reminder;

public class MedReminderActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		OnClickListener testButton = new OnClickListener() {

			public void onClick(View v) {
				Cursor cursor = getContentResolver().query(
						MedicineContentProvider.CONTENT_URI,
					    new String[] {"id", "name", "method"},
					    "name LIKE ?",
					    new String[] {"%2%"},
					    null);
				
				List<Medicine> medicines = MedicineMapper.mapCursorToMedicines(cursor);
				
				cursor = getContentResolver().query(
						ReminderContentProvider.CONTENT_URI,
					    new String[] {"id", "medicine_id", "time"},
					    null,
					    null,
					    null);
				
				List<Reminder> reminders = ReminderMapper.mapCursorToReminders(cursor);
			}
		};
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(testButton);
	}
}