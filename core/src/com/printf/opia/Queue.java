package com.printf.opia;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Joe on 3/10/2015.
 */
public class Queue {
    private int queueLength = 5;
    private ArrayList<Piece> queue;

    public Queue(){
        Random rand = new Random();
        queue = new ArrayList<Piece>();
        for(int i = 0; i < queueLength; i++){
            queue.add(new Piece((i%2) + Game.gameLogic.playerTurn));
        }
    }

    public Piece peekPiece(int index){
        return queue.get(index);
    }

    public Piece usePiece(){
        if(queue.size() < 1){
            throw new IllegalArgumentException();
        }
        Piece temp = queue.get(0);
        queue.remove(0);
        queue.add(new Piece((queueLength % 2) + ((Game.gameLogic.playerTurn + 1) % 2) - 1));
        return temp;
    }

    public int getQueueLength(){
        return queueLength;
    }
}
