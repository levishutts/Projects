package com.example.charles.tideappv2;

import android.content.Intent;
import android.os.AsyncTask;
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

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<TideItem> tideList;
    TideDB tideDB;
    Spinner locationSpinner;
    EditText dateEditText;
    Button showButton;
    String[] stations, stationNumbers;
    RestTask restTask = new RestTask();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stations =  getResources().getStringArray(R.array.spinner_array);
        stationNumbers = getResources().getStringArray(R.array.id_array);
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
        //parseIntoDB(Stations.ELKHORN);
        //parseIntoDB(Stations.MONTEREY);
        //parseIntoDB(Stations.SANTABARBARA);


        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinnerStation = locationSpinner.getSelectedItem().toString();
                String date = formattedDate(dateEditText.getText().toString());
                getStationInfo(spinnerStation);
                Intent listActivity = new Intent(getApplicationContext(), ListActivity.class);
                listActivity.putExtra("station", spinnerStation);
                listActivity.putExtra("date", date);
                startActivity(listActivity);

            }
        });
    }



    public void getStationInfo(String station){
        if(!tideDB.dataExists(station)){
            new RestTask().execute(station);
        }
    }
    /*
    public void parseIntoDB(String station){
        InputSource is = null;

        if (station.equals( stations[0])) {
            //replace with NOAA REST service xml download
            Log.e("parseIntoDB", "Elkhorn being parsed");
            is = new InputSource(getResources().openRawResource(R.raw.elkhorn_annual));
        }else if(station.equals( stations[1])) {
            Log.e("parseIntoDB", "Monterey being parsed");
            is = new InputSource(getResources().openRawResource(R.raw.monterey_annual));
        }else if(station.equals( stations[2])){
            Log.e("parseIntoDB", "Santa Barbara being parsed");
            is = new InputSource(getResources().openRawResource(R.raw.santabarbara_annual));
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
    }*/
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
    public class RestTask extends AsyncTask<String, Void, ArrayList<TideItem>> {

        // TODO:Replace deprecated HTTPEntity with a more up-to-date class
        // HTTPEntity Code adapted from: http://www.techrepublic.com/blog/software-engineer/calling-restful-services-from-your-android-app/

        @Override
        protected ArrayList<TideItem> doInBackground(String ... Params) {
            String baseUrlStart = "https://opendap.co-ops.nos.noaa.gov/axis/webservices/highlowtidepred/response.jsp?stationId=";
            String baseUrlEnd = "&beginDate=20170101&endDate=20171231&datum=MLLW&unit=0&timeZone=0&format=xml&Submit=Submit";
            String stationName = Params[0];
            String stationNumber= null;
            if (stationName.equals( stations[0])) {
                stationNumber = stationNumbers[0];
            }
            else if(stationName.equals( stations[1])) {
                stationNumber = stationNumbers[1];
            }
            else if(stationName.equals( stations[2])) {
                stationNumber = stationNumbers[2];
            }
            ArrayList<TideItem> items = null;
            try {
                URL url = new URL(baseUrlStart + stationNumber + baseUrlEnd);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                InputSource is = new InputSource(in);

                if (is != null) {
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser parser = factory.newSAXParser();
                    XMLReader reader = parser.getXMLReader();

                    TideHandler handler = new TideHandler(stationName);
                    reader.setContentHandler(handler);

                    reader.parse(is);

                    items = handler.getTidelist();
                    if(items.isEmpty()){
                        Log.e("Empty list", "Empty list");
                    }
                }

            } catch (Exception e) {
                Log.e("Rest Task", "doInBackground error: " + e.getLocalizedMessage());
            }
            return items;
        }

        @Override
        protected void onPostExecute(ArrayList<TideItem> tideItems) {
            super.onPostExecute(tideItems);
            for(TideItem tide: tideItems){
                tideDB.insertTideItem(tide);
            }
        }
    }
}
