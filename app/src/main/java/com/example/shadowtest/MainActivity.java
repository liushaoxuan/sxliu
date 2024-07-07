package com.example.shadowtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.graphics.Color;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewOutlineProvider;

import com.example.shadowtest.shadow.CustomImageView;


public class MainActivity extends AppCompatActivity {

    private AppCompatImageView imageView;
    private AppCompatImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (AppCompatImageView) findViewById(R.id.imageview);
        imageView2 = (AppCompatImageView) findViewById(R.id.imageview2);
//        imageView.setElevation(40);
//        imageView.setTranslationZ(30);
//        int color = Color.parseColor("#FF03DAC5");
//        imageView.setElevationShadowColor(color);
//        int color2 = Color.parseColor("#FFBB86FC");
//        ColorStateList colorStateList = ColorStateList.valueOf(color2);
//        imageView.setElevationShadowColor(colorStateList);

//        imageView.setCornerRadius(64.0f);
        imageView.setElevation(32f);
        imageView.setTranslationX(0);
        imageView.setTranslationX(6);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            imageView.setOutlineAmbientShadowColor(Color.RED);
//            imageView.setOutlineSpotShadowColor(Color.RED);
//        }
        imageView.setClipToOutline(true);
        imageView.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),64);
            }
        });
        imageView2.setClipToOutline(true);
        imageView2.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0,0,view.getWidth(),view.getHeight(),64);
            }
        });
    }
}