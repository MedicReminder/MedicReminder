package be.bedroid.medreminder;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		OnClickListener testButton = new OnClickListener() {
			public void onClick(View v) {
				Cursor cursor = getContentResolver().query(
						MedicineContentProvider.CONTENT_URI,
						new String[] {Reminder.ID, "name", "method"},
						"name LIKE ?",
						new String[] {"%2%"},
						null);

				List<Medicine> medicines = MedicineMapper.mapCursorToMedicines(cursor);

				cursor = getContentResolver().query(
						ReminderContentProvider.CONTENT_URI,
						new String[] {Medicine.ID, "medicine_id", "time"},
						null,
						null,
						null);

				List<Reminder> reminders = ReminderMapper.mapCursorToReminders(cursor);
			}
		};

		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(testButton);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settingsMenu:
			Intent intent = new Intent(this, EditPreferencesActivity.class);
			startActivity(intent);
			return true;
		case R.id.medsMenu:
			startActivity(new Intent(this, MedsActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
