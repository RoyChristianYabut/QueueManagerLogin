package com.benilde.queuemanagerlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BeginQueue extends AppCompatActivity {


    private static String urlAddressDoctors="http://10.0.2.2:8080/kerux/doctorSpinner.php";
    private static String urlAddressDepartments="http://10.0.2.2:8080/kerux/departmentSpinner.php";
    private EditText name;
    private Spinner doctor;
    private Spinner department;

    private Button start;

    ConnectionClass connectionClass;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_queue);

        doctor = (Spinner)findViewById(R.id.spinnerDoctor);
        department = (Spinner)findViewById(R.id.spinnerDepartment);


        Downloader doc=new Downloader(BeginQueue.this,urlAddressDoctors,doctor, "Name");
        doc.execute();
        Downloader dep=new Downloader(BeginQueue.this,urlAddressDepartments,department, "Name");
        dep.execute();

        connectionClass = new ConnectionClass();
        progressDialog = new ProgressDialog(this);

        name = (EditText) findViewById(R.id.editName);
        start=(Button)findViewById(R.id.btnSubmit);

        start.setOnClickListener(new View.OnClickListener() {//
            @Override
            public void onClick(View v) {
                DoBeginQueue doBeginQueue = new DoBeginQueue();
                doBeginQueue.execute();
            }
        });
    }

    public String timeStamp() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        return sdf.format(calendar.getTime());
    }

    public String timeS() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(calendar.getTime());
    }

    private class DoBeginQueue extends AsyncTask<String,String,String> {



        int doctorD=(int)doctor.getSelectedItemId()+1;
        int departmentD = (int)department.getSelectedItemId()+1;
        String nameD=name.getText().toString();
        boolean isSuccess=false;
        String z;

        @Override
        protected void onPreExecute() {


            progressDialog.setMessage("Loading...");
            progressDialog.show();


            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            if(nameD.trim().equals("")) {
                z = "Please enter all fields....";
            }
            else
            {
                try {
                    Connection con = connectionClass.CONN();
                    if (con == null) {
                        z = "Please check your internet connection";
                    } else {

                        String query="insert into queue(QueueName, TimeStamp, EndTime,  Doctor_ID, Department_ID) values ('"+nameD+"', '"+timeStamp()+"', '"+timeS()+"', '"+doctorD+"', '"+departmentD+"')";


                        Statement stmt = con.createStatement();

                        stmt.execute(query);

                        z="Sucessfully Added Queue";

                        isSuccess=true;
                    }
                }
                catch (Exception ex)
                {
                    isSuccess = false;
                    z = "Exceptions"+ex;
                }
            }
            return z;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();


            if(isSuccess) {
                finish();
//                Intent intent=new Intent(BeginQueue.this,Dashboard.class);
//
//                // intent.putExtra("name",usernam);
//
//                startActivity(intent);
            }


            progressDialog.hide();

        }
    }

}
