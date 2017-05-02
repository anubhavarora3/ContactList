package com.example.anubharora.contactlist.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anubharora.contactlist.R;
import com.example.anubharora.contactlist.model.Contact;

import java.util.ArrayList;

/**
 * Created by anubharora on 5/2/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private ArrayList<Contact> contacts;

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        public TextView name, contact_id;

        public ContactViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.contact_name);
            contact_id = (TextView) itemView.findViewById(R.id.contact_id);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            return true;
        }
    }

    public ContactAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row,
                parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.name.setText(contact.getName());
        String contact_name = contact.getName().charAt(0) + "";
        holder.contact_id.setText(contact_name);

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

}
