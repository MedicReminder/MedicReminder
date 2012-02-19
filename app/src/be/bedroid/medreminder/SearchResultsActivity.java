package be.bedroid.medreminder;

import java.util.ArrayList;
import java.util.Collection;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import be.bedroid.medreminder.content.adapter.MedicineAdapter;
import be.bedroid.medreminder.content.rest.RestClient;
import be.bedroid.medreminder.model.Medicine;

public class SearchResultsActivity extends ListActivity {
	private static MedicineAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			String query = intent.getStringExtra(SearchManager.QUERY);

			ArrayList<Medicine> medicines = RestClient
					.searchMedicines("http://www.cautreels.be/medicines.php?query=" + query);

			adapter = new MedicineAdapter(getApplicationContext(),R.layout.list_item, medicines, query);
			setListAdapter(adapter);

			ListView lv = getListView();
			lv.setTextFilterEnabled(true);

			lv.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					//TODO
				}
			});

		} else {
			setContentView(R.layout.main);
		}

	}

	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		switch (id) {
		case 1:
			dialog = ProgressDialog.show(this, "", "Loading. Please wait ...",
					true);
			break;
		default:
			dialog = null;
		}
		return dialog;
	}

}