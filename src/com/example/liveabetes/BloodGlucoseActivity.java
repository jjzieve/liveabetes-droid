package com.example.liveabetes;


import com.example.liveabetes.MainActivity.AppSectionsPagerAdapter;
import java.util.List;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.annotation.TargetApi;
import android.os.Build;

public class BloodGlucoseActivity extends FragmentActivity {

	private GlucoseDatabaseHandler db;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blood_glucose);
		db = new GlucoseDatabaseHandler(this);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.blood_glucose, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	//Submit button
	public void submit(View view) {
		
		//Year:Month:Day:Hour:Minute:BloodSugar
		EditText bloodSugar = (EditText)findViewById(R.id.editText1);
		TimePicker time = (TimePicker)findViewById(R.id.timePicker1);
		DatePicker date = (DatePicker)findViewById(R.id.datePicker1);

		String stringBloodSugar = bloodSugar.getText().toString();
		String stringTime = time.getCurrentHour() + ":" + time.getCurrentMinute();
		String stringDate = date.getYear() + ":" + date.getMonth() + ":" + date.getDayOfMonth();
		String timeCode = stringDate + ":" + stringTime;

		System.out.println(stringDate + ":" + stringTime + ":" + stringBloodSugar);

		//Local Database
		Log.d("Insert: ", "Inserting .."); 
		db.addEntry(new Entry(timeCode, stringBloodSugar));        

		Log.d("Reading: ", "Reading all contacts.."); 
		List<Entry> contacts = db.getAllEntries();       

		for (Entry cn : contacts) {
			String log = "Id: "+cn.getID()+", Date: " + cn.getTimecode() + ", Blood Glucose: " + cn.getValue();
			// Writing Contacts to log
			Log.d("Name: ", log);
		}	
		
		// add table entry in leftFragment
		addTableEntry(stringBloodSugar, timeCode, view);
	}
	
	public void addTableEntry(String stringBloodSugar, String timeCode, View v){
		FragmentManager fm = getSupportFragmentManager();
		final AppSectionsPagerAdapter mAppSectionsPagerAdapter = MainActivity.mAppSectionsPagerAdapter;
		mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);

        ((ImageButton)findViewById(R.id.imageView1)).setOnTouchListener(new OnTouchListener() {
        	public boolean onTouch(View v, MotionEvent event) {
        		Log.i("Touch!", "here");
//        		mAppSectionsPagerAdapter.notifyDataSetChanged();
				return false;
			}
        });
	}

}
