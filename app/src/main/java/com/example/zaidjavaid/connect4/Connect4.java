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

    public int whoWon()
    {
        int row = checkRows();
        if(row > 0)
        {
            return row;
        }
        int col = checkColumn();
        if(col > 0)
        {
            return col;
        }
        int diag = checkDiaganol();
        if(diag > 0)
        {
            return diag;
        }
        return 0;
    }

    protected int checkRows()
    {
        for(int row=0; row < Rows; row++)
        {
            int temp = 0;
            int col = 0;
            int player = game[col][row]; //gets the first spot of that row
            col++;
            while(temp < 4 || col<7)
            {
                if(game[row][col] == player)
                {
                    temp++;
                }
                else
                {
                    temp = 0;
                    game[row][col] = player;
                }
                col++;
            }
            if(temp == 4)
            {
                return game[row][col-1];
            }
        }
        return 0;
    }

    protected int checkColumn()
    {
        for(int col = 0; col < Columns; col++)
        {
            int temp = 0;
            int row = 0;
            int player = game[row][col];
            row++;
            while(temp < 4 || row<6)
            {
                if(game[row][col] == player)
                {
                    temp++;
                }
                else
                {
                    temp = 0;
                    player = game[row][col];
                }
                row++;
            }
            if(temp == 4)
            {
                return game[row-1][col];
            }
        }
        return 0;
    }

    protected int checkDiaganol()
    {
        int rowT;
        int colT;
        int temp = 4;
        int endpoint;
        int player;

        for(int row=0; row<3; row++) //this loop handles all diaganol wins from top left to bottom right
        {
            endpoint = 6;

            if(row == 1)
            {
                endpoint = 5;
            }

            rowT = row;
            for (int col = 0; col < 4; col++, endpoint--)
            {
                colT = col;
                player = game[row][col];
                while (temp < 4 || endpoint != 0)
                {
                    if (game[row++][col++] == player) {
                        if(game[row][col] ==)
                        temp++;
                    } else {
                        temp = 0;
                        player = game[row][col];
                    }
                }
                if(temp == 4)
                {
                    return game[row][col];
                }
                col = colT;
                row = rowT;
            }
        }

        for(int row=5; row>3; row--) //this loop will handle all diaganols wins from bottom left to top right
        {
            endpoint = 6;

            if(row == 4)
            {
                endpoint = 5;
            }

            rowT = row;
            for(int col = 0; col <4; col++,endpoint--)
            {
                colT = col;
                player = game[row][col];
                while(temp <4 || endpoint!=0)
                {
                    if (game[row--][col++] == player)
                    {
                        temp++;
                    }
                    else
                    {
                        temp = 0;
                        player = game[row][col];
                    }
                }
                if(temp == 4)
                {
                    return game[row][col];
                }
                col = colT;
                row = rowT;
            }
        }
        return 0;
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
        return cannotPlay( ) || ( whoWon( ) > 0 );
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
