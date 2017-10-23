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
    private PlayArea area;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        con = new Connect4();

        // Get width of the screen
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        int w = size.x / Connect4.Columns;
        //int v = size.x / Connect4.Columns;

        ButtonHandler bh = new ButtonHandler( );
        area = new PlayArea(this, w, Connect4.Rows, Connect4.Columns, bh);
        area.setStatusText(con.result());
        // Set gridLayout as the View of this Activity
        setContentView(area);
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
                   if(area.isButton((Button)v, row, column))
                    {
                        int Row = con.Test(column); //gets the value of row, checks column is in play area ansd if column is full
                        if(row != -1)
                        {
                            int play = con.play(Row, column);
                            if (play == 1)
                            {
                                area.setButtonText(Row, column, "X");
                            }
                            else if (play == 2)
                            {
                                area.setButtonText(Row, column, "O");
                            }
                            if (con.isGameOver())
                            {
                                area.setBackgroundColor(Color.RED);
                                area.enableButtons(false);
                                area.setStatusText(con.result());
                                showNewGameDialog();
                            }
                        }
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
                area.enableButtons(true);
                area.resetButtons();
                area.setBackgroundColor((Color.GREEN));
                area.setStatusText(con.result());
            }
            else if(id == -2)
            {
                MainActivity.this.finish();
            }
        }
    }
}
