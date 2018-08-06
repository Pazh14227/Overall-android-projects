package com.example.malai_pt1882.recyclerviewdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private ArrayList<String> text;

    MyRecyclerAdapter(ArrayList<String> lText){
         text = lText;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_holder,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextView.setText(String.format("%s %d",text.get(position),position));
        holder.mImageView.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public int getItemCount() {
        return text.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;
        ImageView mImageView;

        ViewHolder(View itemView){
            super(itemView);

            mTextView = itemView.findViewById(R.id.holder_text_view_id);
            mImageView = itemView.findViewById(R.id.holder_image_view_id);
        }
    }
}
