package com.example.zaidjavaid.connect4;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity
{
    private Connect4 con;
    private Button [][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        con = new Connect4();
        buildGUI();
    }

    public void buildGUI()
    {
        // Get width of the screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / Connect4.Rows;
        int v = size.x / Connect4.Columns;

        // Create the layout manager as a GridLayout
        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(Connect4.Columns);
        gridLayout.setRowCount(Connect4.Rows);

        // Create the buttons and add them to gridLayout
        buttons = new Button[Connect4.Rows][Connect4.Columns];
        ButtonHandler bh = new ButtonHandler( );
        for(int row=0; row<Connect4.Rows; row++)
        {
            for(int col=0; col<Connect4.Columns; col++)
            {
                buttons[row][col] = new Button(this);
                buttons[row][col].setTextSize( ( int ) ( v * .2 ) );
                buttons[row][col].setOnClickListener( bh );
                gridLayout.addView(buttons[row][col],v,w);
            }
        }

        // Set gridLayout as the View of this Activity
        setContentView( gridLayout );
    }

    public void update(int col) //This does the actual visual setting of text in game board
    {
        int row = con.Test(col); //gets the value of row, checks column is in play area ansd if column is full
        if(row != -1)
        {
            int play = con.play(row, col);
            if (play == 1)
            {
                buttons[row][col].setText("X");
            }
            else if (play == 2)
            {
                buttons[row][col].setText("O");
            }
            if (con.isGameOver())
            {
              enableButtons(false);
            }
        }
    }

    public void enableButtons( boolean enabled )
    {
        for( int row = 0; row < Connect4.Rows; row++ )
        {
            for (int col = 0; col < Connect4.Columns; col++)
            {
                buttons[row][col].setEnabled(enabled);
            }
        }
    }

    private class ButtonHandler implements View.OnClickListener
    {
        public void onClick( View v )
        {
            for( int row = 0; row < Connect4.Rows; row++ )
            {
                for (int column = 0; column < Connect4.Columns; column++)
                {
                    if (v == buttons[row][column])
                    {
                        update(column);
                    }
                }
            }
        }
    }
}
