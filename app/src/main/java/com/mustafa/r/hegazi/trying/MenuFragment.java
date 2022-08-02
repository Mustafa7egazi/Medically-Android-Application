package com.mustafa.r.hegazi.trying;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


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
        usrName = v.findViewById(R.id.fullnameOfUser);
        email = v.findViewById(R.id.usernameOfUser);
        deleteAccount = v.findViewById(R.id.deleteAcc);
        usrName.setText("DR. "+SignInActivity.registeringFullname);
        email.setText("username: "+ActionTakeActivity.registeringUserIs);


         contactBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(getActivity(),SelectContact.class).putExtra("contact","contactBtn"));
//                 Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+5555555"));
//                 startActivity(i);
             }
         });

         logOutBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 SignInActivity.removeFromShared(getContext());
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