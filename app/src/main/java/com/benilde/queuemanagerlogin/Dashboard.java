package com.benilde.queuemanagerlogin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dashboard extends AppCompatActivity implements DBUtility {

    private ListView patientList;
    private ListAdapter listAdapter;

    ConnectionClass connectionClass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        patientList = (ListView)findViewById(R.id.listViewPatients);

        connectionClass = new ConnectionClass();

        ListDept listdept=new Dashboard.ListDept();
        listdept.execute();
//        patientList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                final String selectedFromList = String.valueOf((patientList.getItemAtPosition(position)));
//                Toast.makeText(getApplicationContext(),"You selected: "+selectedFromList,Toast.LENGTH_LONG).show();
//                //Dialog box, for unenrolling
//                AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
//                builder.setMessage("Unenroll Department?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                String name = selectedFromList.substring(3, selectedFromList.length()-1);
//
//                                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_LONG).show();
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
//
//        });


    }

    //function for displaying the enrolled department
    private class ListDept extends AsyncTask<String, String, String> {


        boolean isSuccess = false;
        String message = "";

        @Override
        protected void onPreExecute() {
            Toast.makeText(getBaseContext(),"Please wait..",Toast.LENGTH_LONG).show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                Connection con = connectionClass.CONN();
                if (con == null) {
                    message = "Please check your internet connection";
                } else {
                    try {
                        //listview, list the names of all enrolled department
                        String result = "Database Connection Successful\n";
                        Statement st = con.createStatement();
                        ResultSet rset = st.executeQuery(SELECT_LIST_PATIENT);
                        ResultSetMetaData rsmd = rset.getMetaData();

                        List<Map<String, String>> data = null;
                        data = new ArrayList<Map<String, String>>();

                        while (rset.next()) {
                            Map<String, String> datanum = new HashMap<String, String>();
                            datanum.put("A", rset.getString(3).toString());
                            data.add(datanum);
                        }

                        String[] fromwhere = {"A"};
                        int[] viewswhere = {R.id.lblPatientList};
                        listAdapter = new SimpleAdapter(Dashboard.this, data,
                                R.layout.list_patient_dashboard_template, fromwhere, viewswhere);

                        while (rset.next()) {
                            result += rset.getString(1).toString() + "\n";
                        }
                        message = "ADDED SUCCESSFULLY!";
                    } catch (Exception ex) {
                        isSuccess = false;
                        message = "Exceptions" + ex;
                    }
                    return message;
                }
            } catch (Exception e) {
                message = "Exceptions" + e;

            }
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(),""+message,Toast.LENGTH_LONG).show();
            patientList.setAdapter(listAdapter);
        }
    }
}
