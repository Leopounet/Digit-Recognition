package com.field;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.image.BufferedImage;

public class DrawingField extends JPanel
{
    //
    private Dimension p_size = null;

    //
    private BufferedImage p_bfImage = null;

    //
    private int p_radius = 10;

    public DrawingField(int width, int height)
    {
        super();
        p_size = new Dimension(width, height);
        initImage();
    }

    public DrawingField(Dimension size)
    {
        super();
        p_size = size;
        initImage();
    }

    private void initImage()
    {
        this.setPreferredSize(p_size);
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseHandler());
        p_bfImage = new BufferedImage(p_size.width, p_size.height, BufferedImage.TYPE_INT_ARGB);
        int black = 0xFF000000;
        for(int x = 0; x < p_size.width; x++)
        {
            for(int y = 0; y < p_size.height; y++)
            {
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
        g.drawImage(p_bfImage, 0, 0, this);
    }

    private void getCircle(Position pos[], int centerX, int centerY)
    {
        int index = 0;
        for(int x = centerX - p_radius; x < centerX + p_radius; x++)
        {
            for(int y = centerY - p_radius; y < centerY + p_radius; y++)
            {
                if(x < 0 || y < 0 || x >= p_size.width || y >= p_size.height)
                {
                    continue;
                }
                if(distance(x, y, centerX, centerY) < p_radius)
                {
                    System.out.println(x + " " + y);
                    pos[index] = new Position(x, y);
                    index++;
                }
            }
        }
    }

    private int distance(int x, int y, int centerX, int centerY)
    {
        return (int) Math.sqrt(Math.pow((double)(x - centerX), 2) + Math.pow((double)(y - centerY), 2));
    }

    private class MouseHandler extends MouseInputAdapter
    {
        public MouseHandler()
        {
            super();
        }

        public void mousePressed(MouseEvent e)
        {
            Position  p_position[] = new Position[10000];
            getCircle(p_position, e.getX(), e.getY());
            for(Position p : p_position)
            {
                if(p == null)
                {
                    break;
                }
                p_bfImage.setRGB(p.x, p.y, 0xFFFFFFFF);
            }
            repaint();
        }

        public void mouseDragged(MouseEvent e)
        {
            Position p_position[] = new Position[10000];
            getCircle(p_position, e.getX(), e.getY());
            for(Position p : p_position)
            {
                if(p == null)
                {
                    break;
                }
                p_bfImage.setRGB(p.x, p.y, 0xFFFFFFFF);
            }
            repaint();
        }
    }

    private class Position
    {
        public int x;
        public int y;
        public Position(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
