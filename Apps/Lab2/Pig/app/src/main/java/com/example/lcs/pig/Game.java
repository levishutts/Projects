package com.example.lcs.pig;

/**
 * Created by lcs on 7/1/2017.
 */

import java.util.Random;

public class Game {

    public final static int WIN_SCORE = 100;

    public int player = 1;
    public int turnScore;
    public int player1Score;
    public int player2Score;
    public int die;
    public int winner;
    private Random rand = new Random();

    public void newGame(){
        player = 1;
        player1Score = 0;
        player2Score = 0;
        //winner = 0;
    }

    public void endGame(int playerWin){
        if(playerWin == 1){
            //player 2 has a chance not implemented
            winner = 1;
        }
        else{
            //player 2 wins
            winner = 2;
        }
        newGame();
    }

    public void endTurn(){
        if(player == 1){
            //add turn score to player 1's score
            player1Score += turnScore;
            //if score is winning total declare winner and end game
            if(player1Score >= WIN_SCORE){
                turnScore = 0;
                endGame(1);
            }
            turnScore = 0;
            player = 2;
        }else {
            player2Score += turnScore;
            if(player2Score >= WIN_SCORE){
                endGame(2);
            }
            turnScore = 0;
            player = 1;
        }
    }

    public void roll(){
        die = rand.nextInt(6) + 1;
        //if 1 reset turn score and endturn
        if(die == 1){
            turnScore = 0;
            endTurn();
        }
        else{
            //else increment turn score
            turnScore += die;
        }
    }
}
