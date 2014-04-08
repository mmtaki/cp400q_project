package com.LRA;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LRA.GridArrayAdapter.Item;
import com.example.LRA.R;

public class GridMenuFragment extends Fragment {

	private ArrayList<Item> menuItems;
	
	public static GridMenuFragment newInstance(){
		return new GridMenuFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		menuItems = new ArrayList<Item>();
		populateMenuItems();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		LinearLayout v = (LinearLayout)inflater.inflate(R.layout.activity_grid_menu, parent, false); 
		
		//Find grid view
		GridView gridView = (GridView) getActivity().findViewById(R.id.gridview);
		gridView.setAdapter(new GridArrayAdapter(getActivity(),menuItems));
		
		return v;
		
	}
	
	private void populateMenuItems(){
		Item social = new Item("Social Feeds",R.drawable.facebook_twitter_logo_combo1,new View.OnTouchListener() {	
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(getActivity(), "TEST", Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		
		Item account = new Item("My Account",R.drawable.account,new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(getActivity(), "TEST", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		Item samplePdfView = new Item("Sample PDF View",android.R.drawable.ic_menu_gallery,new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(getActivity(), "TEST", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		Item sampleWebView = new Item("Sample Web View",android.R.drawable.ic_menu_gallery,new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(getActivity(), "TEST", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		Item virtualTour = new Item("Virtual Tour",R.drawable.visualtour,new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(getActivity(), "TEST", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		//add items to menuItems
		menuItems.add(social); menuItems.add(account); menuItems.add(virtualTour); menuItems.add(samplePdfView);
		menuItems.add(sampleWebView);menuItems.add(sampleWebView);menuItems.add(sampleWebView);menuItems.add(sampleWebView);
		menuItems.add(sampleWebView);menuItems.add(sampleWebView);menuItems.add(sampleWebView);menuItems.add(sampleWebView);
		menuItems.add(sampleWebView);menuItems.add(sampleWebView);
	}

}
