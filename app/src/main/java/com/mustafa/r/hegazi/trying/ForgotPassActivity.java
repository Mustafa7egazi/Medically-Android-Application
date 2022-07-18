package com.mustafa.r.hegazi.trying;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassActivity extends AppCompatActivity {
    EditText username,email, newPassword, confirmNewPassword;
    Button saveChange;
    DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);



        initViews();
        clickActions();
    }

    private void clickActions() {
        saveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _username = username.getText().toString().toLowerCase();
                String _email = email.getText().toString().toLowerCase();
                String _newPassword = newPassword.getText().toString();
                String _confirmNewPassword = confirmNewPassword.getText().toString();
                if (dbHelper.checkUsername(_username)) {
                    if (dbHelper.checkEmail(_email)) {
                        if (_newPassword.equals(_confirmNewPassword)) {
                            if (dbHelper.updatePassword(_newPassword, _username)) {
                                Toast.makeText(ForgotPassActivity.this, "Password updated", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotPassActivity.this, SignInActivity.class));
                            } else {
                                Toast.makeText(ForgotPassActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ForgotPassActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(ForgotPassActivity.this, "Incorrect Email", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ForgotPassActivity.this, "No such user", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        username = findViewById(R.id.usernamePassReset);
        email = findViewById(R.id.emailPassReset);
        newPassword = findViewById(R.id.newPasswordReset);
        confirmNewPassword = findViewById(R.id.confirmNewPasswordReset);
        saveChange = findViewById(R.id.savePasswordChange);
        dbHelper = new DBHelper(this);
    }
}