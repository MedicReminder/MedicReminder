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
		String medname = bundle.getString("medname");

		Log.v("AlertReceiver", "Received message " + medname);
		Intent alertIntent = new Intent(context, AlertActivity.class);
		alertIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		alertIntent.putExtra("medname", medname);
		// addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(alertIntent);

	}
}
