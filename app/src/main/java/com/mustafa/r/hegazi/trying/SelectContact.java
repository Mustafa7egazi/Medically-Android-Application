package com.mustafa.r.hegazi.trying;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SelectContact extends AppCompatActivity {
    TextView noData;
    ListView listy;
    ArrayList<custom_list2> data;
    DBHelper.PatientDB patientDB = new DBHelper.PatientDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_contact);
        noData = findViewById(R.id.noContacts);
        listy = findViewById(R.id.listy);
        data = new ArrayList<>();
        data = patientDB.getNameAndPhone();

        if (!(data.size() > 0)) {
            noData.setVisibility(View.VISIBLE);
        } else {
            Adapter2 adapter2 = new Adapter2(this, data);
            listy.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();
        }


        listy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = getIntent();
                String messageBody = intent.getStringExtra("message");
                String welcomeTxt = "Dear, "+(String) data.get(i).getName()+ ", I hope you doing well.\n";
                //Toast.makeText(SelectContact.this, welcomeTxt+messageBody, Toast.LENGTH_SHORT).show();


                Uri uri = Uri.parse("smsto:"+(String) data.get(i).getPhone());
                Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                it.putExtra("sms_body", welcomeTxt+messageBody);
                startActivity(it);
            }
        });
    }
}
