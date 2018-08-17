package com.example.lcs.pig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

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
    }

    public void newGame(){
        //reset players' scores and names
        pigGame.newGame();
        player1ScoreTextView.setText("0");
        player2ScoreTextView.setText("0");
        player1EditText.setText("Player 1 name");
        player2EditText.setText("Player 2 name");
    }

    public void updatePlayerTurnLabel(){
        switch(pigGame.player){
            case 1:
                playerTurnLabel.setText(player1EditText.getText());
                break;
            case 2:
                playerTurnLabel.setText(player2EditText.getText());
                break;
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.rollButton:
                pigGame.roll();
                switch (pigGame.die){
                    case 1:
                        dieImageView.setImageResource(R.drawable.die1);
                        updatePlayerTurnLabel();
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
                turnScoreTextView.setText(Integer.toString(pigGame.turnScore));
                break;
            case R.id.endTurnButton:
                pigGame.endTurn();
                player1ScoreTextView.setText(Integer.toString(pigGame.player1Score));
                player2ScoreTextView.setText(Integer.toString(pigGame.player2Score));
                if(pigGame.winner == 1){
                    Toast.makeText(getBaseContext(), "Player 1 won!", Toast.LENGTH_LONG).show();
                    newGame();
                    pigGame.winner = 0;
                }
                else if(pigGame.winner == 2){
                    Toast.makeText(getBaseContext(), "Player 2 won!", Toast.LENGTH_LONG).show();
                    newGame();
                    pigGame.winner = 0;
                }
                break;
            case R.id.newGameButton:
                Toast.makeText(getBaseContext(), "New game", Toast.LENGTH_LONG).show();
                newGame();
                break;
        }
        updatePlayerTurnLabel();
    }
}
