package com.example.liveabetes;

import java.util.Arrays;
import java.util.List;

import com.androidplot.series.XYSeries;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RightFragment extends Fragment{
	
	private XYPlot mySimpleXYPlot;
	
	private static final String TAG = "RightFrag";
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.swipeview_fragment_right, container, false);
		
		displayGraph(rootView);

		return rootView;
	}
	
	public void displayGraph(ViewGroup rootView){
		
		//create the GlucoseDatabaseHandler
		GlucoseDatabaseHandler db = new GlucoseDatabaseHandler(this.getActivity());
		Log.d("Reading: ", "Reading all contacts.."); 
		List<Entry> contacts = db.getAllEntries(); 
		
		Log.i(TAG, "1");

		Number[] series1Numbers = new Number[contacts.size()];
		Number[] series2Numbers = new Number[contacts.size()];
		
		Log.i(TAG, "2");
		
		System.out.println(contacts.size());
		
		for (int i = 0; i < contacts.size(); i++) {
			series1Numbers[i] = contacts.get(i).getID();
			series2Numbers[i] = Integer.parseInt(contacts.get(i).getValue());
		}
		
		Log.i(TAG, "3");
		
		//for (int i = 0; i < contacts.size; )

		// initialize our XYPlot reference:
		mySimpleXYPlot = (XYPlot) rootView.findViewById(R.id.mySimpleXYPlot);
		
		Log.i(TAG, "4");

		// Turn the above arrays into XYSeries':
		XYSeries series1 = new SimpleXYSeries(
				Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
				SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
				"Series1");                             // Set the display title of the series

		Log.i(TAG, "5");
		
		// same as above
		XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");
		
		Log.i(TAG, "6");

		// Create a formatter to use for drawing a series using LineAndPointRenderer:
		LineAndPointFormatter series1Format = new LineAndPointFormatter(
				Color.rgb(0, 200, 0),                   // line color
				Color.rgb(0, 100, 0),                   // point color
				null,(PointLabelFormatter) null);                                  // fill color (none)
		
		Log.i(TAG, "7");

		// add a new series' to the xyplot:
		mySimpleXYPlot.addSeries(series1, series1Format);
		
		Log.i(TAG, "8");

		// same as above:
		mySimpleXYPlot.addSeries(series2,
				new LineAndPointFormatter(Color.rgb(0, 0, 200), Color.rgb(0, 0, 100), null, (PointLabelFormatter) null));
		
		Log.i(TAG, "9");

		// reduce the number of range labels
		mySimpleXYPlot.setTicksPerRangeLabel(3);
		
		Log.i(TAG, "10");

		// by default, AndroidPlot displays developer guides to aid in laying out your plot.
		// To get rid of them call disableAllMarkup():
		//mySimpleXYPlot.disableAllMarkup();//Deprecated not needed anymore
	}
}