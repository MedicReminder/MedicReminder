package be.bedroid.medreminder;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import be.bedroid.medreminder.content.MedicineContentProvider;
import be.bedroid.medreminder.content.ReminderContentProvider;
import be.bedroid.medreminder.content.mapper.MedicineMapper;
import be.bedroid.medreminder.content.mapper.ReminderMapper;
import be.bedroid.medreminder.model.Medicine;
import be.bedroid.medreminder.model.Reminder;

public class MedReminderActivity extends ListActivity {

	private static final String[] MEDS = new String[] { "Dafalgan", "Citrozone" };

	private ProgressDialog mProgressDialog = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// setContentView(R.layout.main);
		Cursor c = null;
		ListAdapter cursorListAdapter = new SimpleCursorAdapter(this, R.layout.list_item_my_medicines, c, new String[] { "name" }, new int[] { R.id.list_name });
		ListAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.list_item_my_medicines, MEDS);
		setListAdapter(listAdapter);

		OnClickListener testButton = new OnClickListener() {
			public void onClick(View v) {
				Cursor cursor = getContentResolver().query(MedicineContentProvider.CONTENT_URI, new String[] { Reminder.ID, "name", "method" }, "name LIKE ?", new String[] { "%2%" }, null);

				List<Medicine> medicines = MedicineMapper.mapCursorToMedicines(cursor);

				cursor = getContentResolver().query(ReminderContentProvider.CONTENT_URI, new String[] { Medicine.ID, "medicine_id", "time" }, null, null, null);

				List<Reminder> reminders = ReminderMapper.mapCursorToReminders(cursor);
			}
		};

		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(testButton);
		// OnClickListener testButton = new OnClickListener() {
		// public void onClick(View v) {
		// Cursor cursor =
		// getContentResolver().query(MedicineContentProvider.CONTENT_URI, new
		// String[] { Reminder.ID, "name", "method" }, "name LIKE ?", new
		// String[] { "%2%" }, null);
		//
		// List<Medicine> medicines =
		// MedicineMapper.mapCursorToMedicines(cursor);
		//
		// cursor =
		// getContentResolver().query(ReminderContentProvider.CONTENT_URI, new
		// String[] { Medicine.ID, "medicine_id", "time" }, null, null, null);
		//
		// List<Reminder> reminders =
		// ReminderMapper.mapCursorToReminders(cursor);
		// }
		// };
		//
		// Button button = (Button) findViewById(R.id.button1);
		// button.setOnClickListener(testButton);

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
		case R.id.alarmMenu:
			startActivity(new Intent(this, AlertTestLauncher.class));
			return true;
		case R.id.settingsMenu:
			Intent intent = new Intent(this, EditPreferencesActivity.class);
			startActivity(intent);
			return true;
		case R.id.medsMenu:
			startActivity(new Intent(this, MedsActivity.class));
			return true;
		case R.id.remindersMenu:
			startActivity(new Intent(this, RemindersActivity.class));
			return true;
		case R.id.smartMenu:
			startActivity(new Intent(this, SmartPreferencesActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onProgressUpdate(String... args) {
		// here you will have to update the progressbar
		// with something like
		mProgressDialog.setProgress(Integer.parseInt(args[0]));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		// Toast.makeText(this, "klik op item " + position,
		// Toast.LENGTH_LONG).show();

		Intent i = new Intent(this, MedDetailActivity.class);
		i.putExtra("medname", MEDS[position]);
		startActivity(i);

		super.onListItemClick(l, v, position, id);
	}

}
