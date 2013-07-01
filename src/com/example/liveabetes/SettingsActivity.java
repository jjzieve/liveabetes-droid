package com.example.liveabetes;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.os.Build;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		// Show the Up button in the action bar.
		// setupActionBar();
		
		// set font for textview
	    Typeface font = Typeface.createFromAsset(getAssets(), "fonts/thinroboto.ttf");
	    TextView nameTV = (TextView)findViewById(R.id.name);
	    nameTV.setTypeface(font);
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
		getMenuInflater().inflate(R.menu.settings, menu);
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

	public void clearData(View view){
		GlucoseDatabaseHandler db1 = new GlucoseDatabaseHandler(this);
		InsulinDatabaseHandler db2 = new InsulinDatabaseHandler(this);
		PressureDatabaseHandler db3 = new PressureDatabaseHandler(this);
		WeightDatabaseHandler db4 = new WeightDatabaseHandler(this);
		db1.deleteDatabase(this);
		db2.deleteDatabase(this);
		db3.deleteDatabase(this);
		db4.deleteDatabase(this);
	}
}
