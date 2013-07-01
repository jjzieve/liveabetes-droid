package com.example.liveabetes;

import android.support.v4.app.FragmentManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LeftFragment extends Fragment{
	ViewGroup rootView = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = (ViewGroup) inflater.inflate(R.layout.bp_fragment_left, container, false);
		
		rootView.setBackgroundColor(Color.WHITE);
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	}
	
	public void onResume(Bundle savedInstanceState){
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
}