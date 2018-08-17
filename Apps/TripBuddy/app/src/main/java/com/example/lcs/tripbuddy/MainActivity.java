package com.example.lcs.tripbuddy;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    private int MAX_NAMES = 20;

    private EditText name;
    private Button enterButton;
    private Button doneButton;
    private ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    private LinearLayout parentLayout;
    private ArrayList<String> names;
    private int numNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = new ArrayList<>();

        enterButton = (Button) findViewById(R.id.enterButton);
        doneButton = (Button) findViewById(R.id.doneButton);
        name = (EditText) findViewById(R.id.enterNameEditText);

        enterButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);
    }

    private void addName(){
        names.add(name.getText().toString());
        numNames++;
        name.setText("");
    }

    private void done(){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("names", names);
        intent.putExtra("numNames", numNames);
        startActivity(intent);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.enterButton:
                addName();
                break;
            case R.id.doneButton:
                done();
                break;
        }
    }
}
