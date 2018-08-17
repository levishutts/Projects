package com.example.lcs.pig_v3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lcs on 7/11/2017.
 */
public class SecondActivity extends AppCompatActivity {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_activity);

        game = new Game();

        // Pass the fragment a game ref while calling the method that invokes game play
        SecondFragment secondFragment = (SecondFragment)getFragmentManager()
                .findFragmentById(R.id.second_fragment);
        secondFragment.playGame(game);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Get the game state sent from the FirstActivity
        Intent intent = getIntent();
        String player1Name = intent.getExtras().getString("player1Name");
        String player2Name = intent.getExtras().getString("player2Name");
        if (game == null)   // We might already have a game object
            game = new Game();
        game.newGame();
        game.setPlayerName(player1Name, 1);
        game.setPlayerName(player2Name, 2);

        // Pass the fragment a game ref while calling the method invokes game play
        SecondFragment secondFragment = (SecondFragment) getFragmentManager()
                .findFragmentById(R.id.second_fragment);
        secondFragment.playGame(game);
    }
}