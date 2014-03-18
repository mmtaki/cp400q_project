package com.LRA;


import com.example.LRA.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class GridMenuActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid_menu);
		
		FragmentManager manager = this.getSupportFragmentManager();
		Fragment fragment = manager.findFragmentById(R.id.fragment_container);
		
		if (fragment == null){
			fragment = new GridMenuFragment();
			
			manager.beginTransaction()
			.add(R.id.fragment_container, fragment)
            .commit();
		}
	}
}
