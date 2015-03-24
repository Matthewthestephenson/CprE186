package com.printf.opia;

/**
 * Created by Joe on 3/10/2015.
 */
public class Logic {

    protected static int playerTurn;
    protected static int numColors;
    private Grid gameGrid;

    public Logic(){
        playerTurn = 0;
        numColors = 2;
        gameGrid = new Grid();
    }

    public int getPlayerTurn(){
        return playerTurn;
    }

    public void endTurn() { playerTurn = 1 - playerTurn; }

    public int checkColumn(int index){
        int colIndex = 0;

        return 0;
    }



}
