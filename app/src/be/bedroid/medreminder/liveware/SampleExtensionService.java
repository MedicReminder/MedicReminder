package be.bedroid.medreminder.liveware;

import be.bedroid.medreminder.AlertReceiver;
import be.bedroid.medreminder.R;

import com.sonyericsson.extras.liveware.aef.notification.Notification;
import com.sonyericsson.extras.liveware.extension.util.ExtensionService;
import com.sonyericsson.extras.liveware.extension.util.ExtensionUtils;
import com.sonyericsson.extras.liveware.extension.util.notification.NotificationUtil;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;
import android.widget.Toast;

/**
 * The sample extension service handles extension registration and inserts
 * data into the notification database.
 */
public class SampleExtensionService extends ExtensionService {

	/**
	 * Extensions specific id for the source
	 */
	public static final String EXTENSION_SPECIFIC_ID = "EXTENSION_SPECIFIC_ID_SAMPLE_NOTIFICATION";

	/**
	 * Extension key
	 */
	public static final String EXTENSION_KEY = "com.sonyericsson.extras.liveware.extension.notificationsample.key";

	/**
	 * Log tag
	 */
	public static final String LOG_TAG = "MedReminderSmartService";

	/**
	 * Starts periodic insert of data handled in onStartCommand()
	 */
	public static final String INTENT_ACTION_START = "com.sonyericsson.extras.liveware.extension.notificationsample.action.start";

	/**
	 * Stop periodic insert of data, handled in onStartCommand()
	 */
	public static final String INTENT_ACTION_STOP = "com.sonyericsson.extras.liveware.extension.notificationsample.action.stop";


	public SampleExtensionService() {
		super(EXTENSION_KEY);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see android.app.Service#onCreate()
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(LOG_TAG, "onCreate");
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see android.app.Service#onStartCommand()
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int retVal = super.onStartCommand(intent, flags, startId);
		if (intent != null) {
			String message = intent.getStringExtra(AlertReceiver.EXTRA_MESSAGE);
			Log.i(LOG_TAG, "Received message: " + message);
			if (message != null) {
				addData("MedReminder", message);
				stopSelfCheck();
			}
		}

		return retVal;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see android.app.Service#onDestroy()
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(LOG_TAG, "onDestroy");
	}


	private void addData(String name, String message) {
		long time = System.currentTimeMillis();
		long sourceId = NotificationUtil.getSourceId(this, EXTENSION_SPECIFIC_ID);
		if (sourceId == NotificationUtil.INVALID_ID) {
			Log.e(LOG_TAG, "Failed to insert data");
			return;
		}
		String profileImage = ExtensionUtils.getUriString(this,
				R.drawable.widget_default_userpic_bg);

		ContentValues eventValues = new ContentValues();
		eventValues.put(Notification.EventColumns.EVENT_READ_STATUS, false);
		eventValues.put(Notification.EventColumns.DISPLAY_NAME, name);
		eventValues.put(Notification.EventColumns.MESSAGE, message);
		eventValues.put(Notification.EventColumns.PERSONAL, 1);
		eventValues.put(Notification.EventColumns.PROFILE_IMAGE_URI, profileImage);
		eventValues.put(Notification.EventColumns.PUBLISHED_TIME, time);
		eventValues.put(Notification.EventColumns.SOURCE_ID, sourceId);

		try {
			getContentResolver().insert(Notification.Event.URI, eventValues);
		} catch (IllegalArgumentException e) {
			Log.e(LOG_TAG, "Failed to insert event", e);
		} catch (SecurityException e) {
			Log.e(LOG_TAG, "Failed to insert event, is Live Ware Manager installed?", e);
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to insert event", e);
		}
	}

	@Override
	protected void onViewEvent(Intent intent) {
		String action = intent.getStringExtra(Notification.Intents.EXTRA_ACTION);
		int eventId = intent.getIntExtra(Notification.Intents.EXTRA_EVENT_ID, -1);
		if (Notification.SourceColumns.ACTION_1.equals(action)) {
			doAction1(eventId);
		}
	}

	@Override
	protected void onRefreshRequest() {
		// Do nothing here, only relevant for polling extensions, this
		// extension is always up to date
	}

	/**
	 * Show toast with event information
	 *
	 * @param eventId The event id
	 */
	public void doAction1(int eventId) {
		Log.d(LOG_TAG, "doAction1 event id: " + eventId);
		Cursor cursor = null;
		try {
			String name = "";
			String message = "";
			cursor = getContentResolver().query(Notification.Event.URI, null,
					Notification.EventColumns._ID + " = " + eventId, null, null);
			if (cursor != null && cursor.moveToFirst()) {
				int nameIndex = cursor.getColumnIndex(Notification.EventColumns.DISPLAY_NAME);
				int messageIndex = cursor.getColumnIndex(Notification.EventColumns.MESSAGE);
				name = cursor.getString(nameIndex);
				message = cursor.getString(messageIndex);
			}

			String toastMessage = getText(R.string.action_event_1) + ", Event: " + eventId
					+ ", Name: " + name + ", Message: " + message;
			Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
			// Deleting all the previous messages because
			// we received a confirmation from the watch.
			getContentResolver().delete(Notification.Event.URI, null, null);
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to query event", e);
		} catch (SecurityException e) {
			Log.e(LOG_TAG, "Failed to query event", e);
		} catch (IllegalArgumentException e) {
			Log.e(LOG_TAG, "Failed to query event", e);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	}

	/**
	 * Called when extension and sources has been successfully registered.
	 * Override this method to take action after a successful registration.
	 */
	@Override
	public void onRegisterResult(boolean result) {
		super.onRegisterResult(result);
		Log.d(LOG_TAG, "onRegisterResult");
	}

	@Override
	protected RegistrationInformation getRegistrationInformation() {
		return new SampleRegistrationInformation(this);
	}

	/* (non-Javadoc)
	 * @see com.sonyericsson.extras.liveware.aef.util.ExtensionService#keepRunningWhenConnected()
	 */
	@Override
	protected boolean keepRunningWhenConnected() {
		return false;
	}
}
