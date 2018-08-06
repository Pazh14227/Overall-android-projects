package com.example.malai_pt1882.contentprovideremployeeadmin;

import android.provider.BaseColumns;

public class EmployeeDatabaseContract implements BaseColumns{

    public static final String EMPLOYEE_TABLE_NAME = "EmployeeTable";
    public static final String EMPLOYEE_NAME_COLUMN = "EmployeeName";
    public static final String EMPLOYEE_SALARY_COLUMN = "EmployeeSalary";

    private EmployeeDatabaseContract(){

    }
}
