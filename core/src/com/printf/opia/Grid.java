package com.printf.opia;

/**
 * Created by Joe on 3/10/2015.
 */
public class Grid {

    private int width;
    private int height;
    private Piece[][] gamePieces;

    public Grid(){
        width = 7;
        height = 6;
        gamePieces = new Piece[height][width];
    }

    public Piece getPiece(int row, int column){
        return gamePieces[row][column];
    }

    public void setPiece(Piece piece, int row, int column){
        gamePieces[row][column] = piece;
    }
}
