package com.example.malai_pt1882.contentprovideremployeeadmin;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String STRING_URI = "content://" + "com.example.malai_pt1882.contentprovideremployeedatabase.employeeprovider/" + EmployeeDatabaseContract.EMPLOYEE_TABLE_NAME;

    static final Uri uri = Uri.parse(STRING_URI);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button insertEmployeeButton = findViewById(R.id.main_insert_employee_button);
        final Button updateEmployeeButton = findViewById(R.id.main_update_employee_button);
        final Button deleteEmployeeButton = findViewById(R.id.main_delete_employee_button);
        final Button showAllEmployeeButton = findViewById(R.id.main_employee_show_button);

        final Intent intent = new Intent();

        final View.OnClickListener buttonClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.main_insert_employee_button:
                        intent.setClass(MainActivity.this,InsertEmployeeActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.main_delete_employee_button:
                        intent.setClass(MainActivity.this,DeleteEmployeeActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.main_update_employee_button:
                        intent.setClass(MainActivity.this,UpdateEmployeeActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.main_employee_show_button:
                        intent.setClass(MainActivity.this,ShowAllEmployeeActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };

        insertEmployeeButton.setOnClickListener(buttonClickListener);

        updateEmployeeButton.setOnClickListener(buttonClickListener);

        deleteEmployeeButton.setOnClickListener(buttonClickListener);

        showAllEmployeeButton.setOnClickListener(buttonClickListener);
    }

}
