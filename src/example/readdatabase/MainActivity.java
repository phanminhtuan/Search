package example.readdatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Locale;

import edu.fpoly.edict.data.ContactDataAdapter;
import edu.fpoly.edict.data.ContactListAdapter;
import edu.fpoly.edict.data.ContactListItem;
import edu.fpoly.edict.data.table.ContactTable;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText txtTu;
	Button btnTim,btnDoc;
	ListView listView;
	TextView text;
	TextToSpeech doc;
	
	@Override
	protected void onPause() {
		if (doc != null) {
			doc.stop();
			doc.shutdown();
		}		super.onPause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnDoc=(Button)findViewById(R.id.btnDoc);
		text = (TextView) findViewById(R.id.text);
		txtTu = (EditText) findViewById(R.id.editText);
		btnTim = (Button) findViewById(R.id.btnTim);
		btnTim.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// loadData();
				if (txtTu.getText().toString().equals("")) {
					Toast.makeText(MainActivity.this,
							"Search word must be entered", Toast.LENGTH_LONG)
							.show();
					return;
				}
				ContactDataAdapter adapter = null;
				try {
					adapter = new ContactDataAdapter(MainActivity.this);
					adapter.open();
					Cursor cursor = adapter.getWord(txtTu.getText().toString());
					if (cursor != null && cursor.isFirst()) {
						String meaning = cursor.getString(cursor
								.getColumnIndex(ContactTable.FIELD_NGHIA));
						String data = "<b>"
								+ cursor.getString(cursor
										.getColumnIndex(ContactTable.FIELD_NGHIA))
								+ ":</b>";
						data += "<blockquote>" + meaning + "</blockquote>";
						text.setText(meaning);
						btnDoc.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								doc.speak(txtTu.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);								
							}
						});
					} else {
						Toast.makeText(MainActivity.this,
								"The word doesn't exited in the dictionary",
								Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					Toast.makeText(MainActivity.this,
							"There is an error." + e.getMessage(),
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				} finally {
					try {
						adapter.close();
					} catch (Exception ex) {
					}
				}
			}

		});
		initOriginalDb();
		 loadData();
		 doc = new TextToSpeech(getApplicationContext(),
					new TextToSpeech.OnInitListener() {
						@Override
						public void onInit(int status) {
							if (status != TextToSpeech.ERROR) {
								doc.setLanguage(Locale.UK);
							}
						}
					});
	}

	public void loadData() {
		ArrayList<ContactListItem>list=new ArrayList<ContactListItem>();
		ContactDataAdapter adapter = new ContactDataAdapter(
				getApplicationContext());

		try {
			adapter.open();
			Cursor cursor = adapter.getAll();
			while (cursor.moveToNext()) {
				ContactListItem item = new ContactListItem();
				item.set_id(cursor.getInt(cursor
						.getColumnIndex(ContactTable.ID)));
				item.setTu(cursor.getString(cursor
						.getColumnIndex(ContactTable.FIELD_TU)));
				item.setLoaiTu(cursor.getString(cursor
						.getColumnIndex(ContactTable.FIELD_LOAITU)));
				item.setNghiaTu(cursor.getString(cursor
						.getColumnIndex(ContactTable.FIELD_NGHIA)));
				list.add(item);
				Log.d("danh sách từ", item.getTu());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(MainActivity.this, "There is an error.",
					Toast.LENGTH_SHORT).show();
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
