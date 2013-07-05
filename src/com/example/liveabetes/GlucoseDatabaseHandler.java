package com.example.liveabetes;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GlucoseDatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	private SQLiteDatabase db;
	
	// Database Version
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "glucoseManager";
	
	// Contacts table name
	public static final String TABLE_ENTRIES = "entries";
	public static final String KEY_ID = null;
	public static final String KEY_TIME = null;
	public static final String KEY_VALUE = null;

	public GlucoseDatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_ENTRIES_TABLE = "CREATE TABLE " + TABLE_ENTRIES + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TIME + " TEXT,"
				+ KEY_VALUE + " TEXT" + ")";
		db.execSQL(CREATE_ENTRIES_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */
	
	public void open() throws SQLException{
		db = this.getWritableDatabase();
	}

	// Adding new contact
	public void addEntry(Entry entry) {
		ContentValues values = new ContentValues();
		values.put(KEY_TIME, entry.getTimecode()); // Contact Name
		values.put(KEY_VALUE, entry.getValue()); // Contact Phone

		// Inserting Row
		db.insert(TABLE_ENTRIES, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	public Entry getEntry(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_ENTRIES, new String[] { KEY_ID,
				KEY_TIME, KEY_VALUE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Entry entry = new Entry(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		// return contact
		db.close();
		return entry;
	}

	// Getting All Contacts
	public List<Entry> getAllEntries() {
		List<Entry> entryList = new ArrayList<Entry>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_ENTRIES;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Entry entry = new Entry();
				entry.setID(Integer.parseInt(cursor.getString(0)));
				entry.setTimecode(cursor.getString(1));
				entry.setValue(cursor.getString(2));
				// Adding contact to list
				entryList.add(entry);
			} while (cursor.moveToNext());
		}
		
		db.close();
		// return contact list
		return entryList;
	}
	
	public Double getAverage(int getNum){
		double average = 0.;
		List<Entry> entries = this.getAllEntries();
		
		if(getNum == 0){	//get all entries and its average
			for( int i = 0; i < entries.size(); i++ )
				average += Integer.parseInt(this.getEntry(i).getValue());
			average /= entries.size();
		}
		else if(getNum == 1){ // get the latest week's entries and its average
			int counter = 0;  // this is in case there are less than 7 entries in the database.
			for( int i = entries.size(); i > 0 && counter < 8; i--, counter++){
				average += Integer.parseInt(this.getEntry(i).getValue());
			}
			average /= counter;
		}
		return average;
	}

	// Updating single contact
	public int updateEntry(Entry entry) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TIME, entry.getTimecode());
		values.put(KEY_VALUE, entry.getValue());

		// updating row
		return db.update(TABLE_ENTRIES, values, KEY_ID + " = ?",
				new String[] { String.valueOf(entry.getID()) });
	}

	// Deleting single contact
	public void deleteEntry(Entry entry) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_ENTRIES, KEY_ID + " = ?",
				new String[] { String.valueOf(entry.getID()) });
		db.close();
	}
	
	public void clear(){
		List<Entry> entries = this.getAllEntries();       

		for (Entry cn : entries)
			this.deleteEntry(cn);
			// Writing Entries to log
	}


	// Getting contacts Count
	public int getEntriesCount() {
		String countQuery = "SELECT  * FROM " + TABLE_ENTRIES;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
		db.close();
		// return count
		return cursor.getCount();
	}
	
	public void deleteDatabase(Context context){
		context.deleteDatabase(DATABASE_NAME);
	}
}