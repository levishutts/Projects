package com.example.lcs.pig_v3;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by lcs on 7/11/2017.
 */

public class FirstFragment extends Fragment implements OnClickListener {

    private Game game;
    private boolean twoPaneLayout;
    private EditText player2EditText;
    private EditText player1EditText;
    private FirstActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.first_fragment, container, false);

        // Set this fragment to listen for the Play button's click event
        Button b = (Button) view.findViewById(R.id.newGameButton);
        b.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get a references from the host activity
        activity = (FirstActivity)getActivity();
        player1EditText = (EditText) activity.findViewById(R.id.player1EditText);
        player2EditText = (EditText) activity.findViewById(R.id.player2EditText);

        game = new Game();

        // Give the host activity a reference to the game object
        activity.setGame(game);

        // Check to see if FirstActivity has loaded a single or dual pane layout
        twoPaneLayout = activity.findViewById(R.id.second_fragment) != null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.newGameButton) {
            if (twoPaneLayout) {
                game.setPlayerName(player1EditText.getText().toString(), 1);
                game.setPlayerName(player2EditText.getText().toString(), 2);
                activity.playGame(game);
            } else {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("player1Name", player1EditText.getText().toString());
                intent.putExtra("player2Name", player2EditText.getText().toString());
                startActivity(intent);
            }
        }
    }
}
