package com.LRA;

import com.LRA.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

public class LoginActivity extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		FragmentManager manager = this.getSupportFragmentManager();
		Fragment fragment = manager.findFragmentById(R.id.login_container);
		
		if (fragment == null){
			fragment = new LoginFragment();
			
			manager.beginTransaction()
			.add(R.id.login_container, fragment)
            .commit();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
}
