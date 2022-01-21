package eecs1022.lab8.tictactoe.model;

public class Game {

    private String playerX;
    private String playerO;
    private String currentPlayer;

    private int error = 0;
    private String statusMessage;

    private boolean gameStatus = false;
    private String winner = "";

    private char[][] board = { { '_', '_', '_' }, { '_', '_', '_' }, { '_', '_', '_' } };
    private int[][] occupied = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

    public Game(String pX, String pO) {
        this.playerX = pX;
        this.playerO = pO;

        this.currentPlayer = pX;
    }

    public void checkBoard() {
        char checkSign;
        String checkedPlayer = "";
        boolean allOccupied = true;

        if (this.currentPlayer == this.playerX) {
            checkSign = 'o';
            checkedPlayer = this.playerO;
        } else {
            checkSign = 'x';
            checkedPlayer = this.playerX;
        }

        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                if(this.occupied[i][j] == 0) {
                    allOccupied = false;
                }
            }
        }

        if ((this.board[0][0] == checkSign && this.board[0][1] == checkSign && this.board[0][2] == checkSign)
                || (this.board[0][0] == checkSign && this.board[1][0] == checkSign && this.board[2][0] == checkSign)
                || (this.board[0][0] == checkSign && this.board[1][1] == checkSign && this.board[2][2] == checkSign)
                || (this.board[1][0] == checkSign && this.board[1][1] == checkSign && this.board[1][2] == checkSign)
                || (this.board[2][0] == checkSign && this.board[2][1] == checkSign && this.board[2][2] == checkSign)
                || (this.board[2][0] == checkSign && this.board[1][1] == checkSign && this.board[0][2] == checkSign)) {

            this.statusMessage = "Game is over with " + checkedPlayer + " being the winner.";
            this.gameStatus = true;
            this.currentPlayer = null;
            this.winner = checkedPlayer;
        }else if(allOccupied == true) {
            this.statusMessage = "Game is over with a tie between " + this.playerX + " and " +  this.playerO + ".";
            this.currentPlayer = null;
            this.gameStatus = true;
        }
    }

    public void move(int row, int column) {
        if (gameStatus == true) {
            //this.error = 1;
        }else {
            if ((row <= 3 && row >= 1) && (column <= 3 && column >= 1)) {

                if (this.occupied[row - 1][column - 1] == 1) {
                    this.error = 1;
                    this.statusMessage = "Error: slot @ (" + row + ", " + column + ") is already occupied.";
                } else {
                    if (this.currentPlayer == this.playerX) {
                        this.board[row - 1][column - 1] = 'x';
                        this.currentPlayer = this.playerO;
                        this.occupied[row - 1][column - 1] = 1;
                    } else {
                        this.board[row - 1][column - 1] = 'o';
                        this.currentPlayer = this.playerX;
                        this.occupied[row - 1][column - 1] = 1;
                    }
                }
            } else if (row > 3 || row < 1) {
                this.statusMessage = "Error: row " + row + " is invalid.";
                this.error = 1;
            } else if (column > 3 || column < 1) {
                this.statusMessage = "Error: col " + column + " is invalid.";
                this.error = 1;
            }
        }

        checkBoard();

    }

    public String getCurrentPlayer() {
        return this.currentPlayer;
    }

    public String getStatus() {
        String status = "";

        if(this.gameStatus == true) {
            if(this.error == 1 && this.winner != "") {
                status = "Error: game is already over with a winner.";
            }else if(this.error == 1 && this.winner == "") {
                status = "Error: game is already over with a tie.";
            }else if(this.winner != "") {
                status = "Game is over with " + this.winner + " being the winner.";
                this.error = 1;
            }else {
                status = "Game is over with a tie between " + this.playerX + " and " +  this.playerO + ".";
                this.error = 1;
            }

        }else {
            if (this.error == 0) {
                status = this.currentPlayer + "'s turn to play...";
            } else {
                status = this.statusMessage;
                this.error = 0;
            }
        }


        return status;
    }

    public char[][] getBoard() {
        return this.board;
    }

    public void setWhoPlaysFirst(char firstPlayer) {
        if (firstPlayer == 'o') {
            this.currentPlayer = this.playerO;
        } else {
            this.currentPlayer = this.playerX;
        }

    }
}

