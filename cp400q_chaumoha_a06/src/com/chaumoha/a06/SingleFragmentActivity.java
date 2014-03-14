package com.chaumoha.a06;

import com.chaumoha.a06.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;

public abstract class SingleFragmentActivity extends FragmentActivity {
    protected abstract Fragment createFragment();
    protected Fragment fragment;
    protected FragmentManager manager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_fragment);
        manager = getSupportFragmentManager();
        fragment = manager.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();
            manager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
        }
    }
    

}
