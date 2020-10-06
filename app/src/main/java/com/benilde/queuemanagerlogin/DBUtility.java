package com.benilde.queuemanagerlogin;

public interface DBUtility {
//    String jdbcDriverName = "QW3ELt19wnX4IncON0pytPYjJQ7R7KdcfgTP7pypuKI";
//    String jdbcUrl ="Fcr4UFEAihYbgy0ONZrH0yOieNzuRPvdibFJ3A1Rh9s=";
//    String dbUserName = "ZtZog8cq99D+yewURdclvw==";
//    String dbPassword = "Z+pHcz0oWkDRL90KPQ5jQA";

    String jdbcDriverName = "com.mysql.jdbc.Driver";
    String jdbcUrl = "jdbc:mysql://10.70.0.16/keruxdb";
    String dbUserName = "KeruxAdmin";
    String dbPassword = "admin";


    //String INSERT_DEPT_REC = "insert into department (Name, Description) values (?,?)";
    String SELECT_LIST_PATIENT ="SELECT * from department ";
    String LOGIN_CRED="select queuemanager_id, username, firstname, lastname, email from queuemanager where username=? and password =?";

}
