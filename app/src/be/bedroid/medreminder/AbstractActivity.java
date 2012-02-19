package be.bedroid.medreminder;

import android.app.Activity;
import android.widget.Toast;

public class AbstractActivity extends Activity {

	protected void toast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
	}

}