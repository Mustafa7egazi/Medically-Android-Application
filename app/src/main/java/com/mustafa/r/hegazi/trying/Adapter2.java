package com.mustafa.r.hegazi.trying;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter2 extends BaseAdapter {
    Context context;
    ArrayList<custom_list2> arrayList;


    public Adapter2(Context context, ArrayList<custom_list2> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custom_list2, null);
        TextView nameValue = (TextView) view.findViewById(R.id.contactName);
        TextView phoneValue = (TextView) view.findViewById(R.id.contactPhone);


        custom_list2 myList = arrayList.get(position);
        nameValue.setText(myList.name);
        phoneValue.setText(String.valueOf(myList.phone));
        return view;
    }
}
