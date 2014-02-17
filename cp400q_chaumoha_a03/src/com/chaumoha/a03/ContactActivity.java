package com.chaumoha.a03;

import java.util.UUID;

import android.support.v4.app.Fragment;
import android.util.Log;

public class ContactActivity extends SingleFragmentActivity {
	private final String TAG = this.getClass().getName();
	
	@Override
	protected Fragment createFragment() {
		Log.i(TAG, "Created Fragment");
		UUID contactId = (UUID) getIntent().getSerializableExtra(
				ContactFragment.EXTRA_CONTACT_ID);
		return ContactFragment.newInstance(contactId);
	}

}
