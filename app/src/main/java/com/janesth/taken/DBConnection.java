package com.janesth.taken;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    final String DB_URL = "jdbc:mariadb://" + BuildConfig.DBURL + "/" + BuildConfig.DBNAME;

    final String USER = BuildConfig.DBUSER;
    final String PASS = BuildConfig.DBPASSWORD;

    public Connection conclass() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);

            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy gfgPolicy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(gfgPolicy);
            }
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }

        return conn;
    }
}
