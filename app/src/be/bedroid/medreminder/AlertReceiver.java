package be.bedroid.medreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AlertReceiver extends BroadcastReceiver {

	public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v("AlertReceiver", "Received Alert");

		Bundle bundle = intent.getExtras();
		String message = bundle.getString("message");
		Log.v("AlertReceiver", "Received message " + message);
		Intent alertIntent = new Intent(context, AlertActivity.class);
		alertIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(alertIntent);
		
		// TODO: start Sony smart service

	}

}
