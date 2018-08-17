package com.example.lcs.tidepredictions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.Button;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by lcs on 7/15/2017.
 */

public class MainActivity extends AppCompatActivity{

    TideData tideData;
    ArrayList<TideItem> tideList;
    private TideItem tideItems;
    static final String DATE = "date";
    static final String DAY = "day";
    static final String HIGHLOW = "highLow";
    static final String TIME = "time";
    Spinner tideSpinner;
    EditText dateEditText;
    Button showTidesButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tideSpinner = (Spinner) findViewById(R.id.tideSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tideSpinner.setAdapter(adapter);
        dateEditText = (EditText) findViewById(R.id.dateEditText);
        showTidesButton = (Button) findViewById(R.id.showTidesButton);
        tideData = new TideData(getApplicationContext());

        createDatabase(Stations.TILLAMOOK);
        createDatabase(Stations.EMPIRE);
        createDatabase(Stations.FLORENCE);

        showTidesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent listActivity = new Intent(getApplicationContext(), ListActivity.class);
                listActivity.putExtra("station", tideSpinner.getSelectedItemPosition());
                listActivity.putExtra("date", dateEditText.getText().toString());
                startActivity(listActivity);
            }
        });
    }

    public void createDatabase(Stations station){
        InputSource is = null;

        switch (station){
            case TILLAMOOK:
                is = new InputSource(getResources().openRawResource(R.raw.tillamook_tides));
                break;
            case EMPIRE:
                is = new InputSource(getResources().openRawResource(R.raw.empire_tides));
                break;
            case FLORENCE:
                is = new InputSource(getResources().openRawResource(R.raw.florence_tides));
                break;
        }
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            ParseHandler handler = new ParseHandler(station);
            reader.setContentHandler(handler);

            reader.parse(is);

            tideList = handler.getTideList();
            if(tideList.isEmpty()){
                Log.e("Empty list", "Empty list");
            }
            for(TideItem tide: tideList){
                tideData.insertTideItem(tide);
            }

        }catch(Exception e){
            Log.e("parsing", e.toString());
        }

    }
}
