package com.example.lcs.tidepredictions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    private ArrayList<TideItem> tideList;
    private ListView itemsListView;
    private TideData tideDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        itemsListView = (ListView) findViewById(R.id.tideListView);
        String station = getIntent().getStringExtra("station");
        String selectedDate = getIntent().getStringExtra("date");
        int day = Integer.parseInt(selectedDate.substring(selectedDate.length() - 2));
        String nextDay;
        tideDatabase = new TideData(getApplicationContext());
        ArrayList<HashMap<String, String>> data = new  ArrayList<HashMap<String, String>>();
        tideList = tideDatabase.getTideItems(selectedDate, station);

        itemsListView.setOnItemClickListener(this);

        if (day < 10) {
            nextDay = selectedDate.substring(0, selectedDate.length() - 2) + "0" + Integer.toString(day + 1);
        }else{
            nextDay = selectedDate.substring(0, selectedDate.length() - 2)  + Integer.toString(day + 1);
        }

        tideList.addAll(tideDatabase.getTideItems(nextDay, station));

        for(TideItem tide: tideList){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("date", tide.getDayDate());
            map.put("time", tide.getTideTime());
            data.add(map);
        }
        String[] from = {"date", "time"};
        int[] to = {R.id.dateTextView, R.id.timeTextView};
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.listview_items, from, to);
        itemsListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TideItem item = tideList.get(position);
        Toast.makeText(this, item.getFeet()+ " ft\n" + item.getCm() + " cm", Toast.LENGTH_SHORT).show();
    }
}
