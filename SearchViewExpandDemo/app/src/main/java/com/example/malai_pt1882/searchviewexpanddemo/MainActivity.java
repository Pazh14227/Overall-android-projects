package com.example.malai_pt1882.searchviewexpanddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            Log.d("In onCreate","getSupportActionBar is not null");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.search_item);
        MenuItem menuItem1 = menu.findItem(R.id.my_search_item);

        SearchView searchView = (SearchView) menuItem.getActionView();
        LinearLayout linearLayout = (LinearLayout)menuItem1.getActionView();

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Toast.makeText(MainActivity.this,"Focus changed",Toast.LENGTH_SHORT).show();
            }
        });

        EditText editText = linearLayout.findViewById(R.id.search_edit_text);
        editText.requestFocus();


        return super.onCreateOptionsMenu(menu);
    }
}