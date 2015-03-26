package com.printf.opia;

/**
 * Created by Joe on 3/10/2015.
 */
public class Logic {

    protected static int playerTurn;
    protected static int numColors;
    protected static Grid gameGrid;
    protected static Queue gameQueue;
    private Piece lastPlacedPiece;
    private int lastPlacedRow;
    private int lastPlacedCol;
    protected static int winningPlayer;

    public Logic(){
        playerTurn = 0;
        numColors = 3;
        gameGrid = new Grid();
        gameQueue = new Queue();
        lastPlacedPiece = null;
        lastPlacedRow = -1;
        lastPlacedCol = -1;
        winningPlayer = -1;
    }

    public int getPlayerTurn(){
        return playerTurn;
    }

    public void endTurn() { playerTurn = 1 - playerTurn; }

    public int checkColumn(int index){
        for(int rowIndex = 0; rowIndex < gameGrid.height; rowIndex++){
            if(gameGrid.getPiece(rowIndex, index) == null){
                Piece lastPlacedPiece = gameQueue.usePiece();
                lastPlacedRow = rowIndex;
                lastPlacedCol = index;
                gameGrid.setPiece(lastPlacedPiece, rowIndex, index);
                endTurn();
                return rowIndex;
            }
        }
        return -1;
    }

    // Returns true if the last player has just won the game
    public void checkWin(Piece piece, int row, int column){

    }

    private boolean checkDown(Piece piece, int row, int column){
        return false;
    }

    private boolean checkDownLeft(Piece piece, int row, int column){
        return false;
    }

    private boolean checkDownRight(Piece piece, int row, int column){
        return false;
    }

    private boolean checkLeft(Piece piece, int row, int column){
        return false;
    }

    private boolean checkRight(Piece piece, int row, int column){
        return false;
    }

    private boolean checkUp(Piece piece, int row, int column){
        return false;
    }

    private boolean checkUpLeft(Piece piece, int row, int column){
        return false;
    }

    private boolean checkUpRight(Piece piece, int row, int column){
        return false;
    }
}
