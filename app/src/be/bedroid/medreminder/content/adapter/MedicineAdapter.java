package be.bedroid.medreminder.content.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import be.bedroid.medreminder.R;
import be.bedroid.medreminder.model.Medicine;

public class MedicineAdapter extends ArrayAdapter<Medicine> {
	private ArrayList<Medicine> items;
	private String query;

	public MedicineAdapter(Context context, int textViewResourceId,
			ArrayList<Medicine> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}
	
	public MedicineAdapter(Context context, int textViewResourceId,
			ArrayList<Medicine> items, String query) {
		super(context, textViewResourceId, items);
		this.items = items;
		this.query = query;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) getContext().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_item_medicine, null);
		}
		Medicine o = items.get(position);
		if (o != null) {
			TextView textId = (TextView) v.findViewById(R.id.itemMedicineId);
			TextView textName = (TextView) v.findViewById(R.id.itemMedicineName);
			if (textId != null && textName != null) {
				textId.setText(o.getId().toString());
				textName.setText(o.getName());
			}
		}
		return v;
	}

	public ArrayList<Medicine> getItems() {
		return items;
	}
	public void setItems(ArrayList<Medicine> items) {
		this.items = items;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
}
