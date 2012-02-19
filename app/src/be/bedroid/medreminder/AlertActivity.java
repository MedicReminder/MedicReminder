package be.bedroid.medreminder;

import android.os.Bundle;
import android.view.View;

public class AlertActivity extends AbstractActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert);
	}

	public void close(View view) {
		finish();
	}

}
