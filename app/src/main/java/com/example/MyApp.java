package com.example;

import android.app.Application;

import com.example.a4pics1word.domain.MySharedPreference;

public class MyApp  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MySharedPreference.init(this);
    }
}
