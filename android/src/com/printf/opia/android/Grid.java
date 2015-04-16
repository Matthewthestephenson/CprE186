package com.printf.opia.android;

/**
 * Created by Joe on 3/10/2015.
 */
public class Grid {

    protected int width;
    protected int height;
    private Piece[][] gamePieces;

    public Grid(){
        width = 7;
        height = 6;
        gamePieces = new Piece[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                gamePieces[i][j] = null;
            }
        }
    }

    public Piece getPiece(int row, int column){
        return gamePieces[row][column];
    }

    public void setPiece(Piece piece, int row, int column){
        gamePieces[row][column] = piece;
    }

}
