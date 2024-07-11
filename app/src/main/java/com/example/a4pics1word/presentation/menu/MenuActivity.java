package com.example.a4pics1word.presentation.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.a4pics1word.R;
import com.example.a4pics1word.presentation.info_screen.InfoActivity;
import com.example.a4pics1word.presentation.main_screen.MainActivity;

public class MenuActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.parseColor("#FFBBF4"));


        findViewById(R.id.play_game_btn).setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.info_game_btn).setOnClickListener(v->{
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        });
    }
}