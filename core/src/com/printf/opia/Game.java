package com.printf.opia;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class Game extends ApplicationAdapter implements ApplicationListener, InputProcessor {
    private SpriteBatch batch;
    private Camera gameCamera;
    private Sprite currentPiece;
    private Sprite background;
    private Texture redSquare;
    private Texture blueSquare;
    private Texture redCircle;
    private Texture blueCircle;
    private Texture backgroundTexture;
    private int pieceDimensions;
    private static final int GAME_HEIGHT = 1280;
    private static final int GAME_WIDTH = 800;
    protected static Logic gameLogic;
    protected static Grid gameGrid;
    protected static Queue gameQueue;

    @Override
    public void create() {
        batch = new SpriteBatch();
        gameCamera = new OrthographicCamera(GAME_WIDTH, GAME_HEIGHT);
        gameCamera.translate(gameCamera.viewportWidth / 2, gameCamera.viewportHeight / 2, 0);
        backgroundTexture = new Texture(Gdx.files.internal("shittyMSPaintPng/background.png"));
        redSquare = new Texture(Gdx.files.internal("shittyMSPaintPng/redSquare.png"));
        blueSquare = new Texture(Gdx.files.internal("shittyMSPaintPng/blueSquare.png"));
        redCircle = new Texture(Gdx.files.internal("shittyMSPaintPng/redCircle.png"));
        blueCircle = new Texture(Gdx.files.internal("shittyMSPaintPng/blueCircle.png"));

        background = new Sprite(backgroundTexture);
        background.setSize(GAME_WIDTH, GAME_HEIGHT);
        pieceDimensions = GAME_WIDTH / 7;
        currentPiece = new Sprite();
        currentPiece.setSize(pieceDimensions, pieceDimensions);

        gameLogic = new Logic();
        gameQueue = new Queue();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        int i = 0;
        int j = 0;
        int counter = 0;
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameCamera.update();
        batch.begin();
        batch.setProjectionMatrix(gameCamera.combined);
        background.draw(batch);

        for (i = 0; i < 6; i++) {
            for (j = 0; j < 7; j++) {
                //TODO set currentPiece texture using getTexture method
                currentPiece = new Sprite(getTexture(gameLogic.gameGrid.getPiece(j, i)));
                currentPiece.setSize(pieceDimensions, pieceDimensions);
                currentPiece.setX((j * pieceDimensions));
                currentPiece.setY(i * pieceDimensions);
                currentPiece.draw(batch);
            }
        }
        for (i = 0; i < 5; i++){
            currentPiece = new Sprite(getTexture(gameLogic.gameQueue.peekPiece(i)));
            currentPiece.setSize(pieceDimensions, pieceDimensions);
            currentPiece.setX(i*pieceDimensions);
            currentPiece.setY(GAME_HEIGHT - pieceDimensions * 2);
            currentPiece.draw(batch);
        }

        batch.end();
    }

    public  Texture getTexture(Piece piece){
        //TODO how to get texture using enumerated types declared in piece class
       if(piece == null){
           return backgroundTexture;
       }
        switch(piece.pieceShape){
           case SQUARE:
                switch(piece.pieceColor){
                    case BLUE:
                        return blueSquare;

                    case RED:
                        return redSquare;

                }
                break;
           case CIRCLE:
               switch(piece.pieceColor){
                   case BLUE:
                       return blueCircle;

                   case RED:
                       return redCircle;

               }
               break;
            default:
                return backgroundTexture;

        }
        return backgroundTexture;
    }



    public void dispose(){
        redCircle.dispose();
        redSquare.dispose();
        blueCircle.dispose();
        blueSquare.dispose();
        backgroundTexture.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT){
            gameLogic.checkColumn(decideColumn(Gdx.input.getX()));
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int touchX = screenX;
        gameLogic.checkColumn(decideColumn(touchX));
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {

        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    public int decideColumn(int touchX){
        //Game width needs to be changed to grid width whatever that is.
        System.out.println("TouchX = " + touchX);
        if(touchX >= GAME_WIDTH * (6.0/7.0)){
            return 6;
        }
        else if(touchX >= GAME_WIDTH * (5.0/7.0)){
            return 5;
        }
        else if(touchX >= GAME_WIDTH * (4.0/7.0)){
            return 4;
        }
        else if(touchX >= GAME_WIDTH * (3.0/7.0)){
            return 3;
        }
        else if(touchX >= GAME_WIDTH * (2.0/7.0)){
            return 2;
        }
        else if(touchX >= GAME_WIDTH * (1.0/7.0)){
            return 1;
        }
        else {
            return 0;
        }
    }

}



