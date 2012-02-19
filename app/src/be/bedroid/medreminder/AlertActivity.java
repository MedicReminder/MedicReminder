package be.bedroid.medreminder;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AlertActivity extends AbstractActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert);
		TextView t = (TextView) findViewById(R.id.alertMessage);
		String medname = getIntent().getStringExtra("medname");
		t.setText("Please take your " + medname + " pill");
	}

	public void close(View view) {
		finish();
	}

}
