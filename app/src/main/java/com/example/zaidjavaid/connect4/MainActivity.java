package com.example.zaidjavaid.connect4;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private Connect4 con;
    private Button [][] buttons;
    private TextView status;

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
        gridLayout.setRowCount(Connect4.Rows + 1);

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


        //set up layout parameters of 7th row of layout for status bar
        status = new TextView(this);
        GridLayout.Spec rowSpec = GridLayout.spec(Connect4.Rows, 1);
        GridLayout.Spec columnSpec = GridLayout.spec(0, Connect4.Columns);
        GridLayout.LayoutParams lpStatus = new GridLayout.LayoutParams(rowSpec, columnSpec);
        status.setLayoutParams(lpStatus);


        //set status height and width
        status.setWidth(Connect4.Columns * w);
        status.setHeight(v);
        status.setGravity(Gravity.CENTER);
        status.setBackgroundColor(Color.GREEN);
        status.setTextSize((int) (w*.15));
        status.setText(con.result());
        gridLayout.addView(status);



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
                status.setBackgroundColor(Color.RED);
                enableButtons(false);
                status.setText(con.result());
                showNewGameDialog();
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

    public void resetButtons()
    {
        for(int row=0; row < Connect4.Rows; row++)
        {
            for(int col=0; col < Connect4.Columns; col++)
            {
                buttons[row][col].setText("");
            }
        }
    }

    public void showNewGameDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("This is fun");
        alert.setMessage("Play Again?");
        PlayDialog playAgain = new PlayDialog();
        alert.setPositiveButton("YES", playAgain);
        alert.setNegativeButton("NO", playAgain);
        alert.show();
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

    private class PlayDialog implements DialogInterface.OnClickListener
    {
        public void onClick(DialogInterface dialog, int id)
        {
            if(id == -1)
            {
                con.resetGame();
                enableButtons(true);
                resetButtons();
                status.setBackgroundColor((Color.GREEN));
                status.setText(con.result());
            }
            else if(id == -2)
            {
                MainActivity.this.finish();
            }
        }
    }
}
