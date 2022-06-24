package com.janesth.taken;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    private void setLabels() {

        DBConnection dbConnection = new DBConnection();
        Statement statement = null;
        connection = dbConnection.conclass();

        if (connection != null) {

            try {
                statement = connection.createStatement();

                car_one.setText(car_one_string);
                car_two.setText(car_two_string);
                car_three.setText(car_three_string);
                car_four.setText(car_four_string);

                ResultSet resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = '" + car_one_string.toLowerCase() + "'");
                while (resultSet.next()) {
                    if (resultSet.getBoolean("booked")) {
                        car_one.setEnabled(false);
                        car_one.setText(car_one.getText() + " - " + resultSet.getString("name"));
                        if (resultSet.getString("deviceID").equals(Build.MODEL)) {
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
                while (resultSet.next()) {
                    if (resultSet.getBoolean("booked")) {
                        car_two.setEnabled(false);
                        car_two.setText(car_two.getText() + " - " + resultSet.getString("name"));
                        if (resultSet.getString("deviceID").equals(Build.MODEL)) {
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
                while (resultSet.next()) {
                    if (resultSet.getBoolean("booked")) {
                        car_three.setEnabled(false);
                        car_three.setText(car_three.getText() + " - " + resultSet.getString("name"));
                        if (resultSet.getString("deviceID").equals(Build.MODEL)) {
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
                while (resultSet.next()) {
                    if (resultSet.getBoolean("booked")) {
                        car_four.setEnabled(false);
                        car_four.setText(car_four.getText() + " - " + resultSet.getString("name"));
                        if (resultSet.getString("deviceID").equals(Build.MODEL)) {
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
                ex.printStackTrace();
            }

        } else {
            car_one.setText("HELP");
            car_two.setText("HELP");
            car_three.setText("HELP");
            car_four.setText("HELP");
        }

    }

    public void refresh(View view) {
        setLabels();
    }

    public void book(View view) {

        switch (view.getId()) {
            case R.id.car_one:
                handleBooking(car_one, car_one_release);
                break;
            case R.id.car_two:
                handleBooking(car_two, car_two_release);
                break;
            case R.id.car_three:
                handleBooking(car_three, car_three_release);
                break;
            case R.id.car_four:
                handleBooking(car_four, car_four_release);
                break;
        }
    }

    public void release(View view) {
        switch (view.getId()) {
            case R.id.car_one_release:
                handleRelease(car_one, car_one_release);
                break;
            case R.id.car_two_release:
                handleRelease(car_two, car_two_release);
                break;
            case R.id.car_three_release:
                handleRelease(car_three, car_three_release);
                break;
            case R.id.car_four_release:
                handleRelease(car_four, car_four_release);
                break;
            case R.id.release_all:
                handleRelease(car_one, car_one_release);
                handleRelease(car_two, car_two_release);
                handleRelease(car_three, car_three_release);
                handleRelease(car_four, car_four_release);
                break;

        }
    }

    private void handleBooking(Button car_button, Button release_button) {
        if (!name.getText().equals(null) && !name.getText().toString().equals("")) {
            DBConnection dbConnection = new DBConnection();
            Statement statement = null;
            connection = dbConnection.conclass();

            if (connection != null) {

                try {
                    statement = connection.createStatement();

                    ResultSet resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = '" + car_button.getText().toString().toLowerCase() + "'");

                    while (resultSet.next()) {
                        if (resultSet.getBoolean("booked")) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Bereits reserviert. Bitte aktualisieren.", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            int result = statement.executeUpdate("UPDATE `reservation` SET `booked`=1, `deviceID`='" + Build.MODEL + "', `name`='" + name.getText() + "' WHERE `car` = '" + car_button.getText() + "';");

                            car_button.setText(car_button.getText() + " - " + name.getText());
                            car_button.setEnabled(false);
                            release_button.setEnabled(true);
                            name.setEnabled(false);
                        }
                    }

                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Bitte Namen eingeben.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void handleRelease(Button car_button, Button release_button) {
        DBConnection dbConnection = new DBConnection();
        Statement statement = null;
        connection = dbConnection.conclass();

        if (connection != null) {
            try {
                statement = connection.createStatement();

                // BAD CODE
                final String splitName = car_button.getText().toString().split(" -")[0];

                int result = statement.executeUpdate("UPDATE `reservation` SET `booked`=0, `deviceID`='', `name`='' WHERE `car` = '" + splitName + "';");

                car_button.setText(splitName);
                car_button.setEnabled(true);
                release_button.setEnabled(false);
                name.setText("");
                name.setEnabled(true);

                connection.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}