package com.field;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.awt.Graphics;

/**
 * Represents the loading field of the loading window.
 **/
public class LoadingField extends Field
{
    // Left most point of the loading rectangle
    private int p_loadingX = 0;

    // Up most point of the loading rectangle
    private int p_loadingY = 0;

    // Width of the loading rectangle
    private int p_loadingWidth = 0;

    // Height of the loading rectangle
    private int p_loadingHeight = 0;

    // Max width of the loading rectangle
    private int p_maxLoadingWidth = 0;

    // Progression of the loading bar (0 < x < 1)
    private double p_progression = 0;

    // True if the screen should be painted again
    private boolean p_draw = false;

    /**
     * Creates the middle field.
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public LoadingField(int width, int height)
    {
        super(width, height);
        init();
    }

    /**
     * Creates the middle field.
     * @param size The size of the newly created field
     **/
    public LoadingField(Dimension size)
    {
        super(size);
        init();
    }

    /**
     * Initialized the field.
     **/
    private void init()
    {
        // Initializes the field
        initField(Color.WHITE);

        // Sets the left most point of the loading bar (constant)
        p_loadingX = p_fieldSize.width / 50;

        // Sets the up most point of the loading bar (constant)
        p_loadingY = p_fieldSize.height / 10;

        // Sets the height of the loading bar (constant)
        p_loadingHeight = 8 * p_fieldSize.height / 10;

        // Sets the maximum width of the loading bar (constant)
        p_maxLoadingWidth = 48 * p_fieldSize.width / 50;

        // Init the current progression of the progress bar
        p_loadingWidth = 0;
    }

    /**
     * Updates the size of the progression bar.
     * @param progression The progression of the bar (between 0 and 1)
     **/
    public void updateProgression(double progression)
    {
        // Modifies the current progression
        p_progression = progression;

        // Stores the old progression to not updat to often (because of the
        // double -> int conversion, it might sometimes be useless)
        int oldProgression = p_loadingWidth;

        // Computes the new width of the progress bar as a fraction of its max width
        p_loadingWidth = (int)(p_progression * (double)(p_maxLoadingWidth));

        // If the size actually increased, draw the new bar
        if(oldProgression != p_loadingWidth)
        {
            p_draw = true;
            repaint();
        }
    }

    @Override
    /**
     * Paints every ractangles.
     * @param g The graphic component to use
     **/
    public void paintComponent (Graphics g)
    {
        // Super of the paintComponent constructor
        super.paintComponent(g);

        // If the size has been modified
        if(p_draw == true)
        {
            // Writes the action performed and the percentage of the loading
            g.setColor(Color.BLACK);
            g.setFont(new Font("default", Font.BOLD, 16));
            g.drawString(String.format("Loading training files, please wait... [%.2f%%]",
                                                 p_progression * 100),
                                                 p_fieldSize.width / 10,
                                                 19 * p_fieldSize.height / 20);

            // Set the loading bar to be green
            g.setColor(Color.GREEN);

            // Draws the loading bar
            g.fillRect(p_loadingX, p_loadingY, p_loadingWidth, p_loadingHeight);
            p_draw = false;
        }
    }
}
