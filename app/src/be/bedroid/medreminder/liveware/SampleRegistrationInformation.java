package be.bedroid.medreminder.liveware;

import be.bedroid.medreminder.R;
import be.bedroid.medreminder.SmartPreferencesActivity;

import com.sonyericsson.extras.liveware.aef.notification.Notification;
import com.sonyericsson.extras.liveware.aef.registration.Registration;
import com.sonyericsson.extras.liveware.extension.util.ExtensionUtils;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class SampleRegistrationInformation extends RegistrationInformation {

	final Context mContext;

	/**
	 * Create notification registration object
	 *
	 * @param context The context
	 */
	protected SampleRegistrationInformation(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context == null");
		}
		mContext = context;
	}

	@Override
	public int getRequiredNotificationApiVersion() {
		return 1;
	}

	@Override
	public int getRequiredWidgetApiVersion() {
		return 0;
	}

	@Override
	public int getRequiredControlApiVersion() {
		return 0;
	}

	@Override
	public int getRequiredSensorApiVersion() {
		return 0;
	}

	@Override
	public ContentValues getExtensionRegistrationConfiguration() {
		String extensionIcon = ExtensionUtils.getUriString(mContext,
				R.drawable.icon_extension);
		String iconHostapp = ExtensionUtils.getUriString(mContext,
				R.drawable.ic_logo);

		String configurationText = mContext.getString(R.string.configuration_text);
		String extensionName = mContext.getString(R.string.extension_name);

		ContentValues values = new ContentValues();
		values.put(Registration.ExtensionColumns.CONFIGURATION_ACTIVITY,
				SmartPreferencesActivity.class.getName());
		values.put(Registration.ExtensionColumns.CONFIGURATION_TEXT, configurationText);
		values.put(Registration.ExtensionColumns.EXTENSION_ICON_URI, extensionIcon);
		values.put(Registration.ExtensionColumns.EXTENSION_KEY,
				SampleExtensionService.EXTENSION_KEY);
		values.put(Registration.ExtensionColumns.HOST_APP_ICON_URI, iconHostapp);
		values.put(Registration.ExtensionColumns.NAME, extensionName);
		values.put(Registration.ExtensionColumns.NOTIFICATION_API_VERSION,
				getRequiredNotificationApiVersion());
		values.put(Registration.ExtensionColumns.PACKAGE_NAME, mContext.getPackageName());

		return values;
	}

	@Override
	public ContentValues[] getSourceRegistrationConfigurations() {
		List<ContentValues> bulkValues = new ArrayList<ContentValues>();
		bulkValues
		.add(getSourceRegistrationConfiguration(SampleExtensionService.EXTENSION_SPECIFIC_ID));
		return bulkValues.toArray(new ContentValues[bulkValues.size()]);
	}

	/**
	 * Get source configuration associated with extensions specific id
	 *
	 * @param extensionSpecificId
	 * @return The source configuration
	 */
	public ContentValues getSourceRegistrationConfiguration(String extensionSpecificId) {
		ContentValues sourceValues = null;

		String iconSource1 = ExtensionUtils.getUriString(mContext,
				R.drawable.icn_30x30_message_notification);
		String iconSource2 = ExtensionUtils.getUriString(mContext,
				R.drawable.icn_18x18_message_notification);
		String iconBw = ExtensionUtils.getUriString(mContext,
				R.drawable.icn_18x18_black_white_message_notification);
		String actionEvent1 = mContext.getString(R.string.action_event_1);
		String textToSpeech = mContext.getString(R.string.text_to_speech);

		sourceValues = new ContentValues();
		sourceValues.put(Notification.SourceColumns.ACTION_1, actionEvent1);
		sourceValues.put(Notification.SourceColumns.ENABLED, true);
		sourceValues.put(Notification.SourceColumns.ICON_URI_1, iconSource1);
		sourceValues.put(Notification.SourceColumns.ICON_URI_2, iconSource2);
		sourceValues.put(Notification.SourceColumns.ICON_URI_BLACK_WHITE, iconBw);
		sourceValues.put(Notification.SourceColumns.UPDATE_TIME, System.currentTimeMillis());
		sourceValues.put(Notification.SourceColumns.NAME, mContext.getString(R.string.source_name));
		sourceValues.put(Notification.SourceColumns.EXTENSION_SPECIFIC_ID, extensionSpecificId);
		sourceValues.put(Notification.SourceColumns.PACKAGE_NAME, mContext.getPackageName());
		sourceValues.put(Notification.SourceColumns.TEXT_TO_SPEECH, textToSpeech);

		return sourceValues;
	}

}
