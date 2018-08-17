package com.example.lcs.tidepredictions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lcs on 7/19/2017.
 */

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<TideItem> tideList;
    TideData tideDatabase;
    ListView itemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        itemsListView = (ListView) findViewById(R.id.tideListView);
        itemsListView.setOnItemClickListener(this);

        int stationNumber = getIntent().getIntExtra("station", 0);
        String station = "";
        switch (stationNumber){
            case 0:
                station = "Tillamook";
                break;
            case 1:
                station = "Empire";
                break;
            case 2:
                station = "Florence";
                break;
        }

        String selectedDate = getIntent().getStringExtra("date");

        tideDatabase = new TideData(getApplicationContext());
        ArrayList<HashMap<String, String>> data = new  ArrayList<HashMap<String, String>>();
        tideList = tideDatabase.getTideItems(selectedDate, station);

        for(TideItem tide: tideList){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("date", tide.getDay());
            map.put("time", tide.getTime());
            data.add(map);
        }

        int resource = R.layout.listview_items;
        String[] from = {"date", "time"};
        int[] to = {R.id.dateTextView, R.id.timeTextView};

        SimpleAdapter adapter = new SimpleAdapter(this, data, resource, from, to);
        itemsListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TideItem item = tideList.get(position);
        Toast.makeText(this, item.getFeet() + " ft\n" + item.getCm() + "cm", Toast.LENGTH_SHORT).show();
    }
}
