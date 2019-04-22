package com.example.login;

import android.app.Application;

import com.google.firebase.FirebaseApp;

public class Applcation extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(getApplicationContext());
    }
}
