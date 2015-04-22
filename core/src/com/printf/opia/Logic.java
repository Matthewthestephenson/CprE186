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
    protected static int player1score;
    protected static int player2score;

    public Logic(){
        playerTurn = 0;
        player1score = 0;
        player2score = 0;
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
                checkWin();
                endTurn();
                return rowIndex;
            }
        }
        return -1;
    }

    public static boolean checkFull(){
        for(int colIndex = 0; colIndex < gameGrid.width; colIndex++){
            for(int rowIndex = 0; rowIndex < gameGrid.height; rowIndex++){
                if(gameGrid.getPiece(rowIndex, colIndex) == null){
                    return false;
                }
            }
        }
        return true;
    }

    public void reset(){
        playerTurn = 0;
        gameGrid = new Grid();
        gameQueue = new Queue();
        lastPlacedPiece = null;
        lastPlacedRow = -1;
        lastPlacedCol = -1;
        winningPlayer = -1;

        return;
    }

    public void resetToBeginning(){
        playerTurn = 0;
        player1score = 0;
        player2score = 0;
        numColors = 3;
        gameGrid = new Grid();
        gameQueue = new Queue();
        lastPlacedPiece = null;
        lastPlacedRow = -1;
        lastPlacedCol = -1;
        winningPlayer = -1;
    }

    // Returns true if the last player has just won the game
    public void checkWin(){
        for(int colIndex = 0; colIndex < gameGrid.width; colIndex++){
            for(int rowIndex = 0; rowIndex < gameGrid.height; rowIndex++) {
                Piece piece = gameGrid.getPiece(rowIndex, colIndex);
                if(piece != null){
                    if (checkDown(piece, rowIndex, colIndex)) {
                        winningPlayer = playerTurn;
                    } else if (checkDownLeft(piece, rowIndex, colIndex)) {
                        winningPlayer = playerTurn;
                    } else if (checkDownRight(piece, rowIndex, colIndex)) {
                        winningPlayer = playerTurn;
                    } else if (checkUp(piece, rowIndex, colIndex)) {
                        winningPlayer = playerTurn;
                    } else if (checkUpLeft(piece, rowIndex, colIndex)) {
                        winningPlayer = playerTurn;
                    } else if (checkUpRight(piece, rowIndex, colIndex)) {
                        winningPlayer = playerTurn;
                    } else if (checkLeft(piece, rowIndex, colIndex)) {
                        winningPlayer = playerTurn;
                    } else if (checkRight(piece, rowIndex, colIndex)) {
                        winningPlayer = playerTurn;
                    }
                }
            }
        }
    }

    public void nextRound(){
        if(winningPlayer == 0){ player1score++;}else{  player2score++;}
        playerTurn = 0;
        //numColors++;
        gameGrid = new Grid();
        lastPlacedPiece = null;
        lastPlacedRow = -1;
        lastPlacedCol = -1;
        winningPlayer = -1;
        gameQueue = new Queue();

        return;
    }

    private boolean checkDown(Piece piece, int row, int column){
        if((gameGrid.height - row) <= 3){
            // Check for shape first
            int shapeMatches = 0;
            int colorMatches = 0;
            int numChecked = 0;
            int checkedRow = row - 1;
            while(numChecked < 3){
                Piece checkedPiece = gameGrid.getPiece(checkedRow, column);
                if(checkedPiece == null){
                    break;
                }
                if(checkedPiece.pieceShape == piece.pieceShape){
                    shapeMatches++;
                }
                if(checkedPiece.pieceColor == piece.pieceColor){
                    colorMatches++;
                }
                numChecked++;
                checkedRow --;
            }
            if((shapeMatches == 3) || (colorMatches == 3)){
                return true;
            }
        }
        return false;
    }

    private boolean checkDownLeft(Piece piece, int row, int column){
        if((gameGrid.height - row) <= 3 && (gameGrid.width - column) <= 4){
            int shapeMatches = 0;
            int colorMatches = 0;
            int checkedRow = row - 1;
            int checkedCol = column - 1;
            int numChecked = 0;
            while(numChecked < 3){
                Piece checkedPiece = gameGrid.getPiece(checkedRow, checkedCol);
                if(checkedPiece == null){
                    break;
                }
                if(checkedPiece.pieceShape == piece.pieceShape){
                    shapeMatches++;
                }
                if(checkedPiece.pieceColor == piece.pieceColor){
                    colorMatches++;
                }
                numChecked++;
                checkedRow--;
                checkedCol--;
            }
            if((shapeMatches == 3) || (colorMatches == 3)){
                return true;
            }
        }
        return false;
    }

    private boolean checkDownRight(Piece piece, int row, int column){
        if((gameGrid.height - row) <= 3 && (gameGrid.width - column) >= 4){
            int shapeMatches = 0;
            int colorMatches = 0;
            int checkedRow = row - 1;
            int checkedCol = column + 1;
            int numChecked = 0;
            while(numChecked < 3){
                Piece checkedPiece = gameGrid.getPiece(checkedRow, checkedCol);
                if(checkedPiece == null){
                    break;
                }
                if(checkedPiece.pieceShape == piece.pieceShape){
                    shapeMatches++;
                }
                if(checkedPiece.pieceColor == piece.pieceColor){
                    colorMatches++;
                }
                numChecked++;
                checkedRow--;
                checkedCol++;
            }
            if((shapeMatches == 3) || (colorMatches == 3)){
                return true;
            }
        }
        return false;
    }

    private boolean checkLeft(Piece piece, int row, int column){
        if((gameGrid.width - column) <= 4){
            int shapeMatches = 0;
            int colorMatches = 0;
            int checkedCol = column - 1;
            int numChecked = 0;
            while(numChecked < 3){
                Piece checkedPiece = gameGrid.getPiece(row, checkedCol);
                if(checkedPiece == null){
                    break;
                }
                if(checkedPiece.pieceShape == piece.pieceShape){
                    shapeMatches++;
                }
                if(checkedPiece.pieceColor == piece.pieceColor){
                    colorMatches++;
                }
                numChecked++;
                checkedCol--;
            }
            if((shapeMatches == 3) || (colorMatches == 3)){
                return true;
            }
        }
        return false;
    }

    private boolean checkRight(Piece piece, int row, int column){
        if((gameGrid.width - column) >= 4){
            int shapeMatches = 0;
            int colorMatches = 0;
            int checkedCol = column + 1;
            int numChecked = 0;
            while(numChecked < 3){
                Piece checkedPiece = gameGrid.getPiece(row, checkedCol);
                if(checkedPiece == null){
                    break;
                }
                if(checkedPiece.pieceShape == piece.pieceShape){
                    shapeMatches++;
                }
                if(checkedPiece.pieceColor == piece.pieceColor){
                    colorMatches++;
                }
                numChecked++;
                checkedCol++;
            }
            if((shapeMatches == 3) || (colorMatches == 3)){
                return true;
            }
        }
        return false;
    }

    private boolean checkUp(Piece piece, int row, int column){
        if((gameGrid.height - row) >= 4){
            // Check for shape first
            int shapeMatches = 0;
            int colorMatches = 0;
            int numChecked = 0;
            int checkedRow = row + 1;
            while(numChecked < 3){
                Piece checkedPiece = gameGrid.getPiece(checkedRow, column);
                if(checkedPiece == null){
                    break;
                }
                if(checkedPiece.pieceShape == piece.pieceShape){
                    shapeMatches++;
                }
                if(checkedPiece.pieceColor == piece.pieceColor){
                    colorMatches++;
                }
                numChecked++;
                checkedRow++;
            }
            if((shapeMatches == 3) || (colorMatches == 3)){
                return true;
            }
        }
        return false;
    }

    private boolean checkUpLeft(Piece piece, int row, int column){
        if((gameGrid.height - row) >= 4 && (gameGrid.width - column) <= 4){
            // Check for shape first
            int shapeMatches = 0;
            int colorMatches = 0;
            int numChecked = 0;
            int checkedRow = row + 1;
            int checkedCol = column - 1;
            while(numChecked < 3){
                Piece checkedPiece = gameGrid.getPiece(checkedRow, checkedCol);
                if(checkedPiece == null){
                    break;
                }
                if(checkedPiece.pieceShape == piece.pieceShape){
                    shapeMatches++;
                }
                if(checkedPiece.pieceColor == piece.pieceColor){
                    colorMatches++;
                }
                numChecked++;
                checkedRow++;
                checkedCol--;
            }
            if((shapeMatches == 3) || (colorMatches == 3)){
                return true;
            }
        }
        return false;
    }

    private boolean checkUpRight(Piece piece, int row, int column){
        if((gameGrid.height - row) >= 4 && (gameGrid.width - column) >= 4){
            // Check for shape first
            int shapeMatches = 0;
            int colorMatches = 0;
            int numChecked = 0;
            int checkedRow = row + 1;
            int checkedCol = column + 1;
            while(numChecked < 3){
                Piece checkedPiece = gameGrid.getPiece(checkedRow, checkedCol);
                if(checkedPiece == null){
                    break;
                }
                if(checkedPiece.pieceShape == piece.pieceShape){
                    shapeMatches++;
                }
                if(checkedPiece.pieceColor == piece.pieceColor){
                    colorMatches++;
                }
                numChecked++;
                checkedRow++;
                checkedCol++;
            }
            if((shapeMatches == 3) || (colorMatches == 3)){
                return true;
            }
        }
        return false;
    }
}
