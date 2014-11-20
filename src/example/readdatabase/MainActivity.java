package example.readdatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


import edu.fpoly.edict.data.ContactDataAdapter;
import edu.fpoly.edict.data.ContactListAdapter;
import edu.fpoly.edict.data.ContactListItem;
import edu.fpoly.edict.data.table.ContactTable;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	initOriginalDb();
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		ContactListAdapter adapter = new ContactListAdapter(this);
		adapter.loadData();
		listView.setAdapter(adapter);
		
		loadData();
    }

	public void loadData(){
		ContactDataAdapter adapter = new ContactDataAdapter(this);
		
		try {
			adapter.open();
			Cursor cursor =  adapter.getAll();
			while (cursor.moveToNext()){
				ContactListItem item = new ContactListItem();
				item.set_id(cursor.getInt(cursor.getColumnIndex(ContactTable.ID)));
				item.setTu(cursor.getString(cursor.getColumnIndex(ContactTable.FIELD_TU)));
				item.setLoaiTu(cursor.getString(cursor.getColumnIndex(ContactTable.FIELD_LOAITU)));
				item.setNghiaTu(cursor.getString(cursor.getColumnIndex(ContactTable.FIELD_NGHIA)));
				Log.d("Data", item.getTu() + "----" + item.getLoaiTu()+""+item.getNghiaTu());
			}	
		} catch (Exception e) {
			Toast.makeText(this, "There is an error.", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	private void initOriginalDb() {
		try {
			// store database file in assets folder of your project. after that
			// copying to the database folder in runtime.
			// the names of all files in assets folder are in lowercase
			String destPath = "/data/data/" + getPackageName() + "/databases";
			File file = new File(destPath);
			if (!file.exists()) {
				file.mkdir();
				file.createNewFile();

				// ---copy the db from the assets folder into
				// the databases folder---
				copyDB(getBaseContext().getAssets().open("mydata"),
						new FileOutputStream(destPath + "/mydata"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void copyDB(InputStream inputStream, OutputStream outputStream)
			throws IOException {
		// ---copy 1K bytes at a time---
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, length);
		}
		inputStream.close();
		outputStream.close();
	}

}
