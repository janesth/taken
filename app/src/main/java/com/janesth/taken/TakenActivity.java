package com.janesth.taken;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.sql.Connection;

public class TakenActivity extends AppCompatActivity {

    private Button car_one;
    private Button car_two;

    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken);

        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.conclass();

        car_one = (Button) findViewById(R.id.car_one);
        car_two = (Button) findViewById(R.id.car_two);

        setLabels();

    }

    private void setLabels() {


        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.conclass();

        if(connection != null) {

            car_one.setText("Car One");
            car_two.setText("Car Two");
        } else {

            car_one.setText("HELP");
            car_two.setText("HELP");
        }

    }
}