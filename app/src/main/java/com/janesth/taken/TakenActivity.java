package com.janesth.taken;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TakenActivity extends AppCompatActivity {

    private EditText name;

    private Button car_one, car_one_release;
    private Button car_two, car_two_release;
    private Button car_three, car_three_release;
    private Button car_four, car_four_release;

    private String car_one_string;
    private String car_two_string;
    private String car_three_string;
    private String car_four_string;

    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken);

        name = (EditText) findViewById(R.id.name);

        car_one = (Button) findViewById(R.id.car_one);
        car_one_release = (Button) findViewById(R.id.car_one_release);
        car_two = (Button) findViewById(R.id.car_two);
        car_two_release = (Button) findViewById(R.id.car_two_release);
        car_three = (Button) findViewById(R.id.car_three);
        car_three_release = (Button) findViewById(R.id.car_three_release);
        car_four = (Button) findViewById(R.id.car_four);
        car_four_release = (Button) findViewById(R.id.car_four_release);

        car_one_string = getResources().getString(R.string.car_one);
        car_two_string = getResources().getString(R.string.car_two);
        car_three_string = getResources().getString(R.string.car_three);
        car_four_string = getResources().getString(R.string.car_four);

        setLabels();

    }

    public void setLabels() {

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
                        if(resultSet.getString("deviceID").equals(Build.MODEL)) {
                            car_one_release.setEnabled(true);
                            name.setText(resultSet.getString("name"));
                            name.setEnabled(false);
                        } else {
                            car_one_release.setEnabled(false);
                        }
                    } else {
                        car_one.setEnabled(true);
                        car_one_release.setEnabled(false);
                    }
                }

                resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = '" + car_two_string.toLowerCase() + "'");
                while(resultSet.next()) {
                    if (resultSet.getBoolean("booked")) {
                        car_two.setEnabled(false);
                        car_two.setText(car_two.getText() + " - " + resultSet.getString("name"));
                        if(resultSet.getString("deviceID").equals(Build.MODEL)) {
                            car_two_release.setEnabled(true);
                            name.setText(resultSet.getString("name"));
                            name.setEnabled(false);
                        } else {
                            car_two_release.setEnabled(false);
                        }
                    } else {
                        car_two.setEnabled(true);
                        car_two_release.setEnabled(false);
                    }
                }

                resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = '" + car_three_string.toLowerCase() + "'");
                while(resultSet.next()) {
                    if (resultSet.getBoolean("booked")) {
                        car_three.setEnabled(false);
                        car_three.setText(car_three.getText() + " - " + resultSet.getString("name"));
                        if(resultSet.getString("deviceID").equals(Build.MODEL)) {
                            car_three_release.setEnabled(true);
                            name.setText(resultSet.getString("name"));
                            name.setEnabled(false);
                        } else {
                            car_three_release.setEnabled(false);
                        }
                    } else {
                        car_three.setEnabled(true);
                        car_three_release.setEnabled(false);
                    }
                }

                resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = '" + car_four_string.toLowerCase() + "'");
                while(resultSet.next()) {
                    if (resultSet.getBoolean("booked")) {
                        car_four.setEnabled(false);
                        car_four.setText(car_four.getText() + " - " + resultSet.getString("name"));
                        if(resultSet.getString("deviceID").equals(Build.MODEL)) {
                            car_four_release.setEnabled(true);
                            name.setText(resultSet.getString("name"));
                            name.setEnabled(false);
                        } else {
                            car_four_release.setEnabled(false);
                        }
                    } else {
                        car_four.setEnabled(true);
                        car_four_release.setEnabled(false);
                    }
                }

                connection.close();
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