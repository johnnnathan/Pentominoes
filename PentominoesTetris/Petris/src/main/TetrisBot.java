package main;

import Pentominoes.Block;
import Pentominoes.Pentomino;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static main.GamePanel.getStaticBlocks;

public class TetrisBot {
    public static int score = 0;
    public static int bestXPosition = 0;
    public static int bestRotation = 0;

    private static Pentomino currPentomino;
    private static int[][] pentomino = new int[5][5];


    private static final int leftX = GamePanel.left_x;
    private static final int rightX = GamePanel.right_x;
    private static final int topY = GamePanel.top_y;
    private static final int botY = GamePanel.bottom_y;
    static ArrayList<Block> staticBlocks = new ArrayList<>();

    private static int[][] board = new int[15][5];

    public static void playGame() {
        long lastUpdateTime = System.currentTimeMillis();
        long updateTimeInterval = 100; // Set your desired update interval in milliseconds

        while (!GamePanel.gameOver) {
            long currentTime = System.currentTimeMillis();
            updateGameState();
            if (currentTime - lastUpdateTime >= updateTimeInterval) {
                // Update the game state and make decisions here
                getCurrentPentomino();
                makeDecision();
                executeMove();

                // Update the last update time
                lastUpdateTime = currentTime;
            }
        }
    }
    public static void makeDecision() {
        bestRotation=0;
        bestXPosition=0;

        int bestScore = Integer.MIN_VALUE; // Initialize with the lowest possible score
        for (int rotation = 0; rotation < 4; rotation++) {
            for (int x = 0; x < 5; x++) {
                for(int y=0 ; y<15 ; y++) {
                    // Simulate the move
                    simulateMove(x, y, rotation);
                    updateGameState();// Update the best move if this one is better
                    if (score > bestScore) {
                        bestScore = score;
                        bestRotation = rotation;
                        bestXPosition = x;
                    }
                }
            }
            // Rotate the piece for the next iteration
            rotatePiece();
        }

    }
    private static int calculateScore() {
        score = 0;

        // Add points for line clearance
        int linesCleared = countLinesCleared();
        score += linesCleared * 100; // Example scoring, adjust as needed

        // Penalize for height
        int maxHeight = findMaxHeight();
        score -= maxHeight * 10; // Adjust the multiplier as needed

        // Penalize for holes
        int holes = countHoles();
        score -= holes * 50; // Adjust the multiplier as needed

        // Penalize for bumpiness
        int bumpiness = calculateBumpiness();
        score -= bumpiness * 5; // Adjust the multiplier as needed

        return score;
    }
    private static int countLinesCleared() {
        int cleared = 0;
        for (int y = 0; y < board.length; y++) {
            boolean rowFull = true;
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == 0) {
                    rowFull = false;
                    break;
                }
            }
            if (rowFull) cleared++;
        }
        return cleared;
    }
    private static int findMaxHeight() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == 1) {
                    return board.length - y; // Return the height from bottom to the top filled cell
                }
            }
        }
        return 0; // If no blocks are found
    }
    private static int countHoles() {
        int holes = 0;
        for (int x = 0; x < board[0].length; x++) {
            boolean blockFound = false;
            for (int y = 0; y < board.length; y++) {
                if (board[y][x] == 1) blockFound = true;
                else if (board[y][x] == 0 && blockFound) holes++;
            }
        }
        return holes;
    }
    private static int calculateBumpiness() {
        int bumpiness = 0;
        int lastHeight = -1;
        for (int x = 0; x < board[0].length; x++) {
            int columnHeight = 0;
            for (int y = 0; y < board.length; y++) {
                if (board[y][x] == 1) {
                    columnHeight = board.length - y;
                    break;
                }
            }
            if (lastHeight != -1) {
                bumpiness += Math.abs(columnHeight - lastHeight);
            }
            lastHeight = columnHeight;
        }
        return bumpiness;
    }


    public static void simulateMove(int x, int y, int rotation) {
        boolean validMove = true;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (pentomino[i][j] == 1 && i + x < 5 && j + y <15) {
                    if (board[j + y][i+x] == 1) {
                        validMove = false;
                    }
                }
            }
            if (validMove) {
                calculateScore();
            }
        }
    }

    public static void rotatePiece() {
        for (int i = 0; i < 5 / 2; i++) {
            for (int j = i; j < 5 - i - 1; j++) {

                // Swap elements of each cycle
                // in clockwise direction
                int temp = pentomino[i][j];
                pentomino[i][j] = pentomino[5 - 1 - j][i];
                pentomino[5 - 1 - j][i] = pentomino[5 - 1 - i][5 - 1 - j];
                pentomino[5 - 1 - i][5 - 1 - j] = pentomino[j][5 - 1 - i];
                pentomino[j][5 - 1 - i] = temp;
            }
        }
    }

    public static void updateGameState() {
        board = new int[15][5];
        staticBlocks = getStaticBlocks();
        for (Block staticBlock : staticBlocks) {
            board[(staticBlock.y - topY) / Block.SIZE][(staticBlock.x - leftX) / Block.SIZE] = 1;
        }
    }
    public static void getCurrentPentomino(){
        pentomino= new int[5][5];
        currPentomino = GamePanel.getCurrPento();
        for(int i = 0; i < 5; i++){
            pentomino[(currPentomino.b[i].y-topY)/Block.SIZE][(currPentomino.b[i].x-leftX)/Block.SIZE] = 1;
        }
    }

    public static void executeMove() {
        // Rotate to the best rotation
        while (currPentomino.rotation != bestRotation) {
            rotatePiece();
            updateGameState(); // Update the game state after rotation
        }

        // Move to the best X position
        while (currPentomino.b[0].x != bestXPosition) {
            if (currPentomino.b[0].x < bestXPosition) {
                // Move right
                KeyHandler.rightPressed = true;
            } else {
                // Move left
                KeyHandler.leftPressed = true;
            }
            updateGameState(); // Update the game state after movement
        }

        // Optionally, you can add code to make the Tetris piece fall faster if needed
        // (e.g., by pressing the down key).
    }



}
