package com.example.lcs.pig;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText player1EditText;
    private EditText player2EditText;
    private TextView player1ScoreTextView;
    private TextView player2ScoreTextView;
    private TextView playerTurnLabel;
    private ImageView dieImageView;
    private TextView turnScoreTextView;
    private Button rollButton;
    private Button endTurnButton;
    private Button newGameButton;

    private String player1NameString = "Player 1 name";
    private String player1ScoreString = "0";
    private String playerTurnLabelString = "Player 1";
    private int dieImage;
    private String turnScoreString = "0";
    private SharedPreferences prefs;

    private Game pigGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references
        player1EditText = (EditText) findViewById(R.id.player1EditText);
        player2EditText = (EditText) findViewById(R.id.player2EditText);
        player1ScoreTextView = (TextView) findViewById(R.id.player1ScoreTextView);
        player2ScoreTextView = (TextView) findViewById(R.id.player2ScoreTextView);
        playerTurnLabel = (TextView) findViewById(R.id.playerTurnLabel);
        dieImageView = (ImageView) findViewById(R.id.dieImageView);
        turnScoreTextView = (TextView) findViewById(R.id.turnScoreTextView);
        rollButton = (Button) findViewById(R.id.rollButton);
        endTurnButton = (Button) findViewById(R.id.endTurnButton);
        newGameButton = (Button) findViewById(R.id.newGameButton);

        //Set listeners
        rollButton.setOnClickListener(this);
        endTurnButton.setOnClickListener(this);
        newGameButton.setOnClickListener(this);

        //create Game object
        pigGame = new Game();
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
    }


    @Override
    public void onPause(){
        Editor editor = prefs.edit();

        editor.putString("player1NameString", player1NameString);
        editor.putString("player1ScoreString", player1ScoreString);
        editor.putString("player1TurnLabelString", playerTurnLabelString);
        editor.putString("turnScoreString", turnScoreString);
        editor.putInt("dieImage", pigGame.getDie());
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();


        player1NameString = prefs.getString("player1NameString", "Player 1 Name");
        player1ScoreString = prefs.getString("player1ScoreString", "0");
        playerTurnLabelString = prefs.getString("playerTurnLabelString", "Player 1");
        turnScoreString = prefs.getString("turnScoreString", "0");
        dieImage = prefs.getInt("dieInt", 1);

        player1EditText.setText(player1NameString);
        player1ScoreTextView.setText(player1ScoreString);
        playerTurnLabel.setText(playerTurnLabelString);
        turnScoreTextView.setText(turnScoreString);
        pigGame.setDie(dieImage);
    }

    public void newGame(){
        //reset players' scores and names
        pigGame.newGame();
        player1ScoreTextView.setText("0");
        player2ScoreTextView.setText("0");
        player1EditText.setText("Player 1 name");
        player2EditText.setText("Player 2 name");
    }

    public void updateInfo(){
        switch(pigGame.getPlayer()){
            case 1:
                playerTurnLabel.setText(player1EditText.getText());
                player2ScoreTextView.setText(Integer.toString(pigGame.getPlayerScore(2)));
                break;
            case 2:
                playerTurnLabel.setText(player2EditText.getText());
                player1ScoreTextView.setText(Integer.toString(pigGame.getPlayerScore(1)));
                break;
        }
        switch (pigGame.getDie()){
            case 1:
                dieImageView.setImageResource(R.drawable.die1);
                break;
            case 2:
                dieImageView.setImageResource(R.drawable.die2);
                break;
            case 3:
                dieImageView.setImageResource(R.drawable.die3);
                break;
            case 4:
                dieImageView.setImageResource(R.drawable.die4);
                break;
            case 5:
                dieImageView.setImageResource(R.drawable.die5);
                break;
            case 6:
                dieImageView.setImageResource(R.drawable.die6);
                break;
        }
        turnScoreTextView.setText(Integer.toString(pigGame.getTurnScore()));
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rollButton:
                pigGame.roll();
                break;
            case R.id.endTurnButton:
                pigGame.endTurn();
                break;
            case R.id.newGameButton:
                Toast.makeText(getBaseContext(), "New game", Toast.LENGTH_LONG).show();
                newGame();
                break;
        }
        if(pigGame.getWinner() == 1){
            Toast.makeText(getBaseContext(), "Player 1 won!", Toast.LENGTH_LONG).show();
            newGame();
            pigGame.setWinner(0);
        }
        else if(pigGame.getWinner() == 2){
            Toast.makeText(getBaseContext(), "Player 2 won!", Toast.LENGTH_LONG).show();
            newGame();
            pigGame.setWinner(0);
        }
        updateInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;
            case R.id.menu_about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
