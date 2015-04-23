package com.printf.opia;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.Map;


public class Game extends ApplicationAdapter implements ApplicationListener, InputProcessor {


    private SpriteBatch batch;
    private Camera gameCamera;
    private Sprite winner;
    private Sprite currentPiece;
    private Sprite background;
    private Texture redSquare;
    private Texture blueSquare;
    private Texture greenSquare;
    private Texture redCircle;
    private Texture blueCircle;
    private Texture greenCircle;
    private Texture backgroundTexture;
    private Texture winOne;
    private Texture winTwo;
    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private float pieceWidth;
    private float pieceHeight;
    private float queueX;
    private float queueY;
    private float gridWidth;
    private float gridHeight;
    private float gridWidthRatio;
    private float gridHeightRatio;
    private float gridY;
    private float gridX;
    private static float GAME_HEIGHT;
    private static float GAME_WIDTH;
    protected static Logic gameLogic;


    @Override
    public void create() {
        //Setup Game Numbers....
        GAME_WIDTH = Gdx.graphics.getWidth();
        GAME_HEIGHT = Gdx.graphics.getHeight();
        gridWidthRatio = (float) 69.0 / 80;
        gridHeightRatio = (float) 53.0 / 128;
        gridWidth = GAME_WIDTH * gridWidthRatio;
        gridHeight = GAME_HEIGHT * gridHeightRatio;
        pieceWidth = gridWidth / 7;
        pieceHeight = gridHeight / 6;
        gridY = (float) (GAME_HEIGHT * 5.0 / 16);//Game height * the y - placement ratio
        gridX = (float) (GAME_WIDTH * 1.1 / 16);//Game width * the the x - placement ratio
        queueX = gridX;
        queueY = (float) (GAME_HEIGHT * 119.0 / 160);
        //Setup SpriteBatch
        batch = new SpriteBatch();
        //Setup Camera
        gameCamera = new OrthographicCamera(GAME_WIDTH, GAME_HEIGHT);
        gameCamera.translate(gameCamera.viewportWidth / 2, gameCamera.viewportHeight / 2, 0);
        //Load Textures
        backgroundTexture = new Texture(Gdx.files.internal("demoImages/gamescreen.png"));
        winOne = new Texture(Gdx.files.internal("demoImages/player1Win.png"));
        winTwo = new Texture(Gdx.files.internal("demoImages/player2Win.png"));
        redSquare = new Texture(Gdx.files.internal("demoImages/squareRed.png"));
        blueSquare = new Texture(Gdx.files.internal("demoImages/squareBlue.png"));
        greenSquare = new Texture(Gdx.files.internal("demoImages/squareGreen.png"));
        redCircle = new Texture(Gdx.files.internal("demoImages/circleRed.png"));
        blueCircle = new Texture(Gdx.files.internal("demoImages/circleBlue.png"));
        greenCircle = new Texture(Gdx.files.internal("demoImages/circleGreen.png"));
        //FONT
        generator = new FreeTypeFontGenerator(Gdx.files.internal("demoImages/Myriad Italic.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 22;
        font = generator.generateFont(parameter);
        font.setColor(Color.valueOf("ef6e6e"));
        //Setup Sprites
        background = new Sprite(backgroundTexture);
        background.setSize(GAME_WIDTH, GAME_HEIGHT);
        currentPiece = new Sprite();
        winner = new Sprite();
        //init logic object
        gameLogic = new Logic();
        //Input
        Gdx.input.setInputProcessor(this);
        Gdx.graphics.setContinuousRendering(false);
    }

    @Override
    public void render() {
        //TODO add select animation
        int i = 0;
        int j = 0;
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameCamera.update();
        batch.begin();
        batch.setProjectionMatrix(gameCamera.combined);
        //draw Background
        background.draw(batch);
        //draw grid
        for (i = 0; i < 6; i++) {
            for (j = 0; j < 7; j++) {
                currentPiece = new Sprite(getTexture(gameLogic.gameGrid.getPiece(i, j)));
                if (currentPiece.getTexture() != backgroundTexture) {
                    currentPiece.setSize(pieceWidth, pieceHeight);
                    currentPiece.setX((gridX + j * pieceWidth));
                    currentPiece.setY(gridY + i * pieceHeight);
                    currentPiece.draw(batch);
                }
            }
        }
        //draw Queue
        for (i = 0; i < gameLogic.gameQueue.getQueueLength(); i++) {
            currentPiece = new Sprite(getTexture(gameLogic.gameQueue.peekPiece(i)));
            currentPiece.setSize(pieceWidth, pieceHeight);
            currentPiece.setX(i * pieceWidth * 3 + queueX);
            currentPiece.setY(queueY);
            currentPiece.draw(batch);
        }

        //Draw Score
        font.draw(batch,"" + gameLogic.player1score,(float)(GAME_WIDTH*(1.0/8.0)),(float)(GAME_HEIGHT*(49.0/320.0)));
        font.draw(batch,"" + gameLogic.player2score,(float)(GAME_WIDTH*(51.0/80.0)),(float)(GAME_HEIGHT*(49.0/320.0)));
        //if Someone Won
        if(gameLogic.winningPlayer != -1) {
            if (gameLogic.winningPlayer == 0) {
                winner = new Sprite(winOne);
                winner.setSize(GAME_WIDTH, GAME_HEIGHT / 2);
                winner.setY(GAME_HEIGHT / 4);
                winner.draw(batch);
            } else {
                winner = new Sprite(winTwo);
                winner.setSize(GAME_WIDTH, GAME_HEIGHT / 2);
                winner.setY(GAME_HEIGHT / 4);
                winner.draw(batch);
            }

        }

        batch.end();
    }

    public Texture getTexture(Piece piece) {
        if (piece == null) {
            return backgroundTexture;
        }
        switch (piece.pieceShape) {
            case SQUARE:
                switch (piece.pieceColor) {
                    case BLUE:
                        return blueSquare;
                    case RED:
                        return redSquare;
                    case GREEN:
                        return greenSquare;
                }
                break;
            case CIRCLE:
                switch (piece.pieceColor) {
                    case BLUE:
                        return blueCircle;
                    case RED:
                        return redCircle;
                    case GREEN:
                        return greenCircle;
                }
                break;
            default:
                return backgroundTexture;
        }
        return backgroundTexture;
    }


    public void dispose() {
        redCircle.dispose();
        blueCircle.dispose();
        greenCircle.dispose();
        redSquare.dispose();
        greenSquare.dispose();
        blueSquare.dispose();
        winOne.dispose();
        winTwo.dispose();
        backgroundTexture.dispose();
        generator.dispose();
        font.dispose();

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
        int touchX = screenX;
        int touchY = screenY;
        System.out.println(screenX + " " + screenY);
        if (decideColumn(touchX) != -1 && gameLogic.winningPlayer == -1) {
            gameLogic.checkColumn(decideColumn(screenX));
        }
        else if(gameLogic.winningPlayer != -1){
            gameLogic.nextRound();
        }
        if((touchX >= .58*GAME_WIDTH) &&(touchX <= .6*GAME_WIDTH) &&
                (touchY <= .05*GAME_HEIGHT) && (touchY >= .03*GAME_HEIGHT)){
            gameLogic.reset();

            }

        return true;
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

    public int decideColumn(int touchX) {
        int i = 0;
        if (touchX >= gridX && touchX <= (gridX + gridWidth)) {
            for(i = 6; i >= 0; i--){
                if(touchX >= (i*pieceWidth) + gridX){
                    return i;
                }
            }
        }
        return -1;
    }
    public int decideRow(int touchY){
        int i = 0;
        if(touchY >= gridY && touchY <= (gridY + gridHeight)){
            for(i = 6; i >= 0; i--){
                if(touchY >= (i*pieceWidth)+gridY){
                    return i;
                }
            }
        }
        return -1;
    }
}




