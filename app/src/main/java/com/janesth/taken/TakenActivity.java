package com.janesth.taken;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TakenActivity extends AppCompatActivity {

    private Button car_one;
    private Button car_two;
    private Button car_three;
    private Button car_four;

    private String car_one_string;
    private String car_two_string;
    private String car_three_string;
    private String car_four_string;

    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken);

        car_one = (Button) findViewById(R.id.car_one);
        car_two = (Button) findViewById(R.id.car_two);
        car_three = (Button) findViewById(R.id.car_three);
        car_four = (Button) findViewById(R.id.car_four);

        car_one_string = getResources().getString(R.string.car_one);
        car_two_string = getResources().getString(R.string.car_two);
        car_three_string = getResources().getString(R.string.car_three);
        car_four_string = getResources().getString(R.string.car_four);

        setLabels();

    }

    private void setLabels() {

        DBConnection dbConnection = new DBConnection();
        Statement statement = null;
        connection = dbConnection.conclass();

        if(connection != null) {

            try {
                statement = connection.createStatement();

                car_one.setText(car_one_string);
                car_two.setText(car_two_string);
                car_three.setText(car_three_string);
                car_four.setText(car_four_string);

                ResultSet resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = '" + car_one_string.toLowerCase() +"'");
                while(resultSet.next()) {
                    if (resultSet.getBoolean("booked")) {
                        car_one.setEnabled(false);
                        car_one.setText(car_one.getText() + " - " + resultSet.getString("name"));
                    } else {
                        car_one.setEnabled(true);
                    }
                }

                resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = '" + car_two_string.toLowerCase() + "'");
                while(resultSet.next()) {
                    if (resultSet.getBoolean("booked")) {
                        car_two.setEnabled(false);
                        car_two.setText(car_two.getText() + " - " + resultSet.getString("name"));
                    } else {
                        car_two.setEnabled(true);
                    }
                }

                resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = '" + car_three_string.toLowerCase() + "'");
                while(resultSet.next()) {
                    if (resultSet.getBoolean("booked")) {
                        car_three.setEnabled(false);
                        car_three.setText(car_three.getText() + " - " + resultSet.getString("name"));
                    } else {
                        car_three.setEnabled(true);
                    }
                }

                resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = '" + car_four_string.toLowerCase() + "'");
                while(resultSet.next()) {
                    if (resultSet.getBoolean("booked")) {
                        car_four.setEnabled(false);
                        car_four.setText(car_four.getText() + " - " + resultSet.getString("name"));
                    } else {
                        car_four.setEnabled(true);
                    }
                }

            } catch (SQLException ex) {

            }

        } else {

            car_one.setText("HELP");
            car_two.setText("HELP");
            car_three.setText("HELP");
            car_four.setText("HELP");
        }

    }
}