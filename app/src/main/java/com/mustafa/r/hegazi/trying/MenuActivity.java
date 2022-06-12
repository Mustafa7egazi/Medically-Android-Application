package com.mustafa.r.hegazi.trying;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class MenuActivity extends AppCompatActivity {

    public SwitchCompat switchCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_Dark);
        }
        else {
            setTheme(R.style.Theme_Light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        switchCompat = findViewById(R.id.bt_switch);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

            }
        });
    }

    public void onclickAC(View view) {

        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+5555555"));
        startActivity(i);

    }
    public void logOutClick(View view) {
        Intent intent = new Intent(this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void messageBtnClick(View view) {
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
    }
    public void onclickHE(View view) {
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }}