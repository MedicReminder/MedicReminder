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

/**
 * 
 * TOBE DELETED WHEN YOU CAN DO THIS IN THE APP
 */
public class AlertTestLauncher extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alertlauncher);
	}

	public void createAlert(View view) {
		final TimePicker timePicker = (TimePicker) this.findViewById(R.id.timePicker);

		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);

		Log.v("MedReminderActivity", String.format("Alert will go off at %s", calendar.toString()));

		Intent intent = new Intent(this, AlertReceiver.class);
		intent.putExtra("message", getString(R.string.messageTakeMeds));
		PendingIntent sender = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, sender);
	}

	public void removeAlert(View view) {
		// TODO
	}
}
