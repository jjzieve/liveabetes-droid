package com.example.liveabetes;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

import com.androidplot.series.XYSeries;
import com.androidplot.ui.AnchorPosition;
import com.androidplot.ui.SizeLayoutType;
import com.androidplot.ui.SizeMetrics;
import com.androidplot.ui.widget.Widget;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XLayoutStyle;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYStepMode;
import com.androidplot.xy.YLayoutStyle;

import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RightFragment extends Fragment{

	private XYPlot mySimpleXYPlot;
	private ViewGroup rootView;

	private static final String TAG = "RightFrag";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = (ViewGroup) inflater.inflate(R.layout.swipeview_fragment_right, container, false);
		rootView.setBackgroundColor(Color.WHITE);
		return rootView;
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    displayGraph();
	}

	public void displayGraph(){

		//create the GlucoseDatabaseHandler
		GlucoseDatabaseHandler db = new GlucoseDatabaseHandler(this.getActivity());
		Log.d("Reading: ", "Reading all contacts.."); 
		List<Entry> contacts = db.getAllEntries(); 
		
		Log.i(TAG, "CREATE");

		Log.i(TAG, "1");

		Number[] series1Numbers = new Number[contacts.size()];
		Number[] series2Numbers = new Number[contacts.size()];

		Log.i(TAG, "2");

		for (int i = 0; i < contacts.size(); i++) {
			series1Numbers[i] = contacts.get(i).getID();
			series2Numbers[i] = Integer.parseInt(contacts.get(i).getValue());
		}

		Log.i(TAG, "3");

		//for (int i = 0; i < contacts.size; )

		// initialize our XYPlot reference:
		mySimpleXYPlot = (XYPlot) getView().findViewById(R.id.mySimpleXYPlot);
		mySimpleXYPlot.setBorderStyle(XYPlot.BorderStyle.NONE, null, null);
	    mySimpleXYPlot.setPlotMargins(0, 0, 0, 0);
	    mySimpleXYPlot.setPlotPadding(0, 0, 0, 0);
	    mySimpleXYPlot.setGridPadding(0, 10, 5, 0);

	    mySimpleXYPlot.setBackgroundColor(Color.WHITE);

	    mySimpleXYPlot.position(
	            mySimpleXYPlot.getGraphWidget(),
	            0,
	            XLayoutStyle.ABSOLUTE_FROM_LEFT,
	            0,
	            YLayoutStyle.RELATIVE_TO_CENTER,
	            AnchorPosition.LEFT_MIDDLE);

	    mySimpleXYPlot.getGraphWidget().getBackgroundPaint().setColor(Color.WHITE);
	    mySimpleXYPlot.getGraphWidget().getGridBackgroundPaint().setColor(Color.WHITE);

	    mySimpleXYPlot.getGraphWidget().getDomainLabelPaint().setColor(Color.BLACK);
	    mySimpleXYPlot.getGraphWidget().getRangeLabelPaint().setColor(Color.BLACK);

	    mySimpleXYPlot.getGraphWidget().getDomainOriginLabelPaint().setColor(Color.BLACK);
	    mySimpleXYPlot.getGraphWidget().getDomainOriginLinePaint().setColor(Color.BLACK);
	    mySimpleXYPlot.getGraphWidget().getRangeOriginLinePaint().setColor(Color.BLACK);
	    
	    

	    // Domain
	    mySimpleXYPlot.setDomainStep(XYStepMode.INCREMENT_BY_VAL, contacts.size());     
	    mySimpleXYPlot.setDomainValueFormat(new DecimalFormat("0"));
	    mySimpleXYPlot.setDomainStepValue(1);

	    //Range
	    mySimpleXYPlot.setRangeBoundaries(0, 500, BoundaryMode.FIXED);
	    mySimpleXYPlot.setRangeStepValue(10);
	    //mySimpleXYPlot.setRangeStep(XYStepMode.SUBDIVIDE, values.length);
	    mySimpleXYPlot.setRangeValueFormat(new DecimalFormat("0"));

	    //Remove legend
	    mySimpleXYPlot.getLayoutManager().remove(mySimpleXYPlot.getLegendWidget());
	    mySimpleXYPlot.getLayoutManager().remove(mySimpleXYPlot.getDomainLabelWidget());
	    mySimpleXYPlot.getLayoutManager().remove(mySimpleXYPlot.getRangeLabelWidget());
	    mySimpleXYPlot.getLayoutManager().remove(mySimpleXYPlot.getTitleWidget());
	    
	    //Get rid of black borders
	    Widget gw = mySimpleXYPlot.getGraphWidget();
	    SizeMetrics sm = new SizeMetrics(0,SizeLayoutType.FILL, 0,SizeLayoutType.FILL);
	    gw.setSize(sm);
	    

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
	}
	
	
}