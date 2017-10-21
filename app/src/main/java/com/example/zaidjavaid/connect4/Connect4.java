package com.example.zaidjavaid.connect4;

/**
 * Created by Zaid Javaid on 10/12/2017.
 */

public class Connect4 {
    public static final int Rows = 6;   //number of rows (run left to right)
    public static final int Columns = 7;    //number if Columns (run up to down)
    private int turn;
    private int [][] game;

    public Connect4()
    {
        game = new int [Rows][Columns];
        resetGame();
    }

    public int play(int row, int col) //this just sets the play token to the spot in the array
    {
        int currentTurn = turn;
        game[row][col] = turn;
        if(turn == 1)
        {
            turn = 2;
        }
        else
        {
            turn = 1;
        }
        return currentTurn;
    }

    public int Test(int col)
    {
        if(col >= 0 && col<Columns) //if the column is in play area
        {
            if (Fill(col) == false) //if the column is full
            {
                int row = Gravity(col);
                return row;
            }
        }
        return -1;
    }

    public static int checkWin(int[][] board) {
        final int HEIGHT = board.length;
        final int WIDTH = board[0].length;
        final int EMPTY_SLOT = 0;
        for (int r = 0; r < HEIGHT; r++) { // iterate rows, bottom to top
            for (int c = 0; c < WIDTH; c++) { // iterate columns, left to right
                int player = board[r][c];
                if (player == EMPTY_SLOT)
                    continue; // don't check empty slots

                if (c + 3 < WIDTH &&
                        player == board[r][c+1] && // look right
                        player == board[r][c+2] &&
                        player == board[r][c+3])
                    return player;
                if (r + 3 < HEIGHT) {
                    if (player == board[r+1][c] && // look up
                            player == board[r+2][c] &&
                            player == board[r+3][c])
                        return player;
                    if (c + 3 < WIDTH &&
                            player == board[r+1][c+1] && // look up & right
                            player == board[r+2][c+2] &&
                            player == board[r+3][c+3])
                        return player;
                    if (c - 3 >= 0 &&
                            player == board[r+1][c-1] && // look up & left
                            player == board[r+2][c-2] &&
                            player == board[r+3][c-3])
                        return player;
                }
            }
        }
        return EMPTY_SLOT; // no winner found
    }

    public boolean Fill(int col) //checks if the column is filled
    {
        int row =0;
        while(row<=5)
        {
            if(game[row][col]!=0)
            {
                row=6;
            }
            row++;
        }

        if(row==0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public int Gravity(int col) //handles the gravity of the chips so they fall to the bottom
    {
        int row = 0;
        while(row<=5)
        {
            if(game[row][col] == 0)
            {
                row++;
            }
            else
            {
                break;
            }
        }
        return row-1;
    }

    public boolean cannotPlay( )
    {
        boolean result = true;
        for (int row = 0; row < Rows; row++)
        {
            for (int col = 0; col < Columns; col++)
            {
                if (game[row][col] == 0)
                {
                    result = false;
                }
            }
        }
        return result;
    }

    public boolean isGameOver( )
    {
        return cannotPlay( ) || (checkWin(game) > 0 );
    }

    public void resetGame()
    {
        for(int row=0; row<Rows; row++)
        {
            for(int col=0; col<Columns; col++)
            {
                game[row][col] = 0;
            }
        }
        turn = 1;
    }
}
