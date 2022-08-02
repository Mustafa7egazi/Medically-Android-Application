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
                String whatComingFromContact = intent.getStringExtra("contact");
                String whatComingFromMessage = intent.getStringExtra("message");
                if (whatComingFromMessage==null && whatComingFromContact.equals("contactBtn"))
                {
                    Intent iContact = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ data.get(i).getPhone()));
                    startActivity(iContact);
                }else {
                    String messageBody = intent.getStringExtra("message");
                    String welcomeTxt = "Dear, " + data.get(i).getName() + ", I hope you doing well.\n";
                    Uri uri = Uri.parse("smsto:" + data.get(i).getPhone());
                    Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                    it.putExtra("sms_body", welcomeTxt + messageBody);
                    startActivity(it);
                }
            }
        });

    }
}
