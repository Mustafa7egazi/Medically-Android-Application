package com.mustafa.r.hegazi.trying;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity {

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView navigationView = findViewById(R.id.menuBottomNav);
        View v = findViewById(R.id.sideMenu);
        v.setBackgroundColor(Color.YELLOW);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getBaseContext(),ActionTakeActivity.class));
                        return true;
                }

                return false;
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