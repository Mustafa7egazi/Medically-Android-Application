package com.mustafa.r.hegazi.trying;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    Button logIn,register;
    EditText userName , email , password , confirmPassword;
    DBHelper dbHelper;
    SwitchCompat switchCompat ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.Theme_Dark);
        }
        else {
            setTheme(R.style.Theme_Light);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        switchCompat = findViewById(R.id.bt_switch);
        initViews();
        clickActions();
    }

    private void clickActions() {
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _userName = userName.getText().toString();
                String _email = email.getText().toString();
                String _password = password.getText().toString();
                String _confirmPassword = confirmPassword.getText().toString();
                if(TextUtils.isEmpty(_userName) || TextUtils.isEmpty(_email) || TextUtils.isEmpty(_password) || TextUtils.isEmpty(_confirmPassword))
                {
                    Toast.makeText(SignUpActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (!_password.equals(_confirmPassword))
                    {
                        Toast.makeText(SignUpActivity.this, "Passwords does not match", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if(dbHelper.checkUserExist(_userName,_email))
                        {
                            Toast.makeText(SignUpActivity.this, "user already exist", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            dbHelper.registerUser(_userName,_password,_email);
                            Toast.makeText(SignUpActivity.this, "Success Registration", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this,ActionTakeActivity.class));
                        }
                    }
                }
            }
        });
    }

    private void initViews() {
        logIn = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);
        userName = findViewById(R.id.registerUsername);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        confirmPassword = findViewById(R.id.registerConfirmPassword);
        dbHelper = new DBHelper(this);
    }
}