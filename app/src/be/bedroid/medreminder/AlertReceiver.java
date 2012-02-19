package be.bedroid.medreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import be.bedroid.medreminder.liveware.SampleExtensionService;

public class AlertReceiver extends BroadcastReceiver {

	public static final String LOG_TAG = "AlertReceiver";
	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v(LOG_TAG, "Received Alert");

		Bundle bundle = intent.getExtras();
		String medname = bundle.getString("medname");

		Log.v(LOG_TAG, "Received message " + medname);
		Intent alertIntent = new Intent(context, AlertActivity.class);
		alertIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		alertIntent.putExtra("medname", medname);
		// addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(alertIntent);

		// Start Sony smart service
		String message = "Please take your " + medname + "pills";
		// TODO use xml config string

		Intent intentSmart = new Intent();
		Log.v(LOG_TAG, "Sending message to SmartWatch: " + message);
		intentSmart.setClass(context, SampleExtensionService.class);
		intentSmart.putExtra(EXTRA_MESSAGE, message);
		context.startService(intentSmart);

	}
}
