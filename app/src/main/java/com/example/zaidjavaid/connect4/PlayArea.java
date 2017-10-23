package com.example.zaidjavaid.connect4;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import static android.R.attr.width;

/**
 * Created by Zaid Javaid on 10/12/2017.
 */

public class PlayArea extends GridLayout
{
    private int columns;
    private int rows;
    private Button[][] buttons;
    private TextView status;

    public PlayArea(Context context, int width, int newrows, int newcolumns, OnClickListener listener)
    {
        super(context);
        rows = newrows;
        columns = newcolumns;

        //Set the number of rows and columns in the grid layout
        setColumnCount(columns);
        setRowCount(rows + 1);

        //Create the buttons and add them to the layout
        buttons = new Button[rows][columns];
        for(int row=0; row<rows; row++)
        {
            for(int col=0; col<columns; col++)
            {
                buttons[row][col] = new Button(context);
                buttons[row][col].setTextSize((int)(width * .2));
                buttons[row][col].setOnClickListener(listener);
                addView(buttons[row][col], width, width);
            }
        }

        //Set up the layout of the bottom row for state of the game
        status = new TextView(context);
        Spec rowsSpec = GridLayout.spec(rows, 1);
        Spec columnSpec = GridLayout.spec(0, columns);
        LayoutParams lpStatus = new LayoutParams(rowsSpec, columnSpec);
        status.setLayoutParams(lpStatus);

        //set up status bar
        status.setWidth(rows * width);
        status.setHeight(width);
        status.setGravity(Gravity.CENTER);
        status.setBackgroundColor(Color.GREEN);
        status.setTextSize((int)(width * .15));
        addView(status);
    }

    public void setStatusText(String text)
    {
        status.setText(text);
    }

    public void setBackgroundColor(int color)
    {
        status.setBackgroundColor(color);
    }

    public void setButtonText(int row, int column, String text)
    {
        buttons[row][column].setText(text);
    }

    public boolean isButton(Button b, int row, int column)
    {
        return (b == buttons[row][column]);
    }

    public void resetButtons()
    {
        for(int row=0; row<rows; row++)
        {
            for(int col=0; col<columns; col++)
            {
                buttons[row][col].setText("");
            }
        }
    }

    public void enableButtons(boolean enabled)
    {
        for(int row=0; row<rows; row++)
        {
            for(int col=0; col<columns; col++)
            {
                buttons[row][col].setEnabled(enabled);
            }
        }
    }


}
