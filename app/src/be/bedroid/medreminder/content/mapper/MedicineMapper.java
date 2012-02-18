package be.bedroid.medreminder.content.mapper;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import be.bedroid.medreminder.model.Medicine;

public class MedicineMapper {
	/**
	 * Gets the first (or only) result from the given cursor and maps it to a Medicine Object
	 * @param cursor
	 * @return Medicine
	 */
	public static Medicine mapCursorToMedicine(Cursor cursor) {
		cursor.moveToFirst();
		
		Medicine medicine = new Medicine();
		
		medicine.setId(cursor.getInt(cursor.getColumnIndex(Medicine.ID)));
		medicine.setMethod(cursor.getString(cursor.getColumnIndex(Medicine.METHOD)));
		medicine.setName(cursor.getString(cursor.getColumnIndex(Medicine.NAME)));
		
		return medicine;
	}
	
	/**
	 * Gets all the medicines from the given cursor and maps them to a List of Medicine Objects
	 * @param cursor
	 * @return List of Medicines
	 */
	public static List<Medicine> mapCursorToMedicines(Cursor cursor) {
		List<Medicine> medicines = new ArrayList<Medicine>();
		
		while(cursor.moveToNext()) {
			Medicine medicine = new Medicine();
			medicine.setId(cursor.getInt(cursor.getColumnIndex(Medicine.ID)));
			medicine.setMethod(cursor.getString(cursor.getColumnIndex(Medicine.METHOD)));
			medicine.setName(cursor.getString(cursor.getColumnIndex(Medicine.NAME)));
			medicines.add(medicine);
		}
		
		return medicines;
	}
}
