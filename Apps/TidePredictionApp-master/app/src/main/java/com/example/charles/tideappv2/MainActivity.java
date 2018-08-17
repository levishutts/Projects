package com.example.charles.tideappv2;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<TideItem> tideList;
    TideDB tideDB;
    Spinner locationSpinner;
    EditText dateEditText;
    Button showButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Spinner
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        //dateEditText
        dateEditText = (EditText) findViewById(R.id.dateEditText);

        //showButton
        showButton = (Button) findViewById(R.id.showButton);


        tideDB = new TideDB(getApplicationContext());
        parseIntoDB(Stations.ELKHORN);
        parseIntoDB(Stations.MONTEREY);
        parseIntoDB(Stations.SANTABARBARA);


        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent listActivity = new Intent(getApplicationContext(), ListActivity.class);
                listActivity.putExtra("station", locationSpinner.getSelectedItemPosition());
                listActivity.putExtra("date", formattedDate(dateEditText.getText().toString()));
                startActivity(listActivity);
            }
        });
    }




    public void parseIntoDB(Stations station){
        InputSource is = null;

        switch (station){
            case ELKHORN:
                is = new InputSource(getResources().openRawResource(R.raw.elkhorn_annual));
                break;
            case MONTEREY:
                is = new InputSource(getResources().openRawResource(R.raw.monterey_annual));
                break;
            case SANTABARBARA:
                is = new InputSource(getResources().openRawResource(R.raw.santabarbara_annual));
                break;
        }
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();

            TideHandler handler = new TideHandler(station);
            reader.setContentHandler(handler);

            reader.parse(is);

            tideList = handler.getTidelist();
            if(tideList.isEmpty()){
                Log.e("Empty list", "Empty list");
            }
            for(TideItem tide: tideList){
                tideDB.insertTideItem(tide);
            }

        }catch(Exception e){
            Log.e("parsing", e.toString());
        }

    }
    public String formattedDate(String date){
        String seperator = "";
        String[] parts;
        if(date.contains(".")){
            seperator = ".";
        }else if (date.contains("-")){
            seperator = "-";
        }else if (date.contains("/")){
            seperator = "/";
        }
        parts = date.split(seperator);
        return(parts[2]+parts[0] + parts[1]);
    }
}
