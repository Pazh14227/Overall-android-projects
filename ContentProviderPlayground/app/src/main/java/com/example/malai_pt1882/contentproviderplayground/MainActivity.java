package com.example.malai_pt1882.contentproviderplayground;

import android.app.LoaderManager;
import android.content.ContentProvider;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter simpleCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list_view);

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.list_layout, null, new String[]{"Title"}, new int[]{R.id.text_view_1}, 0);

        listView.setAdapter(simpleCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = view.findViewById(R.id.text_view_1);

                Toast.makeText(MainActivity.this,textView.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = view.findViewById(R.id.text_view_2);

                Toast.makeText(MainActivity.this,textView.getText(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        getLoaderManager().initLoader(0, null, this).forceLoad();
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        Log.d("MainActivity","onCreateLoader");

        Uri uri = Uri.parse("content://com.example.malai_pt1882.economictimesdemo.newsprovider.NewsProvider/NewsTable");

        return new CursorLoader(this, uri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        Log.d("MainActivity","onLoadFinished");
        if (data!=null) {
            Log.d("onLoadFinished", data.getCount() + " ");
            simpleCursorAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        simpleCursorAdapter.swapCursor(null);
    }
}
