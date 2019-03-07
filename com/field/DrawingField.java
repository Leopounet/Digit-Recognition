package com.field;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.image.BufferedImage;

/**
 * The field the user draws on.
 **/
public class DrawingField extends Field
{
    // The image the user is drawing on
    private BufferedImage p_bfImage = null;

    // The radius of the pencil
    private int p_radius = 30;

    // The maximum and minimum sizes of the radius
    private int p_minRadius = 10;
    private int p_maxRadius = 50;

    // If true, color set to black
    private boolean p_erase = false;

    /**
     * Creates a new DrawingField.
     * @param width The width of the field
     * @param height The height of the field
     **/
    public DrawingField(int width, int height)
    {
        super(width, height);
        init();
    }

    /**
     * Creates a new DrawingField.
     * @param size The size of the field
     **/
    public DrawingField(Dimension size)
    {
        super(size);
        init();
    }

    /**
     * Initializes the field.
     **/
    private void init()
    {
        // Sets the size of the field
        this.setPreferredSize(p_fieldSize);

        // Adss mouse listeners to the field
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseHandler());

        // Init the image the user will draw on to all black
        initImage();
    }

    /**
     * Creates the image the user will draw on and fills it with squares.
     **/
    private void initImage()
    {
        // Creates the image
        p_bfImage = new BufferedImage(p_fieldSize.width, p_fieldSize.height, BufferedImage.TYPE_INT_ARGB);

        // Black pixel code
        int black = 0xFF000000;

        // For every pixels of the image
        for(int x = 0; x < p_fieldSize.width; x++)
        {
            for(int y = 0; y < p_fieldSize.height; y++)
            {
                // Set the pixel black
                p_bfImage.setRGB(x, y, black);
            }
        }
        repaint();
    }

    /**
     * Overrides the paintComponent method.
     * @param g The graphic component to use to draw.
     **/
    public void paintComponent (Graphics g)
    {

        // Super of the paintComponent constructor
        super.paintComponent(g);

        // Updates the image the user is drawing
        g.drawImage(p_bfImage, 0, 0, this);
    }

    /**
     * Fills the given array with the position of every pixels within the selected
     * radius.
     * @param pos The list of pixels to draw
     * @param centerX The center X of the circle to draw
     * @param centerY The center Y of the circle to draw
     **/
    private void getCircle(Position pos[], int centerX, int centerY)
    {
        int index = 0;
        // For every pixel within the square from (x - r, y - r) (x + r, y + r)
        for(int x = centerX - p_radius; x < centerX + p_radius; x++)
        {
            for(int y = centerY - p_radius; y < centerY + p_radius; y++)
            {
                // If the pixel is outside the field dimensions
                if(x < 0 || y < 0 || x >= p_fieldSize.width || y >= p_fieldSize.height)
                {
                    continue;
                }

                // If the pixel  is within the circle of radius p_radius, add it
                // to the list
                if(distance(x, y, centerX, centerY) < p_radius)
                {
                    pos[index] = new Position(x, y);
                    index++;
                }
            }
        }
    }

    /**
     * Returns the distance between two given point.
     * @param x The x coordinate of the first point
     * @param y The y coordinate of the first point
     * @param centerX The x coordinate of the second point
     * @param centerY The y coordinate of the second point
     **/
    private int distance(int x, int y, int centerX, int centerY)
    {
        // Pythagorian theorem
        return (int) Math.sqrt(Math.pow((double)(x - centerX), 2) + Math.pow((double)(y - centerY), 2));
    }

    /**
     * Sets every pixels belonging to the circle of radius p_radius with center
     (x, y).
     * @param x The x coordinate of the center of the circle to draw
     * @param y The y coordinate of the center of the circle to draw
     **/
    private void setCircle(int x, int y)
    {
        // Creates a new array of position
        Position  p_position[] = new Position[10000];

        // Fills the array with every pixel in the circle around the mouse
        getCircle(p_position, x, y);

        // For every pixel recorded
        for(Position p : p_position)
        {
            // If the pixel is null, the end of the list has been reached
            if(p == null)
            {
                break;
            }

            if(p_erase)
            {
                // Set the corresponding pixel of the image to black
                p_bfImage.setRGB(p.x, p.y, 0xFF000000);
            }
            else
            {
                // Set the corresponding pixel of the image to white
                p_bfImage.setRGB(p.x, p.y, 0xFFFFFFFF);
            }
        }
        repaint();
    }

    /**
     * Returns the drawn image.
     * @return The drawn image as a BufferedImage
     **/
    public BufferedImage getImage()
    {
        return p_bfImage;
    }

    /**
     * Modifies the size of the pencil.
     * @param delta The shift to apply
     **/
    public void setRadius(int delta)
    {
        if(p_radius + delta > p_minRadius && p_radius + delta < p_maxRadius)
        {
            p_radius += delta;
        }
    }

    /**
     * Returns the current radius.
     * @return p_radius
     **/
    public int getRadius()
    {
        return p_radius;
    }

    /**
     * Toggles the erase mode.
     **/
    public void toggleErase()
    {
        p_erase = !p_erase;
    }

    /**
     * Returns true if in erase mode, false otherwise
     * @return p_erase
     **/
    public boolean getErase()
    {
        return p_erase;
    }

    /**
     * Class to handle mouse interactions with the field.
     **/
    private class MouseHandler extends MouseInputAdapter
    {
        /**
         * Creates a new mouse handler.
         **/
        public MouseHandler()
        {
            super();
        }

        /**
         * Called if the mouse is pressed on this field.
         **/
        public void mousePressed(MouseEvent e)
        {
            setCircle(e.getX(), e.getY());
        }

        /**
         * Called if the mouse is dragged.
         **/
        public void mouseDragged(MouseEvent e)
        {
            setCircle(e.getX(), e.getY());
        }
    }

    /**
     * A class to handle point in space.
     **/
    private class Position
    {
        // X coord
        public int x;

        // Y coord
        public int y;

        // Creates a new point
        public Position(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
