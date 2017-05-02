package com.example.anubharora.contactlist;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.anubharora.contactlist.adapter.ContactAdapter;
import com.example.anubharora.contactlist.model.Contact;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts;
    private RecyclerView contactList;
    private ContactAdapter contactAdapter;
    private LoadContacts loadContacts;
    private String name = null, phoneNumber = null;
    private Cursor phones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactList = (RecyclerView) findViewById(R.id.list);
        contacts = new ArrayList<>();
        contactList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        contactList.setItemAnimator(new DefaultItemAnimator());

        phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");

        loadContacts = new LoadContacts();
        loadContacts.execute();


        //readContacts();
    }

    class LoadContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if (phones != null) {
                if (phones.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Contacts in your phone !!", Toast.LENGTH_LONG).show();
                }

                while (phones.moveToNext()) {
                    name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contacts.add(new Contact(name, phoneNumber));
                }
                phones.close();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            contactAdapter = new ContactAdapter(contacts);
            contactList.setAdapter(contactAdapter);
        }
    }

}


//    private void readContacts() {
//        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
//        while (phones.moveToNext()) {
//            name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//            phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//
//        }
//        phones.close();
//    }

