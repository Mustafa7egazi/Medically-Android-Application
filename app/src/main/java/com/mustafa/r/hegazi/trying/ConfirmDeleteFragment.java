package com.mustafa.r.hegazi.trying;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class ConfirmDeleteFragment extends Fragment {

    EditText passwordExt;
    Button confirm;
    DBHelper dbHelper;
    DBHelper.PatientDB patientDB;
    androidx.appcompat.app.AlertDialog alertDialogBuilder;

    public ConfirmDeleteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_confirm_delete, container, false);
        passwordExt = v.findViewById(R.id.passwordForDelete);
        passwordExt.setTransformationMethod(new PasswordTransformationMethod());
        confirm = v.findViewById(R.id.confirmDeleteBtn);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper = new DBHelper(getContext());
                patientDB = new DBHelper.PatientDB(getContext());
                if(dbHelper.checkPasswordForDeleteAccount(passwordExt.getText().toString())){
                    alertDialogBuilder = new MaterialAlertDialogBuilder(getActivity(),R.style.ThemeOverlay_App_MaterialAlertDialog)
                            .setTitle("Account Deletion!").setMessage("All data will be lost")
                            .setCancelable(false)
                            .setPositiveButton("Delete anyway", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (dbHelper.deleteUser(ActionTakeActivity.registeringUserIs)>0)
                                    {
                                        patientDB.deleteAllPatients(ActionTakeActivity.registeringUserIs);
                                        Intent intent = new Intent(getActivity(), SignInActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        Toast.makeText(getContext(), "Account deleted!", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(getActivity(), "something went wrong while deletion", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
                    Toast.makeText(getActivity(), "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}

/*

 if (dbHelper.deleteUser(ActionTakeActivity.registeringUserIs)>0)
                              {
                                  patientDB.deleteAllPatients(ActionTakeActivity.registeringUserIs);
                                  Toast.makeText(getActivity(), "Account deleted!", Toast.LENGTH_SHORT).show();
                                  Intent intent = new Intent(getActivity(), SignInActivity.class);
                                  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                  startActivity(intent);
                              }
                              else {
                                  Toast.makeText(getActivity(), "something went wrong while deletion", Toast.LENGTH_SHORT).show();
                              }
 */