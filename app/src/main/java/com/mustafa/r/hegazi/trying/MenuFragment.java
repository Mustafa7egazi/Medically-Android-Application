package com.mustafa.r.hegazi.trying;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class MenuFragment extends Fragment {
    Button contactBtn,logOutBtn,showMessageBtn,helpBtn;
    TextView usrName,email,deleteAccount;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


         View v = inflater.inflate(R.layout.fragment_menu, container, false);
         contactBtn = v.findViewById(R.id.contactBtn);
         logOutBtn = v.findViewById(R.id.logOutBtn);
        showMessageBtn = v.findViewById(R.id.showMessageBtn);
        helpBtn = v.findViewById(R.id.helpBtn);
        usrName = v.findViewById(R.id.usrName);
        email = v.findViewById(R.id.emailOfUser);
        deleteAccount = v.findViewById(R.id.deleteAcc);
        usrName.setText(""+ActionTakeActivity.registeringUserIs);
        email.setText(""+ActionTakeActivity.registeringEmail);


         contactBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+5555555"));
                 startActivity(i);
             }
         });

         logOutBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(), SignInActivity.class);
                 intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(intent);
             }
         });

         showMessageBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(), MessageActivity.class);
                 startActivity(intent);
             }
         });

         helpBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(), HelpActivity.class);
                 startActivity(intent);
             }
         });

         deleteAccount.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 androidx.fragment.app.FragmentManager fragmentManager = getParentFragmentManager();
                 fragmentManager.beginTransaction()
                         .replace(R.id.fragment_container_view, ConfirmDeleteFragment.class, null)
                         .commit();
             }
         });

         return v;
    }


}