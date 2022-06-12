package com.mustafa.r.hegazi.trying;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class adapter extends BaseAdapter {
    Context context;
    ArrayList<custom_list> arrayList;

    public adapter(Context context, ArrayList<custom_list> arrayList) {
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
        view = inflater.inflate(R.layout.custom_list, null);
        TextView idValue = (TextView) view.findViewById(R.id.idListValue);
        TextView nameValue = (TextView) view.findViewById(R.id.nameListValue);
        TextView phoneValue = (TextView) view.findViewById(R.id.phoneListValue);
        TextView diseaseValue = (TextView) view.findViewById(R.id.diseaseListValue);
        TextView consultDateValue = (TextView) view.findViewById(R.id.consultListValue);
        TextView genderValue = (TextView) view.findViewById(R.id.genderListValue);

        custom_list custom_list = arrayList.get(position);
        idValue.setText(String.valueOf(custom_list.id));
        nameValue.setText(custom_list.name);
        phoneValue.setText(String.valueOf(custom_list.phone));
        diseaseValue.setText(custom_list.disease);
        consultDateValue.setText(custom_list.disease_history);
        genderValue.setText(custom_list.gender);
        return view;
    }
}
