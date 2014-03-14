package com.chaumoha.a06;

import com.chaumoha.a06.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.EditText;

public class NewContactActivity extends FragmentActivity{
	private final String TAG = this.getClass().getName();

	@Override
	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.activity_fragment);
		FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new NewContactFragment();
            manager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
        }
	}
	
	public void addContact(View v){
		EditText firstname = (EditText)findViewById(R.id.first_name);
		EditText lastname = (EditText)findViewById(R.id.last_name);
		EditText phone = (EditText)findViewById(R.id.phone);
		
		Contact c = new Contact(firstname.getText().toString(),
				lastname.getText().toString(),
				phone.getText().toString(),
				"ic_launcher");
		ContactList.get(this).addContact(c);
		this.finish();
	}
	
	

}
