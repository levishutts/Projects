package com.example.charles.tideappv2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<TideItem> tideList;
    ListView itemsListView;
    TideDB tideDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        itemsListView = (ListView) findViewById(R.id.itemsListView);
        itemsListView.setOnItemClickListener(this);

        int stationNumber = getIntent().getIntExtra("station", 0);
        String station = "";
        switch (stationNumber){
            case 0:
                station = "Elkhorn";
                break;
            case 1:
                station = "Monterey";
                break;
            case 2:
                station = "Santa Barbara";
                break;
        }
        String selectedDate = getIntent().getStringExtra("date");
        int day = Integer.parseInt(selectedDate.substring(selectedDate.length()-2));
        int month = Integer.parseInt(selectedDate.substring(selectedDate.length()-4,selectedDate.length()-2) );
        String nextDay;
        String firstNextMonth;
        if (day <10) {
            nextDay = selectedDate.substring(0, selectedDate.length() - 2) + "0" +Integer.toString(day + 1);
        }else{
            nextDay = selectedDate.substring(0, selectedDate.length() - 2)  +Integer.toString(day + 1);
        }
        /*
        if(month<10) {
            firstNextMonth = selectedDate.substring(0, selectedDate.length() - 4) +"0"+ Integer.toString(month + 1) +selectedDate.substring(selectedDate.length()-2);
        }else{
            firstNextMonth = selectedDate.substring(0, selectedDate.length() - 4) + Integer.toString(month + 1) +selectedDate.substring(selectedDate.length()-2);
        }*/

        tideDB = new TideDB(getApplicationContext());
        ArrayList<HashMap<String, String>> data = new  ArrayList<HashMap<String, String>>();
        tideList = tideDB.getTideItems(selectedDate, station);
        tideList.addAll(tideDB.getTideItems(nextDay, station));

        if(tideList == null){
            Log.d("tidelist", "null");
        }
        for(TideItem tide: tideList){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("date", tide.getDayDate());
            map.put("time", tide.getTideTime());
            data.add(map);
        }
        int resource = R.layout.list_view_item;
        String[] from = {"date", "time"};
        int[] to = {R.id.date, R.id.time};

        SimpleAdapter adapter = new SimpleAdapter(this, data, resource, from, to);
        itemsListView.setAdapter(adapter);



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TideItem item = tideList.get(position);
        Toast.makeText(this, item.getMeasurements(), Toast.LENGTH_SHORT).show();
    }



}
