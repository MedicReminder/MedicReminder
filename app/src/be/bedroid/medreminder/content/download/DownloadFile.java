package be.bedroid.medreminder.content.download;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import be.bedroid.medreminder.content.MedicineContentProvider;
import be.bedroid.medreminder.model.Medicine;

public class DownloadFile extends AsyncTask<String, Integer, String>{
	
	private Context context;
	
	public DownloadFile( Context context) {
		this.context = context;
	}
	
    @Override
    protected String doInBackground(String... website) {
        int count;
        String result;
    	HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(website[0]);
		ResponseHandler<String> handler = new BasicResponseHandler();
		request.addHeader("accept", "application/json; charset=UTF-8");
		result = "";
		try {
			result = httpclient.execute(request, handler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpclient.getConnectionManager().shutdown();

		JSONArray ja = null;
		try {
			ja = new JSONArray(result);

			ContentValues[] contentValues = new ContentValues[ja.length()];
			
			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo = (JSONObject) ja.get(i);
				
				ContentValues values = new ContentValues();
				values.put(Medicine.NAME, jo.getString("medicine"));

				contentValues[i] = values;
			}
			
			context.getContentResolver().bulkInsert(MedicineContentProvider.CONTENT_URI, contentValues);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
        return "";
    }
    }