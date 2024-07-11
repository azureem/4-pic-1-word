package com.example.a4pics1word.domain;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.a4pics1word.data.QuestionData;


public class MySharedPreference {

    private SharedPreferences myShar;

    private static MySharedPreference instance;

    public static MySharedPreference getInstance() {
        if (instance == null) {
            instance = new MySharedPreference();
        }
        return instance;
    }

    public static void init(Application application) {
        if (instance == null) {
            instance = new MySharedPreference();
        }
        instance.myShar = application.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
    }

//
//    public void saveQuestion(QuestionData question) {
//        myShar.edit().putInt("QUESTION", question.).apply();
//    }


    //simple brute force

    public void saveImg1(QuestionData questionData) {
        int img1 = questionData.getQuestion1();
        myShar.edit().putInt("IMAGE1", img1).apply();
    }


    @SuppressLint("CommitPrefEdits")
    public void saveImg2(QuestionData questionData) {
        int img2 = questionData.getQuestion1();
        myShar.edit().putInt("IMAGE2", img2).apply();
    }


    public void saveImg3(QuestionData questionData) {
        int img3 = questionData.getQuestion1();
        myShar.edit().putInt("IMAGE3", img3).apply();
    }


    public void saveImg4(QuestionData questionData) {
        int img4 = questionData.getQuestion1();
        myShar.edit().putInt("IMAGE4", img4).apply();
    }


    public int getImage1() {
        return myShar.getInt("IMAGE1", -1);
    }


    public int getImage2() {
        return myShar.getInt("IMAGE2", -1);
    }


    public int getImage3() {
        return myShar.getInt("IMAGE3", -1);
    }

    public int getImage4() {
        return myShar.getInt("IMAGE4", -1);
    }


    // variants

    public void saveVariants(QuestionData questionData) {
        String variant = questionData.getVariant();
        myShar.edit().putString("VARIANT", variant).apply();
    }


    public String getVariant() {
        return myShar.getString("VARIANT", null);
    }


    // answer

    public void saveAnswer(QuestionData questionData) {
        String answer = questionData.getAnswer();
        myShar.edit().putString("ANSWER", answer).apply();
    }




    public void saveDelete(int times){
        myShar.edit().putInt("DELETE", times).apply();
    }

    public int getDelete(){
        return myShar.getInt("DELETE", 0);
    }
    public void saveCoins(int coins) {
        myShar.edit().putInt("COIN", coins).apply();
    }

    public int getCoins() {
        return myShar.getInt("COIN", 0);
    }


    public void saveLevel(int levelIndex) {
        myShar.edit().putInt("LEVEL", levelIndex).apply();
    }

    public int getLevel() {
        return myShar.getInt("LEVEL", 0);
    }
    public void removeCoins() {
       myShar.edit().remove("COIN").apply();
    }

    public void removeLevel() {
      myShar.edit().remove("LEVEL").apply();
    }



}
