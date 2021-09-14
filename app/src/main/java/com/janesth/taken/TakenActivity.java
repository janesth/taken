package com.janesth.taken;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class TakenActivity extends AppCompatActivity {

    private Button car_one;
    private Button car_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taken);

        car_one = (Button) findViewById(R.id.car_one);
        car_two = (Button) findViewById(R.id.car_two);

        setLabels();

    }

    private void setLabels() {
        car_one.setText("Car One");
        car_two.setText("Car Two");
    }
}