package com.chaumoha.a04;

import java.util.UUID;

import com.chaumoha.a04.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
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
        UUID contactId = (UUID)getArguments().getSerializable(EXTRA_CONTACT_ID);
        mContact = ContactList.get(getActivity()).getContact(contactId);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, parent, false);       
        
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
}
