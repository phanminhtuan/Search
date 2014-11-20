package edu.fpoly.edict.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import edu.fpoly.edict.data.table.ContactTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Quản lý cơ sở dữ liệu. 
 * 	
 * 
 * @author Nguyễn Ngọc Anh - Tel: 0905. 119948 - Email: anhnnst@yahoo.com - Facebook: anhnnst
 * @CreatedDate: 18/10/2014
 * 
 * @Purpose: Nhằm tạo một đồ án mẫu giúp cho sinh viên Fpoly Đà Nẵng có thể hiểu và vận dụng vào làm một đồ án thực tế
 * @version 1.0.1
 * @Reference: Chương trình có sử dụng một số tài nguyên của đồ án tốt nghiệp của nhóm TaxiCalling thuộc FU Đà Nẵng
 */
public class DataSchemaHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "mydata";
	// TOGGLE THIS NUMBER FOR UPDATING TABLES AND DATABASE
	private static final int DATABASE_VERSION = 1;

	DataSchemaHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("Hello", ContactTable.SQL_CREATE_TABLE);
		// CREATE STUDENTS TABLE
		db.execSQL(ContactTable.SQL_CREATE_TABLE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("LOG_TAG", "Upgrading database from version " + oldVersion
				+ " to " + newVersion + ", which will destroy all old data");
		// KILL PREVIOUS TABLES IF UPGRADED
		db.execSQL(ContactTable.SQL_DROP_TABLE);
		
		// CREATE NEW INSTANCE OF SCHEMA
		onCreate(db);
	}
}
