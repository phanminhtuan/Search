package edu.fpoly.edict.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import edu.fpoly.edict.data.table.ContactTable;

/**
 * Chá»©a cÃ¡c chá»©c nÄƒng cÆ¡ báº£n Ä‘á»ƒ quáº£n lÃ½ ná»™i dung cá»§a báº£ng Word trong csdl
 * 
 * @author Nguyá»…n Ngá»�c Anh - Tel: 0905. 119948 - Email: anhnnst@yahoo.com - Facebook: anhnnst
 * @CreatedDate: 18/10/2014
 * 
 * @Purpose: Nháº±m táº¡o má»™t Ä‘á»“ Ã¡n máº«u giÃºp cho sinh viÃªn Fpoly Ä�Ã  Náºµng cÃ³ thá»ƒ hiá»ƒu vÃ  váº­n dá»¥ng vÃ o lÃ m má»™t Ä‘á»“ Ã¡n thá»±c táº¿
 * @version 1.0.1
 * @Reference: ChÆ°Æ¡ng trÃ¬nh cÃ³ sá»­ dá»¥ng má»™t sá»‘ tÃ i nguyÃªn cá»§a Ä‘á»“ Ã¡n tá»‘t nghiá»‡p cá»§a nhÃ³m TaxiCalling thuá»™c FU Ä�Ã  Náºµng
 */
public class ContactDataAdapter {
	private DataSchemaHelper mHelper;
	private Context mContext;
	private SQLiteDatabase db ;
		
	public ContactDataAdapter(Context mContext) {
		this.mContext = mContext;
		
		mHelper= new DataSchemaHelper(mContext);
	}
	/**
	 * Má»Ÿ káº¿t ná»‘i vá»›i CSDL
	 */
	public void open(){
		db = mHelper.getWritableDatabase();
	}
	/**
	 * Ä�Ã³ng káº¿t ná»‘i CSDL
	 */
	public void close(){
		db.close();
	}

	/**
	 * Nháº­p tá»« má»›i vÃ o báº£ng Word.
	 * @param key
	 * @param meaning
	 * @return
	 */
	public long addWord(String key,String loai, String meaning) {
		// CREATE A CONTENTVALUE OBJECT
		ContentValues cv = new ContentValues();
		cv.put(ContactTable.FIELD_TU, key);
		cv.put(ContactTable.FIELD_LOAITU, loai);
		cv.put(ContactTable.FIELD_NGHIA, meaning);

		long result = db.insert(ContactTable.TABLE_NAME, ContactTable.FIELD_TU, cv);
		
		Log.d("Hello", "insert " + result);
		return result;
	}
	/**
	 * Cáº­p nháº­t thÃ´ng tin cá»§a má»™t tá»«
	 * @param id
	 * @param key
	 * @param meaning
	 * @return
	 */
	public long updateWord(String id, String key, String meaning){
		ContentValues values = new ContentValues();
		values.put(ContactTable.FIELD_TU, key);
		values.put(ContactTable.FIELD_NGHIA, meaning);
		
		return db.update(ContactTable.TABLE_NAME, values,
				ContactTable.ID +" = " + id , null);
	}
	
	/**
	 * XÃ³a tá»«
	 * @param id
	 * @return
	 */
	public boolean deleteWord(int id){
		return db.delete(ContactTable.TABLE_NAME, ContactTable.ID + "=" + id, null)> 0;
	}
	/**
	 * Tráº£ vá»� táº¥t cáº£ cÃ¡c tá»« cÃ³ trong tá»« Ä‘iá»ƒn
	 * @return
	 */
	public Cursor getAll(){
		Cursor result = db.query(ContactTable.TABLE_NAME,
				new String[]{ContactTable.ID, ContactTable.FIELD_TU,ContactTable.FIELD_LOAITU, ContactTable.FIELD_NGHIA}
				, null, null, null, null, null);
		return result;
	}
	/**
	 * Truy váº¥n ná»™i dung cá»§a tá»« theo ID
	 * @param id
	 * @return
	 */
	public Cursor getWord(int id){
		Cursor cursor = db.query(ContactTable.TABLE_NAME, 
				new String[]{ContactTable.ID, ContactTable.FIELD_TU,ContactTable.FIELD_LOAITU, ContactTable.FIELD_NGHIA}, ContactTable.ID + "=" + id, null, null, null, null);
		
		if (cursor != null){
			cursor.moveToFirst();
		}
		return cursor;
	}
	
	/**
	 * Truy váº¥n ná»™i dung tá»« theo key
	 * @param key
	 * @return
	 */
	public Cursor getWord(String key){
		Cursor cursor = db.query(ContactTable.TABLE_NAME, 
				new String[]{ContactTable.ID, ContactTable.FIELD_TU,ContactTable.FIELD_LOAITU, ContactTable.FIELD_NGHIA}, ContactTable.FIELD_TU + " like '%" + key.trim() +"%'", null, null, null, null);
		
		if (cursor != null){
			cursor.moveToFirst();
		}
		return cursor;
	}
	
	/**
	 * Truy váº¥n nghÄ©a cá»§a tá»« dá»±a vÃ o ID
	 * @param id
	 * @return
	 */
	public String getWordMeaning(int id){
		Cursor cursor = db.query(ContactTable.TABLE_NAME, 
				new String[]{ContactTable.ID, ContactTable.FIELD_TU,ContactTable.FIELD_LOAITU, ContactTable.FIELD_NGHIA}, ContactTable.ID + "=" + id, null, null, null, null);
		
		if (cursor != null){
			cursor.moveToFirst();
			
			return cursor.getString(cursor.getColumnIndex(ContactTable.FIELD_NGHIA));
		}
		return "";
	}
}
