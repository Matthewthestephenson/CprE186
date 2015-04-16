package com.printf.opia.android;


import java.util.Random;

/**
 * Created by Joe on 3/10/2015.
 */
public class Piece {

    protected static enum Color{RED, BLUE, YELLOW, GREEN};
    protected static enum Shape{CIRCLE, SQUARE};
    protected Color pieceColor;
    protected Shape pieceShape;


    public Piece(){
        Random rand = new Random();
        int colorInt = rand.nextInt(Game.gameLogic.numColors) + 1;
        pieceColor = generateColor(colorInt);
    }

    public Piece(int playerTurn){
        Random rand = new Random();
        int colorInt = rand.nextInt(Game.gameLogic.numColors) + 1;
        pieceColor = generateColor(colorInt);
        if(playerTurn == 0){
            pieceShape = Shape.CIRCLE;
        }
        else if(playerTurn == 1){
            pieceShape = Shape.SQUARE;
        }
    }


    private Color generateColor(int colorInt){
        switch(colorInt){
            case 1:
                return Color.RED;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.YELLOW;
        }
        return null;
    }
}

