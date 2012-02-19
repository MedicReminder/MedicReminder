package be.bedroid.medreminder;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

public class MedDetailActivity extends Activity {

	private String medname;

	private TimePicker p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		medname = getIntent().getStringExtra("medname");

		setContentView(R.layout.detail);

		p = (TimePicker) this.findViewById(R.id.medtimep);

	}

	public void save(View view) {
		// TODO update the database / ...

		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), p.getCurrentHour(), p.getCurrentMinute(), 0);

		Log.v("MedReminderActivity", String.format("Alert for medicine %s will go off at %s", medname, calendar.toString()));

		Intent intent = new Intent(this, AlertReceiver.class);
		intent.putExtra("medname", medname);
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, sender);

		finish();
	}
}
