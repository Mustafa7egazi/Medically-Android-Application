package com.mustafa.r.hegazi.trying;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignInActivity extends AppCompatActivity {
    Button signUp,login;
    TextView forgotPass;
    DBHelper dbHelper;
    EditText userOrEmail, password;
    CheckBox rememberMe;
    sharedString returnData;

    public static String registeringFullname="";




    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        initViews();
        // make password as dots by default
        password.setTransformationMethod(new PasswordTransformationMethod());

        sharedString data = sharedPrefRead();
        String user = data.getUsername();
        String pass= data.getPassword();
        if (user !=null && pass !=null)
        {
            Cursor fullNameCursor = dbHelper.getFullname(user);
            if( fullNameCursor != null && fullNameCursor.moveToFirst()){
                registeringFullname = fullNameCursor.getString(fullNameCursor.getColumnIndex("fullname"));
                fullNameCursor.close();
                Cursor emailCursor =  dbHelper.getEmail(registeringFullname);
                if(emailCursor!=null && emailCursor.moveToFirst())
                {
                    ActionTakeActivity.registeringEmail = emailCursor.getString(emailCursor.getColumnIndex("email"));
                    emailCursor.close();
                }
            }
            ActionTakeActivity.registeringUserIs = user;
            startActivity(new Intent(getApplicationContext(),ActionTakeActivity.class));
        }

        clickActions();
    }

    private void clickActions()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                Cursor cursor = dbHelper.getFullname(userOrEmail.getText().toString().toLowerCase());
                if( cursor != null && cursor.moveToFirst() ){
                    registeringFullname = cursor.getString(cursor.getColumnIndex("fullname"));
                    cursor.close();
                }
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
                            Cursor c1 = dbHelper.getUsername(registeringFullname);
                            Cursor c2 = dbHelper.getEmail(registeringFullname);
                            if(c1 != null && c1.moveToFirst() && c2 != null && c2.moveToFirst())
                            {
                                ActionTakeActivity.registeringUserIs = c1.getString(c1.getColumnIndex("username"));
                                ActionTakeActivity.registeringEmail = c2.getString(c2.getColumnIndex("email"));
                                c1.close();
                                c2.close();
                            }

//                            String newString = _username;
//                            newString = newString.substring(_username.length() - 3);
//                            if (newString.equals("com"))
//                            {
//                                ActionTakeActivity.registeringEmail = _username;
//                                Cursor c = dbHelper.getUsername(registeringFullname);
//                                c.moveToFirst();
//                                ActionTakeActivity.registeringUserIs = c.getString(0);
//                            }
//                            else
//                            {
//                                ActionTakeActivity.registeringUserIs=_username;
//                                Cursor c = dbHelper.getEmail(registeringFullname);
//                                if( c != null && c.moveToFirst() ){
//                                    ActionTakeActivity.registeringEmail = c.getString(c.getColumnIndex("email"));
//                                    c.close();
//                                }
//                            }

                            if (rememberMe.isChecked()) {
                                sharedPrefWrite(_username, _password);
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


    private void initViews() {

        dbHelper = new DBHelper(this);
        signUp = findViewById(R.id.signUpBtn);
        login = findViewById(R.id.loginBtn);
        forgotPass = findViewById(R.id.forgotPassword);
        userOrEmail = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        rememberMe = findViewById(R.id.rememberMe);

    }
    public void sharedPrefWrite(String username, String password) {
        SharedPreferences shared = getSharedPreferences("rememberMe",MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
    }

    public sharedString sharedPrefRead()
    {
        returnData = new sharedString();
        SharedPreferences sharedPreferences = getSharedPreferences("rememberMe",MODE_PRIVATE);
        returnData.setUsername(sharedPreferences.getString("username",null));
        returnData.setPassword(sharedPreferences.getString("password",null));
        return returnData;
    }
    public static void removeFromShared(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences("rememberMe",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("username");
        editor.remove("password");
        editor.commit();
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