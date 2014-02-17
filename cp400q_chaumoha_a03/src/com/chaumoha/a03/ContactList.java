package com.chaumoha.a03;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class ContactList {
    private ArrayList<Contact> mContacts;

    private static ContactList sContactList;
    private Context mAppContext;

    private ContactList(Context appContext) {
        mAppContext = appContext;
        mContacts = new ArrayList<Contact>();
        mContacts.add(new Contact("Bryan", "Chau", "(416) 993-4832", "contactbryan"));
        mContacts.add(new Contact("Mily", "Chen", "(647) 818-9833", "contactmily"));
        mContacts.add(new Contact("Mohamed", "Mohamedtaki", "(647) 895-8796", "contactmo"));
    }

    public static ContactList get(Context c) {
        if (sContactList == null) {
            sContactList = new ContactList(c.getApplicationContext());
        }
        return sContactList;
    }

    public Contact getContact(UUID id) {
        for (Contact c : mContacts) {
            if (c.getID().equals(id))
                return c;
        }
        return null;
    }
    
    public ArrayList<Contact> getContacts() {
        return mContacts;
    }
}

