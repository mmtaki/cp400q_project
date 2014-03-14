package com.chaumoha.a06;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
    	getActivity().getMenuInflater().inflate(R.menu.contact_list_item_context,menu);
    }
    
    @Override        
    public void onResume(){       
    	super.onResume();  
    	((ContactAdapter)getListAdapter()).notifyDataSetChanged();    
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
    	View v = super.onCreateView(inflater, parent,  savedInstanceState);
    	
    	ListView listView = (ListView)v.findViewById(android.R.id.list);
    	
    	if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
    		registerForContextMenu(listView);
    	else{
    		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    		listView.setMultiChoiceModeListener(new MultiChoiceModeListener(){

				@Override
				public boolean onActionItemClicked(ActionMode mode,
						MenuItem item) {
					switch(item.getItemId()){
						case R.id.menu_item_delete_contact:
							ContactAdapter adapter = (ContactAdapter)getListAdapter();
							ContactList contactList = ContactList.get(getActivity());
							for (int i = adapter.getCount() - 1; i >= 0; i--){
								if (getListView().isItemChecked(i)){
									contactList.removeContact(adapter.getItem(i));
								}
							}
							mode.finish();
							adapter.notifyDataSetChanged();
							return true;
						default:
							return false;
					}
				}

				@Override
				public boolean onCreateActionMode(ActionMode mode, Menu menu) {
					MenuInflater inflater = mode.getMenuInflater();
					inflater.inflate(R.menu.contact_list_item_context, menu);
					return true;
				}

				@Override
				public void onDestroyActionMode(ActionMode mode) {
				}

				@Override
				public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
					return false;
				}

				@Override
				public void onItemCheckedStateChanged(ActionMode mode,
						int position, long id, boolean checked) {
				}
    			
    		});
    	}
    	return v;
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item){
    	AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
    	int pos = info.position;
    	
    	ContactAdapter adapter = (ContactAdapter)getListAdapter();
    	Contact contact = adapter.getItem(pos);
    	
    	switch (item.getItemId()){
    		case R.id.menu_item_delete_contact:
    			ContactList.get(getActivity()).removeContact(contact);
    			adapter.notifyDataSetChanged();
    			return true;
    	}
    	return super.onContextItemSelected(item);
    }
}

