package com.benilde.queuemanagerlogin;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    String classs = "vxcd9lOiVlb9DcyuaKAzLr5qD7AQB+5gr7zwfl1MXhY=";

    String url="ER4diYPhlBRRZUZYsS4OBOb61fWGW3j8bfgbddGEPT7HP5kPGctwXHPTY6udJAM3";
    String un="kD9jED0psTijKJLS4DAVXQ==";
    String password="lGlxeD9VUG03UojXgyxBJQ==";

    @SuppressLint("NewApi")
    public Connection CONN(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Security sec =new Security();
        Connection conn = null;
        String ConnURL = null;
        try{
            Class.forName(sec.decrypt(classs));

            conn= DriverManager.getConnection(sec.decrypt(url),sec.decrypt(un),sec.decrypt(password));

            //conn=DriverManager.getConnection(ConnURL);
        }catch(SQLException se){
            Log.e("ERRO", se.getMessage());
        }catch(ClassNotFoundException e){
            Log.e("ERRO", e.getMessage());
        }catch(Exception e){
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }
}
