package com.example.liveabetes;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will display the three primary sections of the app, one at a
     * time.
     */
    ViewPager mViewPager;
    
    // debug TAGs. this is how you display error messages in logcat.
    private static final String TAG = ":( :( :( :( :((((( fuq";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
          
        // Set up the ViewPager with the sections adapter.  
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/** 
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to 
     * one of the sections/tabs/pages. 
     */  
    public class AppSectionsPagerAdapter extends FragmentPagerAdapter {  
  
        public AppSectionsPagerAdapter(FragmentManager fm) {  
            super(fm);
        }  
  
        @Override  
        public Fragment getItem(int position) {  
            // getItem is called to instantiate the fragment for the given page.  
            // Return a DummySectionFragment (defined as a static inner class  
            // below) with the page number as its lone argument.  
        	Fragment fragment = new Fragment();
        	Log.i(TAG, "received an exception");
            switch (position) {  
            case 0:  
                return fragment = new LeftFragment();  
            case 1:
                return fragment = new CenterFragment();  
            case 2:  
                return fragment = new RightFragment();  
            default:  
                break;  
            }
			return fragment;  
        }
  
        @Override  
        public int getCount() {  
            // Show 3 total pages
            return 3;
        }  
  
        @Override  
        public CharSequence getPageTitle(int position) {  
            /*Locale l = Locale.getDefault();  
            switch (position) {  
            case 0:  
                return getString(R.string.title_section1).toUpperCase(l);  
            case 1:  
                return getString(R.string.title_section2).toUpperCase(l);  
            case 2:  
                return getString(R.string.title_section3).toUpperCase(l);  
            }  
            return null;  */
        	return "Section " + (position + 1);
        }  
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
	
	/** Called when the user clicks the Graph button 
	public void graph(View view) {
		Intent intent = new Intent(this, GraphActivity.class);
	    startActivity(intent);
	}*/
	
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

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
