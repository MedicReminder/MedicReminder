package be.bedroid.medreminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MedReminderActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		startActivity(new Intent(this, NotificationActivity.class));

	}
}