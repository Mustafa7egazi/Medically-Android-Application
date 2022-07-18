package com.mustafa.r.hegazi.trying;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class SignInActivity extends AppCompatActivity {
    Button signUp,login;
    TextView forgotPass;
    DBHelper dbHelper;
    EditText userOrEmail, password;
    RadioButton rememberMe,doNotRemember;
    sharedString returnData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initViews();
        // make password as dots by default
        password.setTransformationMethod(new PasswordTransformationMethod());

        sharedString data = sharedPrefRead();
        String user = data.getUsername();
        String pass= data.getPassword();
        userOrEmail.setText(user);
        password.setText(pass);

        clickActions();
    }

    private void clickActions()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _username = userOrEmail.getText().toString().toLowerCase();
                String _email = userOrEmail.getText().toString().toLowerCase();
                String _password = password.getText().toString();
                if (TextUtils.isEmpty(_username)|| TextUtils.isEmpty(_password))
                {
                    Toast.makeText(SignInActivity.this, "Enter you data", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (dbHelper.checkUsername(_username) || dbHelper.checkEmail(_email)) {
                        if (dbHelper.checkUserRegistered(_username, _email, _password)) {

                            String newString = _username;
                            newString = newString.substring(_username.length() - 3);
                            if (newString.equals("com"))
                            {
                                ActionTakeActivity.registeringEmail = _username;
                                Cursor c = dbHelper.getUsername(_password,_username);
                                c.moveToFirst();
                                ActionTakeActivity.registeringUserIs = c.getString(0);
                            }
                            else
                            {
                                ActionTakeActivity.registeringUserIs=_username;
                                Cursor c = dbHelper.getEmail(_password,_username);
                                c.moveToFirst();
                                ActionTakeActivity.registeringEmail = c.getString(0);
                            }

                            if (rememberMe.isChecked()) {
                                sharedPrefWrite(_username, _password);
                            } else if (doNotRemember.isChecked()) {
                                sharedPrefWrite("", "");
                            }

                            startActivity(new Intent(SignInActivity.this, ActionTakeActivity.class));
                        } else {
                            Toast.makeText(SignInActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(SignInActivity.this, "Incorrect username or email", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,ForgotPassActivity.class));
            }
        });
    }

    public void sharedPrefWrite(String username, String password) {
        SharedPreferences shared = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
    }

    private void initViews() {

        dbHelper = new DBHelper(this);
        signUp = findViewById(R.id.signUpBtn);
        login = findViewById(R.id.loginBtn);
        forgotPass = findViewById(R.id.forgotPassword);
        userOrEmail = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        rememberMe = findViewById(R.id.rememberMe);
        doNotRemember = findViewById(R.id.notRememberMe);

    }

    private sharedString sharedPrefRead()
    {
        returnData = new sharedString();
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        returnData.setUsername(sharedPreferences.getString("username",null));
        returnData.setPassword(sharedPreferences.getString("password",null));
        return returnData;
    }
    public static class sharedString
    {
        String username;
        String password;

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public sharedString() {
        }

        public String getUsername() {
            return username;
        }


        public String getPassword() {
            return password;
        }



    }}