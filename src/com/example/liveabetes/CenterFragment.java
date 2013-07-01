package com.example.liveabetes;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CenterFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.swipeview_fragment_center, container, false);
		rootView.setBackgroundColor(Color.WHITE);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);

	    // set font for two textviews in fragment
	    Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/thinroboto.ttf");
	    TextView averageTV = (TextView)getView().findViewById(R.id.average_bg);
	    TextView titleTV = (TextView)getView().findViewById(R.id.title_center);
	    averageTV.setTypeface(font);
	    titleTV.setTypeface(font);

	    // change average value number in textview
	    String avgValue = fetchAvgFromDB();
	    Log.i(avgValue, "avgValue");
	    averageTV.setText(avgValue);
	}
	
	public void onResume(){
		super.onResume();
		// change average value number in textview
		TextView averageTV = (TextView)getView().findViewById(R.id.average_bg);
	    String avgValue = fetchAvgFromDB();
	    Log.i(avgValue, "avgValue");
	    averageTV.setText(avgValue);
	}
	
	public String fetchAvgFromDB(){
		GlucoseDatabaseHandler db = new GlucoseDatabaseHandler(getActivity());
		int roundDown = 0;
		roundDown = db.getAverage(1).intValue();
		return roundDown + "";
	}
}