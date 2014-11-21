package edu.fpoly.edict.data;

import java.util.ArrayList;


import edu.fpoly.edict.data.table.ContactTable;
import example.readdatabase.R;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Lá»›p quáº£n lÃ½ dá»¯ liá»‡u sáº½ Ä‘Æ°á»£c hiá»ƒn thá»‹ trÃªn danh sÃ¡ch tá»«
 * 
 * @author Nguyá»…n Ngá»�c Anh - Tel: 0905. 119948 - Email: anhnnst@yahoo.com - Facebook: anhnnst
 * @CreatedDate: 18/10/2014
 * 
 * @Purpose: Nháº±m táº¡o má»™t Ä‘á»“ Ã¡n máº«u giÃºp cho sinh viÃªn Fpoly Ä�Ã  Náºµng cÃ³ thá»ƒ hiá»ƒu vÃ  váº­n dá»¥ng vÃ o lÃ m má»™t Ä‘á»“ Ã¡n thá»±c táº¿
 * @version 1.0.1
 * @Reference: ChÆ°Æ¡ng trÃ¬nh cÃ³ sá»­ dá»¥ng má»™t sá»‘ tÃ i nguyÃªn cá»§a Ä‘á»“ Ã¡n tá»‘t nghiá»‡p cá»§a nhÃ³m TaxiCalling thuá»™c FU Ä�Ã  Náºµng
 */
public class ContactListAdapter extends BaseAdapter {
	ArrayList<ContactListItem> mList;
	Context mContext;
	
	public ContactListAdapter(Context context) {
		mList = new ArrayList<ContactListItem>();
		mContext = context;
	}
	
	public void loadData(){
		ContactDataAdapter adapter = new ContactDataAdapter(mContext);
		
		try {
			adapter.open();
			Cursor cursor =  adapter.getAll();
			while (cursor.moveToNext()){
				ContactListItem item = new ContactListItem();
				item.set_id(cursor.getInt(cursor.getColumnIndex(ContactTable.ID)));
				item.setTu(cursor.getString(cursor.getColumnIndex(ContactTable.FIELD_TU)));
				item.setLoaiTu(cursor.getString(cursor.getColumnIndex(ContactTable.FIELD_LOAITU)));
				item.setNghiaTu(cursor.getString(cursor.getColumnIndex(ContactTable.FIELD_NGHIA)));
				mList.add(item);
//				if (txtTu.getText().toString().equals(item.getTu())) {
////					Log.d("tu", item.getTu());
////				} else {
////					Toast.makeText(getApplicationContext(), "ko cos",
////							Toast.LENGTH_LONG).show();
////				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(mContext, "There is an error.", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null){
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			convertView = inflater.inflate(R.layout.contact, null);
			TextView tv1 = (TextView) convertView.findViewById(R.id.textView1);
			TextView tv2 = (TextView) convertView.findViewById(R.id.textView2);
			TextView tv3 = (TextView) convertView.findViewById(R.id.textView3);
			
//			tv1.setText(""+mList.get(position).getTu());
//			tv2.setText(""+mList.get(position).getLoaiTu());
			tv3.setText(""+mList.get(position).getNghiaTu());
		}
		
		return convertView;
	}

}
