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
        currentPiece = new Sprite(redSquare);
        currentPiece.setSize(pieceDimensions, pieceDimensions);

        gameLogic = new Logic();
        gameGrid = new Grid();
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
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            gameCamera.translate(-1f, 0f, 0f);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            gameCamera.translate(1f, 0f, 0f);
        }
        gameCamera.update();
        batch.begin();
        batch.setProjectionMatrix(gameCamera.combined);
        background.draw(batch);
        for (i = 0; i < 6; i++) {
            for (j = 0; j < 7; j++) {

                currentPiece = new Sprite(redCircle);
            }
            currentPiece.setSize(pieceDimensions, pieceDimensions);
            currentPiece.setX((j * pieceDimensions));
            currentPiece.setY(i * pieceDimensions);
            currentPiece.draw(batch);
            counter++;
        }

        batch.end();
    }

    private Texture getTexture(Piece piece){

        return null;
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
        return false;
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
}



