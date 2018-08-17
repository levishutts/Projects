package com.example.lcs.tripbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


import android.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by lcs on 7/17/2017.
 */

public class SecondActivity extends AppCompatActivity implements OnClickListener{

    private ArrayList<String> namesArray;
    private CheckName[] names;
    private String[] resultsNames;
    private int numNames = 0;
    private String name;
    private CheckName checkName;
    private int listLength;
    private EditText nameEditText;
    private EditText amountEditText;
    private Button enterButton;
    private Button doneButton;
    private HashMap<String, Account> people;
    private List<String> ListElementsArrayList;
    private ListView checkListView;
    private ListView resultsListView;
    private ArrayAdapter<String> adapter2;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        enterButton = (Button) findViewById(R.id.enterAmountButton);
        checkListView = (ListView) findViewById(R.id.nameListView);
        resultsListView = (ListView) findViewById(R.id.resultsListView);

        enterButton.setOnClickListener(this);
        checkListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
              //  CheckName checkName = (CheckName) parent.getItemAtPosition(position);
              //  Toast.makeText(getApplicationContext(),
              //          "Clicked on Row: " + checkName.getName(),
              //          Toast.LENGTH_LONG).show();
            }
        });

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        for(int i = 0; i < numNames; i++){
            name = names[i].getName();
            people.get(name).setAmount(prefs.getFloat(name, 0));
        }
    }

    @Override
    public void onPause(){
        Editor editor = prefs.edit();

        for(int i = 0; i < numNames; i++){
            name = names[i].getName();
            editor.putFloat(name, people.get(name).getAmount());
        }
        editor.commit();

        super.onPause();
    }


    @Override
    protected void onResume(){
        super.onResume();

        for(int i = 0; i < numNames; i++){
            name = names[i].getName();
            people.get(name).setAmount(prefs.getFloat(name, 0));
        }

        Bundle bundle = getIntent().getExtras();
        namesArray = (ArrayList<String>) bundle.getSerializable("names");
        listLength = (int) bundle.get("numNames");
        names = new CheckName[listLength];
        resultsNames = new String[listLength];
        people = new HashMap<>();

        //convert names from ArrayList<CheckName> to CheckName[]
        Iterator<String> iterator = namesArray.iterator();
        while(iterator.hasNext()){
            name = iterator.next();
            checkName = new CheckName(name, true);
            if (name != null) {
                Account account = new Account(name);
                people.put(name, account);
                names[numNames] = checkName;
                resultsNames[numNames] = name;
                numNames++;
            }
        }
        CustomAdapter adapter = new CustomAdapter(this, R.id.nameListView, names);
        checkListView.setAdapter(adapter);

        List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(resultsNames));
        adapter2 = new ArrayAdapter<String>
                (SecondActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);

        resultsListView.setAdapter(adapter2);
    }

    private void update() {
        if (!people.containsKey(nameEditText.getText().toString()) || amountEditText.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(),
                    "Please enter a correct name and amount",
                    Toast.LENGTH_LONG).show();
        } else {
            int divisor = 0;
            Account payer = people.get(nameEditText.getText().toString());
            Account debtor;
            float amount = Float.valueOf(amountEditText.getText().toString());
            payer.addAmount(amount);
            for (int i = 0; i < numNames; i++) {
                if (names[i].isChecked()) {
                    divisor++;
                }
            }
            amount /= divisor;
            for (int i = 0; i < numNames; i++) {
                if (names[i].isChecked()) {
                    name = names[i].getName();
                    debtor = people.get(name);
                    debtor.subAmount(amount);
                    String amountString = String.format("%.2f", debtor.getAmount());
                    resultsNames[i] = name + " " + amountString;
                }
            }
            List<String> ListElementsArrayList = new ArrayList<String>(Arrays.asList(resultsNames));
            adapter2 = new ArrayAdapter<String>
                    (SecondActivity.this, android.R.layout.simple_list_item_1, ListElementsArrayList);
            resultsListView.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.enterAmountButton:
                update();
                break;
        }
    }
}
