package com.janesth.taken;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;

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

                ResultSet resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = " + car_one.getId());
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

                resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = " + car_two.getId());
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

                resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = " + car_three.getId());
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

                resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = " + car_four.getId());
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
            car_one.setText(getResources().getString(R.string.car_placeholder));
            car_two.setText(getResources().getString(R.string.car_placeholder));
            car_three.setText(getResources().getString(R.string.car_placeholder));
            car_four.setText(getResources().getString(R.string.car_placeholder));
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
                if(checkIfMinOneBookingExists()) {
                    new AlertDialog.Builder(this)
                            .setTitle(getResources().getString(R.string.release_all_dialog_title))
                            .setMessage(getResources().getString(R.string.release_all_dialog_message))
                            .setNegativeButton(getResources().getString(R.string.release_all_dialog_opt1), null)
                            .setPositiveButton(getResources().getString(R.string.release_all_dialog_opt2), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dia, int f) {
                                    handleRelease(car_one, car_one_release);
                                    handleRelease(car_two, car_two_release);
                                    handleRelease(car_three, car_three_release);
                                    handleRelease(car_four, car_four_release);
                                }
                            }).create().show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_noBooking), Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;

        }
    }

    private boolean checkIfMinOneBookingExists() {
        if(car_one.isEnabled() && car_two.isEnabled() && car_three.isEnabled() && car_four.isEnabled()) {
            return false;
        }
        return true;
    }

    private void handleBooking(Button car_button, Button release_button) {
        if (name.getText() != null && !StringUtils.isEmpty(name.getText().toString())) {
            DBConnection dbConnection = new DBConnection();
            Statement statement = null;
            connection = dbConnection.conclass();

            if (connection != null) {

                try {
                    statement = connection.createStatement();

                    ResultSet resultSet = statement.executeQuery("SELECT * FROM `reservation` where `car` = " + car_button.getId());

                    while (resultSet.next()) {
                        if (resultSet.getBoolean("booked")) {
                            Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_alreadyBooked), Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            int result = statement.executeUpdate("UPDATE `reservation` SET `booked`=1, `deviceID`='" + Build.MODEL + "', `name`='" + name.getText() + "' WHERE `car` = " + car_button.getId() + ";");

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
            Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_noName), Toast.LENGTH_SHORT);
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
                String splitName = getResources().getString(R.string.car_placeholder);

                if(car_button.getText() != null && !StringUtils.isEmpty(car_button.getText().toString())) {
                    if(car_button.getText().toString().split(" -").length > 1)
                    splitName = car_button.getText().toString().split(" -")[0];
                }

                statement.executeUpdate("UPDATE `reservation` SET `booked`=0, `deviceID`='', `name`='' WHERE `car` = " + car_button.getId() + ";");

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