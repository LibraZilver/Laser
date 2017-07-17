package com.example.watcharac.laser;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText laserNumber = (EditText) findViewById(R.id.editText_laser);
        laserNumber.addTextChangedListener(new LaserNumberTextWatcher());
        laserNumber.setFilters(new InputFilter[] { new LaserNumberFilter(), new InputFilter.LengthFilter(14) });

    }
}
