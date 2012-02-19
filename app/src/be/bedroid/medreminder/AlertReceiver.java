package be.bedroid.medreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AlertReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.v("AlertReceiver", "Received Alert");

		Bundle bundle = intent.getExtras();
		String message = bundle.getString("message");
		Log.v("AlertReceiver", "Received message " + message);
		Intent alertIntent = new Intent(context, AlertWithTtsActivity.class);
		alertIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(alertIntent);

	}

}
