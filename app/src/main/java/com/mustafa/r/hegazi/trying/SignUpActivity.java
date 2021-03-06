package com.mustafa.r.hegazi.trying;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    Button logIn,register;
    EditText userName , email , password , confirmPassword;
    DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();
        // make password as dots by default
        password.setTransformationMethod(new PasswordTransformationMethod());
        confirmPassword.setTransformationMethod(new PasswordTransformationMethod());
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
                String _userName = userName.getText().toString().toLowerCase();
                String _email = email.getText().toString().toLowerCase();
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
                            ActionTakeActivity.registeringUserIs = _userName;
                            ActionTakeActivity.registeringEmail = _email;
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