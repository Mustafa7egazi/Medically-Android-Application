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
    EditText fullName, userName , email , password , confirmPassword;
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

                String _fullname = fullName.getText().toString();
                String _userName = userName.getText().toString().toLowerCase();
                String _email = email.getText().toString().toLowerCase();
                String _password = password.getText().toString();
                String _confirmPassword = confirmPassword.getText().toString();

                // count number of names in fullname
                int count = 1;
                for (int i = 0; i < _fullname.length() - 1; i++)
                {
                    if ((_fullname.charAt(i) == ' ') && (_fullname.charAt(i + 1) != ' '))
                    {
                        count++;
                    }
                }
                // processing checking validity
                if(TextUtils.isEmpty(_fullname)||TextUtils.isEmpty(_userName) || TextUtils.isEmpty(_email) || TextUtils.isEmpty(_password) || TextUtils.isEmpty(_confirmPassword))
                {
                    Toast.makeText(SignUpActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (!_password.equals(_confirmPassword))
                    {
                        Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        if (dbHelper.checkFullname(_fullname))
                        {
                            Toast.makeText(SignUpActivity.this, "There exist name matching, change your last name", Toast.LENGTH_SHORT).show();
                        }else if (count != 3){
                            Toast.makeText(SignUpActivity.this, "Fullname must have exactly 3 names ", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if (dbHelper.checkUserExist(_userName, _email)) {
                                Toast.makeText(SignUpActivity.this, "user already exist", Toast.LENGTH_SHORT).show();
                            } else {
                                if (_password.length()<8 && _confirmPassword.length()<8)
                                {
                                    Toast.makeText(SignUpActivity.this, "Password must be 8 chars at least", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    ActionTakeActivity.registeringUserIs = _userName;
                                    ActionTakeActivity.registeringEmail = _email;
                                    SignInActivity.registeringFullname = _fullname;
                                    dbHelper.registerUser(_fullname, _userName, _password, _email);
                                    Toast.makeText(SignUpActivity.this, "Success Registration", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignUpActivity.this, ActionTakeActivity.class));
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    private void initViews() {
        logIn = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);
        fullName = findViewById(R.id.registerFullName);
        userName = findViewById(R.id.registerUsername);
        email = findViewById(R.id.registerEmail);
        password = findViewById(R.id.registerPassword);
        confirmPassword = findViewById(R.id.registerConfirmPassword);
        dbHelper = new DBHelper(this);
    }
}