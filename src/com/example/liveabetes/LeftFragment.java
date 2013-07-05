package com.example.liveabetes;

import java.util.List;

import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class LeftFragment extends ListFragment{
	ViewGroup rootView = null;
	SQLiteOpenHelper db;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = (ViewGroup) inflater.inflate(R.layout.bp_fragment_left, container, false);
		rootView.setBackgroundColor(Color.WHITE);
		
		databaseSetup();
		
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	}
	
	public void onResume(Bundle savedInstanceState){
		if(MainActivity.viewHandler == 0){
			((GlucoseDatabaseHandler) db).open();
		}
		super.onResume();
/*		switch(MainActivity.viewHandler){
		case(0):
			rootView = (ViewGroup) getLayoutInflater(savedInstanceState).inflate(R.layout.swipeview_fragment_left, rootView, false);
		case(1):
			rootView = (ViewGroup) getLayoutInflater(savedInstanceState).inflate(R.layout.ins_fragment_left, rootView, false);
			break;
		case(2):
			rootView = (ViewGroup) getLayoutInflater(savedInstanceState).inflate(R.layout.wt_fragment_left, rootView, false);
			break;
		case(3):
			rootView = (ViewGroup) getLayoutInflater(savedInstanceState).inflate(R.layout.bp_fragment_left, rootView, false);
			// set font for textview
			TextView bpTitleTV = (TextView)getView().findViewById(R.id.bp_left_title);
			Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/thinroboto.ttf");
			bpTitleTV.setTypeface(font);
			break;
		default:
			break;
		}*/
	}
	
	@Override
	public void onPause(){
		db.close();
		super.onPause();
	}
	
	void databaseSetup(){
		// blood glucose database
		if(MainActivity.viewHandler == 0){
			db = new GlucoseDatabaseHandler(getActivity());
			((GlucoseDatabaseHandler) db).open();
			Log.i("databaseSetup", "it works?");
//			List<Entry> values = ((GlucoseDatabaseHandler) db).getAllEntries();
//			ArrayAdapter<Entry> adapter = new ArrayAdapter<Entry>(getActivity(), android.R.layout.activity_list_item, values);
//			setListAdapter(adapter);
		}
	}
}