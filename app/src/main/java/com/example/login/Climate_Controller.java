package com.example.login;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Climate_Controller extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate__controller);

        ActionBar actionbar=getSupportActionBar();
        actionbar.setTitle("Climate Controller");
    }
}
