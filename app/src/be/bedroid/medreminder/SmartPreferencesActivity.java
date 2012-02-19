/*
 Copyright (c) 2011, Sony Ericsson Mobile Communications AB

 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer.

 * Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution.

 * Neither the name of the Sony Ericsson Mobile Communications AB nor the names
 of its contributors may be used to endorse or promote products derived from
 this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package be.bedroid.medreminder;

import be.bedroid.medreminder.R;
import be.bedroid.medreminder.liveware.SampleExtensionService;

import com.sonyericsson.extras.liveware.extension.util.ExtensionUtils;
import com.sonyericsson.extras.liveware.extension.util.notification.NotificationUtil;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * The sample preference activity lets the user toggle start/stop of periodic
 * data insertion. It also allows the user to clear all events associated with
 * this extension.
 */
public class SmartPreferencesActivity extends PreferenceActivity {

	private static final int DIALOG_READ_ME = 1;

	private static final int DIALOG_CLEAR = 2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences_liveware);

		// Handle read me
		Preference preference = findPreference(getText(R.string.preference_key_read_me));
		preference.setOnPreferenceClickListener(new OnPreferenceClickListener() {

			public boolean onPreferenceClick(Preference preference) {
				showDialog(DIALOG_READ_ME);
				return true;
			}
		});

		// Handle active
		preference = (CheckBoxPreference)findPreference(getText(R.string.preference_key_is_active));
		preference.setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

			public boolean onPreferenceChange(Preference preference, Object newValue) {
				if ((Boolean)newValue) {
					startSampleExtensionService();
				} else {
					stopSampleExtensionService();
				}
				return true;
			}
		});

		// Handle clear all events
		preference = findPreference(getString(R.string.preference_key_clear));
		preference.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				showDialog(DIALOG_CLEAR);
				return true;
			}
		});

		// Remove preferences that are not supported by the accessory
		if (!ExtensionUtils.supportsHistory(getIntent())) {
			preference = findPreference(getString(R.string.preference_key_clear));
			getPreferenceScreen().removePreference(preference);
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;

		switch (id) {
		case DIALOG_READ_ME:
			dialog = createReadMeDialog();
			break;
		case DIALOG_CLEAR:
			dialog = createClearDialog();
			break;
		default:
			Log.w(SampleExtensionService.LOG_TAG, "Not a valid dialog id: " + id);
			break;
		}

		return dialog;
	}

	/**
	 * Create the Read me dialog
	 *
	 * @return the Dialog
	 */
	private Dialog createReadMeDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.preference_option_read_me_txt)
		.setTitle(R.string.preference_option_read_me)
		.setIcon(android.R.drawable.ic_dialog_info)
		.setPositiveButton(android.R.string.ok, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		return builder.create();
	}

	/**
	 * Create the Clear events dialog
	 *
	 * @return the Dialog
	 */
	private Dialog createClearDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.preference_option_clear_txt)
		.setTitle(R.string.preference_option_clear)
		.setIcon(android.R.drawable.ic_input_delete)
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				new ClearEventsTask().execute();
			}
		}).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		return builder.create();
	}

	/**
	 * Activate event generation
	 */
	private void startSampleExtensionService() {
		Intent serviceIntent = new Intent(this, SampleExtensionService.class);
		serviceIntent.setAction(SampleExtensionService.INTENT_ACTION_START);
		startService(serviceIntent);
	}

	/**
	 * Cancel event generation
	 */
	private void stopSampleExtensionService() {
		Intent serviceIntent = new Intent(this, SampleExtensionService.class);
		serviceIntent.setAction(SampleExtensionService.INTENT_ACTION_STOP);
		startService(serviceIntent);
	}

	/**
	 * Clear all messaging events
	 */
	private class ClearEventsTask extends AsyncTask<Void, Void, Integer> {

		protected void onPreExecute() {
		}

		protected Integer doInBackground(Void... params) {
			int nbrDeleted = 0;
			nbrDeleted = NotificationUtil.deleteAllEvents(SmartPreferencesActivity.this);
			return nbrDeleted;
		}

		protected void onPostExecute(Integer id) {
			if (id != NotificationUtil.INVALID_ID) {
				Toast.makeText(SmartPreferencesActivity.this, R.string.clear_success,
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(SmartPreferencesActivity.this, R.string.clear_failure,
						Toast.LENGTH_SHORT).show();
			}
		}

	}
}
