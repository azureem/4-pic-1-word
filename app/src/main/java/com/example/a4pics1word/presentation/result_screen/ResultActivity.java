package com.example.a4pics1word.presentation.result_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.example.a4pics1word.R;
import com.example.a4pics1word.domain.MySharedPreference;
import com.example.a4pics1word.presentation.menu.MenuActivity;

public class ResultActivity extends AppCompatActivity {

    LottieAnimationView resultAnim;
    MySharedPreference myShar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.parseColor("#F7CD4F"));

        setContentView(R.layout.activity_result);


        resultAnim = findViewById(R.id.resultfire_anim);
        resultAnim.playAnimation();
            myShar = MySharedPreference.getInstance();
        findViewById(R.id.back_result).setOnClickListener(v->{
            finish();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            myShar.removeCoins();
            myShar.removeLevel();
        });
    }

    @Override
    public void onBackPressed() {

    }
}