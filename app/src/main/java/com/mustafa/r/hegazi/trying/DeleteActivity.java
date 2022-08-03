package com.mustafa.r.hegazi.trying;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.window.SplashScreen;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class DeleteActivity extends AppCompatActivity {
    Button delete;
    EditText patientNatID;
    DBHelper dbHelper;
    DBHelper.PatientDB patientDB;

    androidx.appcompat.app.AlertDialog alertDialogBuilder;

    @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        initView();
        delete.setOnClickListener(new View.OnClickListener() {
            LinearLayout parent = findViewById(R.id.deleteParent);

            @Override
            public void onClick(View view) {
                String id = patientNatID.getText().toString();
                if (TextUtils.isEmpty(id))
                {

                    Snackbar.make(parent,"Enter id for deleting",-1).show();
                }
                else if (id.length() != 14)
                {
                    Snackbar.make(parent," Id must be 14 digit",-1).show();
                }
                else if(patientDB.patientSearch(id))
                {
                    alertDialogBuilder = new MaterialAlertDialogBuilder(DeleteActivity.this,R.style.ThemeOverlay_App_MaterialAlertDialog)
                            .setTitle("Warning!").setMessage("Confirm deletion")
                            .setCancelable(false)
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(patientDB.deletePatient(id))
                                    {
                                        Toast.makeText(DeleteActivity.this, "Patient deleted", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Toast.makeText(DeleteActivity.this, "Deleting failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .setIcon(R.drawable.ic_warning_24)
                            .show();

                }
                else
                {
                    Toast.makeText(DeleteActivity.this, "No such patient id", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void initView()
    {
        delete = findViewById(R.id.deleteBtnItself);
        patientNatID = findViewById(R.id.patientNatIdDelete);
        dbHelper = new DBHelper(this);
        patientDB = new DBHelper.PatientDB(this);
    }
}