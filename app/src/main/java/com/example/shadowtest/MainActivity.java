package com.example.shadowtest;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;

import com.example.shadowtest.shadow.CustomImageView;


public class MainActivity extends AppCompatActivity {

    private CustomImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (CustomImageView) findViewById(R.id.imageview);
//        imageView.setElevation(40);
//        imageView.setTranslationZ(30);
//        int color = Color.parseColor("#FF03DAC5");
//        imageView.setElevationShadowColor(color);
//        int color2 = Color.parseColor("#FFBB86FC");
//        ColorStateList colorStateList = ColorStateList.valueOf(color2);
//        imageView.setElevationShadowColor(colorStateList);

        imageView.setCornerRadius(64.0f);
    }
}