package com.printf.opia;

/**
 * Created by Joe on 3/10/2015.
 */
public class Logic {

    protected static int playerTurn;
    protected static int numColors;
    protected static Grid gameGrid;
    protected static Queue gameQueue;

    public Logic(){
        playerTurn = 0;
        numColors = 2;
        gameGrid = new Grid();
        gameQueue = new Queue();
    }

    public int getPlayerTurn(){
        return playerTurn;
    }

    public void endTurn() { playerTurn = 1 - playerTurn; }

    public int checkColumn(int index){
        for(int rowIndex = 0; rowIndex < gameGrid.height; rowIndex++){
            if(gameGrid.getPiece(rowIndex, index) == null){
                gameGrid.setPiece(gameQueue.usePiece(), rowIndex, index);
                endTurn();
                return rowIndex;
            }
        }
        return -1;
    }



}
