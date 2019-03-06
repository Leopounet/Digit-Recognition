package com.field;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

/**
 * Represents the bottom field of this window.
 **/
public class BottomField extends Field
{
    // The messages to print
    private String p_messages[] = null;

    // If true, the bottom field is redrawn
    private boolean p_draw = false;

    // the color of the message
    private Color p_color = Color.BLACK;

    /**
     * Creates the bottom field.
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public BottomField(int width, int height)
    {
        super(width, height);
        init();
    }

    /**
     * Creates the bottom field.
     * @param size The size of the newly created field
     **/
    public BottomField(Dimension size)
    {
        super(size);
        init();
    }

    /**
     * Initializes the current object.
     **/
    private void init()
    {
        initField(Color.DARK_GRAY);
        p_messages = new String[2];
    }

    /**
     * Prints the given string.
     * @param message The string to print
     * @param c The color of the text
     * @param line The line the text should be displayed at (0, 1, 2, 3)
     **/
    public void printMessage(String message, Color c, int line)
    {
        p_messages[line] = new String(message);
        p_color = c;
        p_draw = true;
        repaint();
    }

    /**
     * Overrides the paintComponent method.
     * @param g The graphic component to use to draw.
     **/
    public void paintComponent(Graphics g)
    {
        // Super of the paintComponent constructor
        super.paintComponent(g);

        // If true, draw the message
        if(p_draw == true)
        {
            int line = 0;
            for(String s : p_messages)
            {
                if(s != null)
                {
                    g.setFont(new Font("default", Font.BOLD, 14));
                    g.setColor(p_color);

                    // Prints the message in black
                    g.drawString(s, 5, 20 * (line + 1));

                    line++;
                }
            }
        }
    }
}
