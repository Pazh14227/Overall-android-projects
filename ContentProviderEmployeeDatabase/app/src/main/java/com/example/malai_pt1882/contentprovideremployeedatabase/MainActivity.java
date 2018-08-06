package com.example.malai_pt1882.contentprovideremployeedatabase;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.malai_pt1882.contentprovideremployeedatabase.employee_database.EmployeeDatabaseContract;

import java.lang.ref.WeakReference;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    private static final String STRING_URI = "content://" + "com.example.malai_pt1882.contentprovideremployeedatabase.employeeprovider/" + EmployeeDatabaseContract.EMPLOYEE_TABLE_NAME;

    private static final Uri uri = Uri.parse(STRING_URI);

    private static final int EMPLOYEE_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        EmployeeAdapter employeeAdapter = new EmployeeAdapter(null);
        recyclerView.setAdapter(employeeAdapter);

        EmployeeLoader employeeLoader = new EmployeeLoader(uri,this,employeeAdapter);
        getSupportLoaderManager().initLoader(EMPLOYEE_LOADER_ID,null,employeeLoader).forceLoad();
    }


    private static class EmployeeLoader implements LoaderManager.LoaderCallbacks<Cursor>{

        private final Uri uri;
        private final WeakReference<Context> contextWeakReference;

        private final EmployeeAdapter employeeAdapter;

        private EmployeeLoader(Uri uri,Context context,EmployeeAdapter employeeAdapter){
            this.uri = uri;
            contextWeakReference = new WeakReference<>(context);
            this.employeeAdapter = employeeAdapter;
        }

        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
            return new CursorLoader(contextWeakReference.get(),uri,null,null,null,EmployeeDatabaseContract._ID);
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

             if(data!=null){
                 Log.d("onLoadFinished","data is not null");
                 employeeAdapter.swapCursor(data);
             }

        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
             employeeAdapter.swapCursor(null);
        }
    }


    private static class EmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Cursor employeeCursor;

        private EmployeeAdapter(Cursor employeeCursor){
            this.employeeCursor = employeeCursor;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View layout = layoutInflater.inflate(R.layout.employee_details, parent, false);
            return new EmployeeViewHolder(layout);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            EmployeeViewHolder employeeViewHolder = (EmployeeViewHolder) holder;

            if (position == 0) {
                employeeViewHolder.employeeIdTextView.setText("Employee id");
                employeeViewHolder.employeeNameTextView.setText("Employee Name");
                employeeViewHolder.employeeSalaryTextView.setText("Employee Salary");
            } else if(employeeCursor!=null){
                Log.d("onBindViewHolder","position is " + position);
                employeeCursor.moveToPosition(position-1);
                employeeViewHolder.employeeIdTextView.setText(employeeCursor.getString(employeeCursor.getColumnIndex(EmployeeDatabaseContract._ID)));
                employeeViewHolder.employeeNameTextView.setText(employeeCursor.getString(employeeCursor.getColumnIndex(EmployeeDatabaseContract.EMPLOYEE_NAME_COLUMN)));

                String employeeSalary = String.format(Locale.ENGLISH,"%d",employeeCursor.getInt(employeeCursor.getColumnIndex(EmployeeDatabaseContract.EMPLOYEE_SALARY_COLUMN)));

                employeeViewHolder.employeeSalaryTextView.setText(employeeSalary);
            }

        }

        @Override
        public int getItemCount() {

            if(employeeCursor!=null) {
                Log.d("Number of entries is",employeeCursor.getCount()+"");
                return employeeCursor.getCount() + 1;
            }

            return 0;
        }

        private void swapCursor(Cursor newData){
            employeeCursor = newData;
            notifyDataSetChanged();
        }


        static class EmployeeViewHolder extends RecyclerView.ViewHolder {

            private TextView employeeIdTextView;
            private TextView employeeNameTextView;
            private TextView employeeSalaryTextView;

            private EmployeeViewHolder(View itemView) {
                super(itemView);

                employeeIdTextView = itemView.findViewById(R.id.employee_id_textview);
                employeeNameTextView = itemView.findViewById(R.id.employee_name_textview);
                employeeSalaryTextView = itemView.findViewById(R.id.employee_salary_textview);

            }
        }
    }
}
