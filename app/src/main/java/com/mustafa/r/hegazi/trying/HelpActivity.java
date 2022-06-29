package com.mustafa.r.hegazi.trying;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class HelpActivity extends AppCompatActivity {
    SwitchCompat switchCompat ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        switchCompat = findViewById(R.id.bt_switch);

    }

}