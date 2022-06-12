package com.mustafa.r.hegazi.trying;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity {
    SwitchCompat switchCompat ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: Dark mode
//        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
//            setTheme(R.style.Theme_Dark);
//        }
//        else {
//            setTheme(R.style.Theme_Light);
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }
    // TODO: Messaging functionality
//    public void messageClick(View v)
//    {
//        EditText message = findViewById(R.id.messageBody);
//        Toast.makeText(this, message.getText().toString(), Toast.LENGTH_SHORT).show();
//    }
}