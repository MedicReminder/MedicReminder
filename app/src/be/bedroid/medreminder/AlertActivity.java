package be.bedroid.medreminder;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class AlertActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert);
	}

	public void close(View view) {
		finish();
	}

}
