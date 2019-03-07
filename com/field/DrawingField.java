package com.field;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.image.BufferedImage;

import com.image.*;

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

    //
    private int p_color = 0xFFFFFFFF;

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
        // Inits the field
        initField(Color.GRAY);

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
        p_bfImage = ImageProcessing.createBufferedImage(p_fieldSize, 0xFF000000);
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
     * Sets every pixels belonging to the circle of radius p_radius with center
     (x, y).
     * @param x The x coordinate of the center of the circle to draw
     * @param y The y coordinate of the center of the circle to draw
     **/
    private void setCircle(int x, int y)
    {
        ImageProcessing.setCircle(p_bfImage, p_fieldSize, x, y, p_radius, p_color);
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
     * Sets the color of the pencil.
     **/
    public void setColor(int c)
    {
        p_color = c;
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
}
