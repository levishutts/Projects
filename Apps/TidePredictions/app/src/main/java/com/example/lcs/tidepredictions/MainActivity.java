package com.example.lcs.tidepredictions;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by lcs on 7/15/2017.
 */

public class MainActivity extends AppCompatActivity {

    TideData tideData;
    Spinner tideSpinner;
    EditText dateEditText;
    Button showTidesButton;
    String[] stations;
    String[] stationNumbers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stations =  getResources().getStringArray(R.array.spinner_array);
        stationNumbers = new String[3];
        stationNumbers[0] = "9434098";
        stationNumbers[1] = "9437585";
        stationNumbers[2] = "9439011";

        tideSpinner = (Spinner) findViewById(R.id.tideSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tideSpinner.setAdapter(adapter);
        dateEditText = (EditText) findViewById(R.id.dateEditText);
        showTidesButton = (Button) findViewById(R.id.showTidesButton);
        tideData = new TideData(getApplicationContext());

        showTidesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinnerStation = tideSpinner.getSelectedItem().toString();
                String date = dateEditText.getText().toString();
                getStationInfo(spinnerStation);
                Intent listActivity = new Intent(getApplicationContext(), ListActivity.class);
                listActivity.putExtra("station", spinnerStation);
                listActivity.putExtra("date", date);
                startActivity(listActivity);
            }
        });
    }

    public void getStationInfo(String station){
        if(!tideData.dataExists(station)){
            new RestTask().execute(station);
        }
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

                    ParseHandler handler = new ParseHandler(stationName);
                    reader.setContentHandler(handler);

                    reader.parse(is);

                    items = handler.getTideList();
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
                tideData.insertTideItem(tide);
            }
        }
    }
}
