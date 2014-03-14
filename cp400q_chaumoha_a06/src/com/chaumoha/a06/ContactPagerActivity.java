package com.chaumoha.a06;

import java.util.ArrayList;
import java.util.UUID;

import com.chaumoha.a06.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class ContactPagerActivity extends FragmentActivity {
	private final String TAG = this.getClass().getName();
	protected UUID contactId;
	ViewPager mViewPager;
	private ArrayList<Contact> mContacts;
	
	@Override
	public void onCreate(Bundle b){
		super.onCreate(b);
		mContacts = ContactList.get(this).getContacts();
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewpager);
        setContentView(mViewPager);
        
        
        FragmentManager manager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager){
        	@Override
        	public Fragment getItem(int pos) {
        		Contact contact = mContacts.get(pos);
        		return ContactFragment.newInstance(contact.getID());
        	}

        	@Override
        	public int getCount() {
        		return mContacts.size();
        	}
        } );
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int pos) {
				Contact contact = mContacts.get(pos);
				if(contact.getFullName() != null){
					setTitle(contact.getFullName());
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {}
		});

        UUID contactId = (UUID)getIntent().getSerializableExtra(ContactFragment.EXTRA_CONTACT_ID);
        for (int i = 0; i < mContacts.size(); i++){
        	if (mContacts.get(i).getID().equals(contactId)){
        		mViewPager.setCurrentItem(i);
        		break;
        	}
        }
        
        //Set first name
        Contact contact = mContacts.get(mViewPager.getCurrentItem());
		if(contact.getFullName() != null){
			setTitle(contact.getFullName());
		}
        
	}

	
}
