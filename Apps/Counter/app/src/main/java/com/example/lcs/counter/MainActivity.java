package com.example.lcs.counter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    //variables for widgets
    private TextView countTextView;
    private Button countButton;
    private Button resetButton;

    //define SharedPreferences object
    private SharedPreferences savedCount;

    //count variable
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to widgets
        countTextView = (TextView) findViewById(R.id.countTextView);
        countButton = (Button) findViewById(R.id.countButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        //set listeners
        countButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);

        //get SharedPreferences object
        savedCount = getSharedPreferences("SavedCount", MODE_PRIVATE);
    }

    public void display(){
        //set the count text to whatever value count is
        countTextView.setText(Integer.toString(count));
    }

    @Override
    public void onPause(){
        //save the instance variables
        Editor editor = savedCount.edit();
        editor.putInt("count", count);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();

        //get and set the count
        count = savedCount.getInt("count", count);
        display();
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            //add 1 to count
            case R.id.countButton:
                count++;
                display();
                break;
            //reset count
            case R.id.resetButton:
                count = 0;
                display();
                break;
        }
    }
}
