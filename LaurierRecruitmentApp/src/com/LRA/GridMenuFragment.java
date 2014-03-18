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
		Item social = new Item();
		social.text = "Social Feeds";
		social.imageResourceId = R.drawable.ic_launcher;
		social.touch = new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(getActivity(), "TEST", Toast.LENGTH_SHORT).show();
				return false;
			}
		};
		
		Item test = new Item();
		test.text = "Test";
		test.imageResourceId = android.R.drawable.ic_delete;
		test.touch = new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Toast.makeText(getActivity(), "TEST", Toast.LENGTH_SHORT).show();
				return false;
			}
		};
		
		//add items to menuItems
		menuItems.add(social); menuItems.add(social); menuItems.add(social);
		menuItems.add(social); menuItems.add(social); menuItems.add(social);
		menuItems.add(social); menuItems.add(social); menuItems.add(test);
	}

}
