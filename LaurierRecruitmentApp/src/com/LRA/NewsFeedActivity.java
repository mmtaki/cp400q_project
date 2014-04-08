package com.LRA;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.google.analytics.tracking.android.EasyTracker;

public class NewsFeedActivity extends FragmentActivity {
	private final String TAG = this.getClass().getName();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "OnCreate");
		setContentView(R.layout.activity_fragment);

		FragmentManager manager = this.getSupportFragmentManager();
		Fragment fragment = manager.findFragmentById(R.id.fragmentcontainer);

		if (fragment == null) {
			fragment = new NewsFeedFragment();

			manager.beginTransaction().add(R.id.fragmentcontainer, fragment)
					.commit();
		}
	}


	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this); // Add this method.
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this); // Add this method.
	}
}
