package com.LRA;

import java.util.ArrayList;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.LRA.GridArrayAdapter.Item;
import com.LRA.R;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.analytics.tracking.android.Tracker;

public class GridMenuFragment extends Fragment {
	private final String TAG = this.getClass().getName();
	private ArrayList<Item> menuItems;
	private Tracker tracker;
	private Uri filename;
	
	public static GridMenuFragment newInstance() {
		return new GridMenuFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		menuItems = new ArrayList<Item>();
		populateMenuItems();
		this.tracker = EasyTracker.getInstance(this.getActivity());
		this.filename = Uri.parse("content://"+this.getActivity().getPackageName()+"/sample.pdf");
		Log.d(TAG, filename.toString());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent,
			Bundle savedInstanceState) {
		LinearLayout v = (LinearLayout) inflater.inflate(
				R.layout.activity_grid_menu, parent, false);

		// Find grid view
		GridView gridView = (GridView) getActivity()
				.findViewById(R.id.gridview);
		gridView.setAdapter(new GridArrayAdapter(getActivity(), menuItems));

		return v;

	}

	@Override
	public void onResume() {

		super.onResume();

		this.tracker.set(Fields.SCREEN_NAME, getClass().getSimpleName());
		this.tracker.send(MapBuilder.createAppView().build());
	}

	private void populateMenuItems() {

		// MapBuilder.createEvent().build() returns a Map of event fields and
		// values
		// that are set and sent with the hit.
		Item social = new Item("Social Feeds",
				R.drawable.facebook_twitter_logo_combo1,
				new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						Toast.makeText(getActivity(), "Social Media",
								Toast.LENGTH_SHORT).show();
						tracker.send(MapBuilder.createEvent("ui_action", // Event
																			// category
																			// (required)
								"button_press", // Event action (required)
								"social_feed_button", // Event label
								null) // Event value
								.build());
						//mSearchText.setText("");

						ConnectivityManager connMgr = (ConnectivityManager) getActivity()
								.getSystemService(Context.CONNECTIVITY_SERVICE);
						NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
						if (networkInfo != null && networkInfo.isConnected()) {
							Intent i = new Intent(getActivity(), NewsFeedActivity.class);
				    		startActivity(i);
						} else {
							Toast.makeText(getActivity(), R.string.no_network,
									Toast.LENGTH_LONG).show();
						}
						return false;
					}
				});

		Item account = new Item("My Account", R.drawable.account,
				new View.OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						Toast.makeText(getActivity(), "My Account",
								Toast.LENGTH_SHORT).show();
						tracker.send(MapBuilder.createEvent("ui_action", // Event
								// category
								// (required)
								"button_press", // Event action (required)
								"my_account_button", // Event label
								null) // Event value
								.build());
						return false;
					}
				});

		Item samplePdfView = new Item("Sample PDF View",
				android.R.drawable.ic_menu_gallery, new View.OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						
						tracker.send(MapBuilder.createEvent("ui_action", // Event
								// category
								// (required)
								"button_press", // Event action (required)
								"pdf_button", // Event label
								null) // Event value
								.build());
						Intent target = new Intent(Intent.ACTION_VIEW);
						target.setDataAndType(filename,"application/pdf");
						target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

						Intent intent = Intent.createChooser(target, "Open File");
						try {
						    startActivity(intent);
						} catch (ActivityNotFoundException e) {
							Toast.makeText(getActivity(), "Please install a PDF viewer.",
								Toast.LENGTH_SHORT).show();
						    // Instruct the user to install a PDF reader here, or something
						}   
						return false;
					}
				});

		Item sampleWebView = new Item("Sample Web View",
				android.R.drawable.ic_menu_gallery, new View.OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						tracker.send(MapBuilder.createEvent("ui_action", // Event
								// category
								// (required)
								"button_press", // Event action (required)
								"web_view_button", // Event label
								null) // Event value
								.build());
						Intent i = new Intent(getActivity(), WebpageActivity.class);
			    		startActivity(i);
						return false;
					}
				});

		Item virtualTour = new Item("Virtual Tour", R.drawable.visualtour,
				new View.OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						Toast.makeText(getActivity(), "Virtual Tour",
								Toast.LENGTH_SHORT).show();
						tracker.send(MapBuilder.createEvent("ui_action", // Event
								// category
								// (required)
								"button_press", // Event action (required)
								"virtual_tour_button", // Event label
								null) // Event value
								.build());
						return false;
					}
				});

		// add items to menuItems
		menuItems.add(social);
		menuItems.add(account);
		menuItems.add(virtualTour);
		menuItems.add(samplePdfView);
		menuItems.add(sampleWebView);
		menuItems.add(sampleWebView);
		menuItems.add(sampleWebView);
		menuItems.add(sampleWebView);
		menuItems.add(sampleWebView);
		menuItems.add(sampleWebView);
		menuItems.add(sampleWebView);
		menuItems.add(sampleWebView);
		menuItems.add(sampleWebView);
		menuItems.add(sampleWebView);
	}

}