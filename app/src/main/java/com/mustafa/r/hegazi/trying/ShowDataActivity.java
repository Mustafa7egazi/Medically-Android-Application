package com.mustafa.r.hegazi.trying;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {
    TextView noData;
    DBHelper db =  new DBHelper(this);
    DBHelper.PatientDB patientDB = new DBHelper.PatientDB(this);
    ListView lst;
    adapter adapter;
    ArrayList<custom_list> arrayList;
    SwitchCompat switchCompat ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
//            setTheme(R.style.Theme_Dark);
//        }
//        else {
//            setTheme(R.style.Theme_Light);
//        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        lst = findViewById(R.id.lstView);
        arrayList = new ArrayList<>();
        showDataInListView();
    }

    private void showDataInListView() {
        arrayList = patientDB.getAllData();
        noData = findViewById(R.id.noData);
        if (!(arrayList.size()>0))
        {
            noData.setVisibility(View.VISIBLE);
        }
        else {
            adapter = new adapter(this, arrayList);
            lst.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}