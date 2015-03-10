package com.printf.opia;


import java.util.Random;

/**
 * Created by Joe on 3/10/2015.
 */
public class Piece {

    private static enum Color{RED, BLUE, YELLOW, GREEN};
    private static enum Shape{CIRCLE, SQUARE};
    private Color pieceColor;
    private Shape pieceShape;

    public Piece(){
        Random rand = new Random();
        int colorInt = rand.nextInt(Game.gameLogic.numColors);
        pieceColor = generateColor(colorInt);
    }

    public Piece(int playerTurn){
        Random rand = new Random();
        int colorInt = rand.nextInt(Game.gameLogic.numColors);
        pieceColor = generateColor(colorInt);
    }


    private Color generateColor(int colorInt){
        switch(colorInt){
            case 1:
                return Color.RED;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.GREEN;
        }
        return null;
    }
}

