package be.bedroid.medreminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MedReminderActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settingsMenu:
			Intent intent = new Intent(this, EditPreferencesActivity.class);
			startActivity(intent);
			return true;
		case R.id.medsMenu:
			startActivity(new Intent(this, MedsActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}