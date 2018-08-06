package com.example.malai_pt1882.contactsappusinglistview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

class ContactAdapter extends BaseAdapter {

    private final ArrayList<HashMap<String, String>> myContacts;

    ContactAdapter(ArrayList<HashMap<String, String>> contacts) {
        Log.d("tag", "Inside constructor");
        myContacts = contacts;
    }

    @Override
    public int getCount() {
        Log.d("tag", "In get coutnt" + myContacts.size());
        return myContacts.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d("tag", "get Item");
        return null;
    }

    @Override
    public long getItemId(int position) {
        Log.d("tag", "In get Item ID");
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("tag", "In get view method at position " + position);

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        }

        getImageView(convertView).setImageResource(R.drawable.ic_contact_icon);

        getNameTextView(convertView).setText(myContacts.get(position).get("name"));

        getEmailTextView(convertView).setText(myContacts.get(position).get("email"));

        return convertView;
    }

    private ImageView getImageView(View convertView){
        return (ImageView)convertView.findViewById(R.id.relative_image_id);
    }

    private TextView getNameTextView(View convertView){
        return (TextView)convertView.findViewById(R.id.name_text_id);
    }

    private TextView getEmailTextView(View convertView){
        return (TextView)convertView.findViewById(R.id.email_text_id);
    }

}