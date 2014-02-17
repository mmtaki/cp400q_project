package com.chaumoha.a03;

import android.support.v4.app.Fragment;
import android.util.Log;

public class ContactListActivity extends SingleFragmentActivity {
	private final String TAG = this.getClass().getName();
	@Override
	protected Fragment createFragment() {
		Log.i(TAG, "Created Fragment");
        return new ContactListFragment();
    }

}
