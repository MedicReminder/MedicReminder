package be.bedroid.medreminder;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import be.bedroid.medreminder.content.ReminderContentProvider;
import be.bedroid.medreminder.content.download.DownloadFile;
import be.bedroid.medreminder.model.Medicine;


public class MedReminderActivity extends Activity {

	private ProgressDialog mProgressDialog = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		OnClickListener testButton = new OnClickListener() {
			public void onClick(View v) {			
				mProgressDialog = new ProgressDialog(MedReminderActivity.this);
				mProgressDialog.setMessage("A message");
				mProgressDialog.setIndeterminate(false);
				mProgressDialog.setMax(100);
				mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				DownloadFile downloadFile = new DownloadFile(MedReminderActivity.this);
				downloadFile.execute("http://www.cautreels.be/medicines.json");
			}
		};

		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(testButton);
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
		case R.id.alarmMenu:
			startActivity(new Intent(this, AlertTestLauncher.class));
			return true;
		case R.id.settingsMenu:
			Intent intent = new Intent(this, EditPreferencesActivity.class);
			startActivity(intent);
			return true;
		case R.id.medsMenu:
			startActivity(new Intent(this, MedsActivity.class));
			return true;
		case R.id.remindersMenu:
			startActivity(new Intent(this, RemindersActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void onProgressUpdate(String... args){
        // here you will have to update the progressbar
        // with something like
        mProgressDialog.setProgress(Integer.parseInt(args[0]));
    }
}


