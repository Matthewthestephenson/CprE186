package com.printf.opia;

/**
 * Created by Joe on 3/10/2015.
 */
public class Logic {

    protected static int playerTurn;
    protected static int numColors;

    public Logic(){
        playerTurn = 0;
        numColors = 2;
    }

    public int getPlayerTurn(){
        return playerTurn;
    }

}
