package com.example.malai_pt1882.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.main_recycler_view_id);
        ArrayList<String> list = new ArrayList<>();
        list.add("This is a model recycler view app");
        list.add("This is a model recycler view app");
        list.add("This is a model recycler view app");
        list.add("This is a model recycler view app");
        list.add("This is a model recycler view app");
        list.add("This is a model recycler view app");
        list.add("This is a model recycler view app");
        list.add("This is a model recycler view app");
        list.add("This is a model recycler view app");
        list.add("This is a model recycler view app");
        list.add("This is a model recycler view app");

        recyclerView.setAdapter(new MyRecyclerAdapter(list));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));


    }
}
