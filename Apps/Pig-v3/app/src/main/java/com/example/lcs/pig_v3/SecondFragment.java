package com.example.lcs.pig_v3;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lcs on 7/11/2017.
 */

public class SecondFragment extends Fragment implements OnClickListener{

    private TextView playerTurnLabel;
    private TextView player1Name;
    private TextView player2Name;
    private TextView player1Score;
    private TextView player2Score;
    private ImageView dieImageView;
    private TextView turnScoreTextView;
    private Button rollButton;
    private Button endTurnButton;
    private Game game;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // inflate the layout for this fragment
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        return view;
    }

    public void playGame(Game g) {
        Activity activity = getActivity();   // Get a reference to the host activity

        playerTurnLabel = (TextView) activity.findViewById(R.id.playerTurnLabel);
        player1Name = (TextView) activity.findViewById(R.id.player1Name);
        player2Name = (TextView) activity.findViewById(R.id.player2Name);
        player1Score = (TextView) activity.findViewById(R.id.player1Score);
        player2Score = (TextView) activity.findViewById(R.id.player2Score);
        turnScoreTextView = (TextView) activity.findViewById(R.id.turnScoreTextView);
        dieImageView = (ImageView) activity.findViewById(R.id.dieImageView);
        rollButton = (Button) activity.findViewById(R.id.rollButton);
        endTurnButton = (Button) activity.findViewById(R.id.endTurnButton);

        rollButton.setOnClickListener(this);
        endTurnButton.setOnClickListener(this);

        game = g;

        player1Name.setText(g.getPlayerName(1));
        player2Name.setText(g.getPlayerName(2));
    }

    public void updateInfo(){
        switch(game.getPlayer()){
            case 1:
                playerTurnLabel.setText(player1Name.getText());
                player2Score.setText(Integer.toString(game.getPlayerScore(2)));
                break;
            case 2:
                playerTurnLabel.setText(player2Name.getText());
                player1Score.setText(Integer.toString(game.getPlayerScore(1)));
                break;
        }
        switch (game.getDie()){
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
        turnScoreTextView.setText(Integer.toString(game.getTurnScore()));
    }

    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.rollButton:
                game.roll();
                break;
            case R.id.endTurnButton:
                game.endTurn();
                break;
        }
        if(game.getWinner() == 1){
            Toast.makeText(getActivity(), "Player 1 won!", Toast.LENGTH_LONG).show();
            game.setWinner(0);
        }
        else if(game.getWinner() == 2){
            Toast.makeText(getActivity(), "Player 2 won!", Toast.LENGTH_LONG).show();
            game.setWinner(0);
        }
        updateInfo();
    }
}
