package com.example.malai_pt1882.contentprovideremployeeadmin;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UpdateEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        final TextInputEditText employeeIdEditText = findViewById(R.id.update_employee_id_edittext);
        final Button checkEmployeeButton = findViewById(R.id.check_button);
        final TextInputEditText employeeNameEditText = findViewById(R.id.update_employee_name_edittext);
        final TextInputEditText employeeSalaryEditText = findViewById(R.id.update_employee_salary_edittext);
        final Button updateButton = findViewById(R.id.update_button);


        final UpdateButtonListener updateButtonOnClickListener = new UpdateButtonListener() {

            private int employeeId;

            @Override
            public void onClick(View v) {
                String employeeName = employeeNameEditText.getText().toString();
                String employeeStringSalary = employeeSalaryEditText.getText().toString();


                if(employeeName.length()>0 && employeeStringSalary.length()>0){

                    int employeeSalary = Integer.parseInt(employeeStringSalary);

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(EmployeeDatabaseContract.EMPLOYEE_NAME_COLUMN,employeeName);
                    contentValues.put(EmployeeDatabaseContract.EMPLOYEE_SALARY_COLUMN,employeeSalary);

                    int rowsUpdated = getContentResolver().update(MainActivity.uri,contentValues,EmployeeDatabaseContract._ID + " = " + employeeId,null);

                    if(rowsUpdated > 0){
                        Toast.makeText(UpdateEmployeeActivity.this,"Update successful",Toast.LENGTH_SHORT).show();

                        employeeNameEditText.setFocusable(false);
                        employeeSalaryEditText.setFocusable(false);
                        updateButton.setFocusable(false);
                    }

                }
            }

            public void setId(int employeeId){
                this.employeeId = employeeId;
            }

        };

        checkEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(employeeIdEditText.getText().toString().length()>0) {

                    int employeeId = Integer.parseInt(employeeIdEditText.getText().toString());

                    Uri employeeUri = ContentUris.withAppendedId(MainActivity.uri,employeeId);

                    Cursor cursor = getContentResolver().query(employeeUri,null,null,null,null);

                    if(cursor!=null && cursor.getCount()>0){

                        employeeNameEditText.setFocusable(true);
                        employeeNameEditText.setFocusableInTouchMode(true);

                        employeeSalaryEditText.setFocusable(true);
                        employeeSalaryEditText.setFocusableInTouchMode(true);

                        updateButton.setClickable(true);

                        updateButtonOnClickListener.setId(employeeId);
                        updateButton.setOnClickListener(updateButtonOnClickListener);
                        cursor.close();

                    } else{
                        Toast.makeText(UpdateEmployeeActivity.this,"Employee id not exists",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    private interface UpdateButtonListener extends View.OnClickListener{

        void setId(int employeeId);
    }
}
