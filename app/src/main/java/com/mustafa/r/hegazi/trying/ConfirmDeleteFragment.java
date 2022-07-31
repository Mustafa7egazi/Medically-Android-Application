package com.mustafa.r.hegazi.trying;

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

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class ConfirmDeleteFragment extends Fragment {

    EditText passwordExt;
    Button confirm;
    DBHelper dbHelper;
    DBHelper.PatientDB patientDB;

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
                    Snackbar.make(v,"Stored data will be lost", BaseTransientBottomBar.LENGTH_INDEFINITE).setAction("Delete anyway", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
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
                        }
                    }).show();
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