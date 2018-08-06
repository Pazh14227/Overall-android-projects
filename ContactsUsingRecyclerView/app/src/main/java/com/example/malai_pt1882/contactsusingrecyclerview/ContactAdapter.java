package com.example.malai_pt1882.contactsusingrecyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {

    private ArrayList<HashMap<String,String>> myContacts;

    ContactAdapter(ArrayList<HashMap<String,String>> contacts){
        myContacts = contacts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout,parent,false);
        return new MyViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nameTextView.setText(myContacts.get(position).get("name"));
        holder.emailTextView.setText(myContacts.get(position).get("email"));
    }

    @Override
    public int getItemCount() {
        return myContacts.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nameTextView;
        private TextView emailTextView;

         MyViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name_text_id);
            emailTextView = itemView.findViewById(R.id.email_text_id);
        }
    }
}
