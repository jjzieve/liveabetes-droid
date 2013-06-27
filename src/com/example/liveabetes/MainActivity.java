package com.example.liveabetes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
//import android.widget.EditText;
import android.view.Window;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/* remove top title bar cuz it's ugly */
//		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** Called when the user clicks the Blood Glucose button */
	public void bloodGlucose(View view) {
		Intent intent = new Intent(this, BloodGlucoseActivity.class);
	    startActivity(intent);
	}

	/** Called when the user clicks the Blood Pressure button */
	public void bloodPressure(View view) {
		Intent intent = new Intent(this, BloodPressureActivity.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Graph button */
	public void graph(View view) {
		Intent intent = new Intent(this, GraphActivity.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Insulin button */
	public void insulin(View view) {
		Intent intent = new Intent(this, InsulinActivity.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Weight button */
	public void weight(View view) {
		Intent intent = new Intent(this, WeightActivity.class);
	    startActivity(intent);
	}
	
	/** Called when the user clicks the Settings button */
	public void settings(View view) {
		Intent intent = new Intent(this, SettingsActivity.class);
	    startActivity(intent);
	}
}
