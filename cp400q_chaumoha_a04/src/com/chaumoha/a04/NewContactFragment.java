package com.chaumoha.a04;

import java.util.UUID;

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
import android.widget.TextView;

public class NewContactFragment extends Fragment{
	private final String TAG = this.getClass().getName();

	public static NewContactFragment newInstance(){
		return new NewContactFragment();
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Log.i(TAG, "onCreate");
    }

    @TargetApi(11)
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.new_contact, parent, false);       

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
        	getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
