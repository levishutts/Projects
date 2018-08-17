package com.example.lcs.pig_v3;

/**
 * Created by lcs on 7/1/2017.
 */

import java.util.Random;

public class Game {

    private final static int WIN_SCORE = 100;

    private int player = 1;
    private int turnScore;
    private int player1Score;
    private int player2Score;
    private int die;
    private int winner;
    private int p1Win;
    private String player1Name;
    private String player2Name;
    private Random rand = new Random();

    public void setPlayerName(String playerName, int player){
        if(player == 1){
            player1Name = playerName;
        }else if(player == 2){
            player2Name = playerName;
        }
    }

    public String getPlayerName(int player){
        if(player == 1){
            return player1Name;
        }else if(player == 2){
            return player2Name;
        }else{
            return null;
        }
    }

    public int getPlayer(){
        return player;
    }

    public int getPlayerScore(int player){
        if(player == 1){
            return player1Score;
        }
        else{
            return player2Score;
        }
    }

    public int getDie(){
        return die;
    }

    public void setDie(int n){
        die = n;
    }

    public int getTurnScore(){
        return turnScore;
    }

    public int getWinner(){
        return winner;
    }

    public void setWinner(int player){
        winner = player;
    }

    public void newGame(){
        player = 1;
        player1Score = 0;
        player2Score = 0;
        p1Win = 0;
        turnScore = 0;
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
            turnScore = 0;
            player = 2;
        }else {
            player2Score += turnScore;
            turnScore = 0;
            player = 1;
        }
        //if score is winning total declare winner and end game
        if(player1Score >= WIN_SCORE){
            if(p1Win == 1) {
                if(player2Score < player1Score) {
                    endGame(1);
                }
            }
            p1Win = 1;
            turnScore = 0;
        }
        if(player2Score >= WIN_SCORE){
            //if p2 has a higher score than p1, they win
            endGame(2);
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
