package com.example.lcs.tripbuddy;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by lcs on 7/18/2017.
 */

public class CustomAdapter extends ArrayAdapter<CheckName> {

    CheckName[] names = null;
    Context context;

    public CustomAdapter(Context context, int textViewResourceId,
                           CheckName[] items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.names = items;
    }

    private class ViewHolder {
        TextView code;
        CheckBox name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.checkbox_row, null);

            holder = new ViewHolder();
            holder.code = (TextView) convertView.findViewById(R.id.textView1);
            holder.name = (CheckBox) convertView.findViewById(R.id.checkBox1);
            convertView.setTag(holder);

            holder.name.setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v ;
                    CheckName checkName = (CheckName) cb.getTag();
                    //Toast.makeText(context.getApplicationContext(),
                     //       "Clicked on Checkbox: " + cb.getText() +
                     //               " is " + cb.isChecked(),
                     //       Toast.LENGTH_LONG).show();
                    checkName.setChecked(cb.isChecked());
                }
            });
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        CheckName country = names[position];
        holder.code.setText("");
        holder.name.setText(country.getName());
        holder.name.setChecked(country.isChecked());
        holder.name.setTag(country);

        return convertView;

    }

}

