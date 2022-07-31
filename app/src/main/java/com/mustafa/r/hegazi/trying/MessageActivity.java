package com.mustafa.r.hegazi.trying;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity {
    EditText messageBody;
    Button select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        messageBody = findViewById(R.id.messageBody);
        select = findViewById(R.id.selectToSendBtn);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MessageActivity.this, SelectContact.class);
                intent.putExtra("message", messageBody.getText().toString());
                if (TextUtils.isEmpty(messageBody.getText().toString())) {
                    Toast.makeText(MessageActivity.this, "Please, enter your message\nbefore selecting a contact", Toast.LENGTH_LONG).show();
                } else {
                    startActivity(intent);
                }
            }
        });
    }
}