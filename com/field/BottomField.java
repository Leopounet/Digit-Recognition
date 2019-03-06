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
    // The message to print
    private String p_message = null;

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
    }

    /**
     * Prints the given string.
     * @param message The string to print
     **/
    public void printMessage(String message, Color c)
    {
        p_message = new String(message);
        p_color = c;
        p_draw = true;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        // Super of the paintComponent constructor
        super.paintComponent(g);

        // If true, draw the message
        if(p_draw == true)
        {
            g.setFont(new Font("default", Font.BOLD, 14));
            g.setColor(p_color);
            // Prints the message in black
            g.drawString(p_message, 5, p_fieldSize.height / 2);
        }
    }
}
