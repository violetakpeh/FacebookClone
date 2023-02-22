package com.example.facebookclone.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private static Connection con;

    public static Connection getConnection()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp?autoReconnect=true&useSSL=false","root","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

}
