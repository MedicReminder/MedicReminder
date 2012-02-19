package be.bedroid.medreminder.content.rest;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import be.bedroid.medreminder.model.Medicine;

public class RestClient {
	public static ArrayList<Medicine> searchMedicines(String url) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		ResponseHandler<String> handler = new BasicResponseHandler();
		request.addHeader("accept", "application/json; charset=UTF-8");
		String result = "";
		try {
			result = httpclient.execute(request, handler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpclient.getConnectionManager().shutdown();

		ArrayList<Medicine> medicines = new ArrayList<Medicine>();

		JSONArray ja = null;
		try {
			ja = new JSONArray(result);

			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo = (JSONObject) ja.get(i);
				Medicine medicine = new Medicine();
				medicine.setId(jo.getInt("id"));
				medicine.setName(jo.getString("name"));
				medicines.add(medicine);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return medicines;
	}

}
