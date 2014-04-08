package com.LRA;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.LRA.R;
import com.google.analytics.tracking.android.EasyTracker;

public class GridMenuActivity extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_menu);

		FragmentManager manager = this.getSupportFragmentManager();
		Fragment fragment = manager.findFragmentById(R.id.fragment_container);

		if (fragment == null) {
			fragment = new GridMenuFragment();

			manager.beginTransaction().add(R.id.fragment_container, fragment)
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
