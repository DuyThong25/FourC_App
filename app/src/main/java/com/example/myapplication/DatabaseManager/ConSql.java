package com.example.myapplication.DatabaseManager;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConSql {
    Connection con;

    @SuppressLint("NewApi")
    public Connection conclass() {
        String ip = "SQL8006.site4now.net", port = "1433", db = "db_a9f440_login", username = "db_a9f440_login_admin", password = "Thanh260599";
        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String connectURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db + ";user=" + username + ";"+"password=" + password + ";";

            System.out.println("ok 1");
            con = DriverManager.getConnection(connectURL);
            System.out.println("ok 2");
        } catch (Exception e) {
            Log.e("CÓ LỖI  :", e.getMessage());
        }
        return con;
    }
}
