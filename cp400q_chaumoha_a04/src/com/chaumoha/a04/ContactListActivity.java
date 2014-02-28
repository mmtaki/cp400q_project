package com.chaumoha.a04;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

public class ContactListActivity extends SingleFragmentActivity {
	private final String TAG = this.getClass().getName();
	
	
	@Override
	protected Fragment createFragment() {
		Log.i(TAG, "Created Fragment");
        return new ContactListFragment();
    }

}
