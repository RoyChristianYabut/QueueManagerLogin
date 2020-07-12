package com.benilde.queuemanagerlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;

public class EndQueue extends AppCompatActivity {

    private static String urlAddress="jdbc:mysql://192.168.64.2/queues.php";
    private Spinner queueName;

    private Button endQueue;

    ConnectionClass connectionClass;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_queue);

//        queueName = (Spinner)findViewById(R.id.spinnerEndQueue);
//
//        Downloader d=new Downloader(EndQueue.this,urlAddress,queueName);
//        d.execute();
//        ArrayAdapter<String> endQueueAdapter = new ArrayAdapter<String>(EndQueue.this,
//                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinnerQueueNames));
//        endQueueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        queueName.setAdapter(endQueueAdapter);
//
//        connectionClass = new ConnectionClass();
//        progressDialog = new ProgressDialog(this);
//
//        endQueue=(Button)findViewById(R.id.btnSubmitEnd);
//
//        endQueue.setOnClickListener(new View.OnClickListener() {//
//            @Override
//            public void onClick(View v) {
//
//
//
//                EndQueue.DoEndQueue doEndQueue = new EndQueue.DoEndQueue();
//                doEndQueue.execute();
//            }
//        });
    }

//    private class DoEndQueue extends AsyncTask<String,String,String> {
//
//
//
//        int queuenames=(int)queueName.getSelectedItemId()+1;
//        boolean isSuccess=false;
//        String z;
//
//        @Override
//        protected void onPreExecute() {
//
//
//            progressDialog.setMessage("Loading...");
//            progressDialog.show();
//
//
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    switch (which){
//                        case DialogInterface.BUTTON_POSITIVE:
//                            try {
//                                Connection con = connectionClass.CONN();
//                                if (con == null) {
//                                    z = "Please check your internet connection";
//                                } else {
//
//                                    String query="insert into queue(QueueName, TimeStamp, EndTime,  Doctor_ID, Department_ID) values ('"+name+"', '"+timeStamp()+"', '"+timeS()+"', '"+doctorD+"', '"+departmentD+"')";
//
//
//                                    Statement stmt = con.createStatement();
//
//                                    stmt.execute(query);
//
//                                    z="Sucessfully Added Queue";
//
//
//                                }
//                            }
//                            catch (Exception ex)
//                            {
//                                isSuccess = false;
//                                z = "Exceptions"+ex;
//                            }
//                            break;
//
//                        case DialogInterface.BUTTON_NEGATIVE:
//                            //No button clicked
//                            break;
//                    }
//                }
//            };
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(EndQueue.getActivity());
//            builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
//                    .setNegativeButton("No", dialogClickListener).show();
//
//
//            return z;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Toast.makeText(getBaseContext(),""+z,Toast.LENGTH_LONG).show();
//
//
//            if(isSuccess) {
//
//                Intent intent=new Intent(EndQueue.this,Dashboard.class);
//
//                // intent.putExtra("name",usernam);
//
//                startActivity(intent);
//            }
//
//
//            progressDialog.hide();
//
//        }
//    }

}
