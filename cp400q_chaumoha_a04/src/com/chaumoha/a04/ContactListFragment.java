package com.chaumoha.a04;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ContactListFragment extends ListFragment {
    private ArrayList<Contact> mContacts;

	private final String TAG = this.getClass().getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mContacts = ContactList.get(getActivity()).getContacts();
        ContactAdapter adapter = new ContactAdapter(mContacts);
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
    	Log.i(TAG, "onListItemClick");
        // get the Contact from the adapter
        Contact c = ((ContactAdapter)getListAdapter()).getItem(position);
        // start an instance of ContactActivity
        Intent i = new Intent(getActivity(), ContactPagerActivity.class);
        i.putExtra(ContactFragment.EXTRA_CONTACT_ID, c.getID());
        startActivityForResult(i, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ((ContactAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class ContactAdapter extends ArrayAdapter<Contact> {
        public ContactAdapter(ArrayList<Contact> contacts) {
            super(getActivity(), android.R.layout.simple_list_item_1, contacts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // if we weren't given a view, inflate one
            if (null == convertView) {
                convertView = getActivity().getLayoutInflater()
                    .inflate(R.layout.list_item_contact, null);
            }

            // configure the view for this Contact
            Contact c = getItem(position);
            
            TextView name =
                (TextView)convertView.findViewById(R.id.contact_list_item_data);
            name.setText(c.getFullName() + "\n" + c.getPhoneNumber());
            
            String packageName = this.getClass().getPackage().getName();
            int resID = getResources().getIdentifier(c.getPhoto(), "drawable", packageName);
            ImageView icon =
            	(ImageView)convertView.findViewById(R.id.contact_list_item_icon);
            icon.setImageResource(resID);

            return convertView;
        }
    }
    
    @Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.fragment_contact_list, menu);
	}
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case R.id.menu_item_new_contact:
    		//Contact contact = new Contact(TAG, TAG, TAG, TAG);
    		//ContactList.get(getActivity()).addContact(contact);
    		Intent i = new Intent(getActivity(),NewContactActivity.class);
    		startActivity(i);
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
    
    @Override        
    public void onResume(){       
    	super.onResume();  
    	((ContactAdapter)getListAdapter()).notifyDataSetChanged();    
    }
}

