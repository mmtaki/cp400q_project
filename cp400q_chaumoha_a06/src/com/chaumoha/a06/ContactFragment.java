package com.chaumoha.a06;

import java.util.UUID;

import com.chaumoha.a06.R;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactFragment extends Fragment {
    public static final String EXTRA_CONTACT_ID = "Contacts.CONTACT_ID";
    private final String TAG = this.getClass().getName();

    Contact mContact;
    TextView mName, mPhone;
    ImageView mPhoto;

    public static ContactFragment newInstance(UUID contactId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CONTACT_ID, contactId);
        
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setHasOptionsMenu(true);
        UUID contactId = (UUID)getArguments().getSerializable(EXTRA_CONTACT_ID);
        mContact = ContactList.get(getActivity()).getContact(contactId);
        
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, parent, false);       
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
        		getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        mName = (TextView)v.findViewById(R.id.contact_name);
        mName.setText(mContact.getFullName());
        
        mPhone = (TextView)v.findViewById(R.id.contact_number);
        mPhone.setText(mContact.getPhoneNumber());
        
        mPhoto = (ImageView)v.findViewById(R.id.contact_photo);
        String packageName = this.getClass().getPackage().getName();
        int resID = getResources().getIdentifier(mContact.getPhoto(), "drawable", packageName);
        mPhoto.setImageResource(resID);
        
        return v; 
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    		case android.R.id.home:
    			getActivity().finish();
    			return true;
    		default:
    			return super.onOptionsItemSelected(item);
    	}
    }
}
