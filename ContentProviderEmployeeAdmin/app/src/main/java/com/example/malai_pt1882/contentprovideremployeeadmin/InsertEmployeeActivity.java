package com.example.malai_pt1882.contentprovideremployeeadmin;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertEmployeeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        Button insertEmployeeButton = findViewById(R.id.insert_employee_button);
        final EditText employeeNameEditText = findViewById(R.id.employee_name_edittext);
        final EditText employeeSalaryEditText = findViewById(R.id.employee_salary_edittext);


        insertEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String employeeName = employeeNameEditText.getText().toString();
                String employeeSalaryString = employeeSalaryEditText.getText().toString();

                if(employeeName.length() == 0){
                   employeeNameEditText.setError("Enter employee name");
                   return;
                }

                if(employeeSalaryString.length() == 0){
                    employeeSalaryEditText.setError("Enter salary");
                    return;
                }

                int employeeSalary = Integer.parseInt(employeeSalaryEditText.getText().toString());

                ContentValues contentValues = new ContentValues();
                contentValues.put(EmployeeDatabaseContract.EMPLOYEE_NAME_COLUMN,employeeName);
                contentValues.put(EmployeeDatabaseContract.EMPLOYEE_SALARY_COLUMN,employeeSalary);
                Uri newUri = getContentResolver().insert(MainActivity.uri,contentValues);
                displayToast(newUri);
            }

            private void displayToast(Uri newUri){
                if(newUri!=null){

                    Log.d("displayToast",newUri.toString());
                    Cursor cursor = getContentResolver().query(newUri,null,null,null,null);

                    if(cursor!=null) {
                        cursor.moveToFirst();
                        int employeeId = cursor.getInt(cursor.getColumnIndex(EmployeeDatabaseContract._ID));
                        Toast.makeText(InsertEmployeeActivity.this, "Employee added with employee id " + employeeId, Toast.LENGTH_SHORT).show();
                        cursor.close();
                    }
                }
            }
        });
    }
}
