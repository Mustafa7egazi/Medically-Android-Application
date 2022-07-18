package com.mustafa.r.hegazi.trying;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class HelpActivity extends AppCompatActivity {

TextView emailTo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        emailTo = findViewById(R.id.emailTo);

        emailTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "Mustafareda3210@gmail.com"));
                intent.putExtra(intent.EXTRA_SUBJECT, "Medically application feedback");
                intent.putExtra(intent.EXTRA_TEXT, "Type your message");
                startActivity(intent);
            }
        });

    }

}