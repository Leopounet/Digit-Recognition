package com.field;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.util.Arrays;

/**
 * Represents the top left field of this window.
 **/
public class TopLeftField extends Field
{
    // If true, an image should be displayed (the button just got clicked)
    private boolean p_displayImage = false;

    // Image to display
    private Image p_image = null;

    /**
     * Creates the top left field.
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public TopLeftField(int width, int height)
    {
        super(width, height);
        initField(Color.WHITE);
    }

    /**
     * Creates the top left field.
     * @param size The size of the newly created field
     **/
    public TopLeftField(Dimension size)
    {
        super(size);
        initField(Color.WHITE);
    }

    /**
     * Overrides the paintComponent method.
     * @param g The graphic component to use to draw.
     **/
    public void paintComponent (Graphics g){

        // Super of the paintComponent constructor
        super.paintComponent(g);

        if(p_displayImage == true)
        {
            paintImage(g);
        }
    }

    /**
     * Paints the image to the canvas.
     * @param g The graphic component to use to draw.
     **/
    private void paintImage(Graphics g)
    {
        g.drawImage(p_image, 0, 0, this);
        p_displayImage = false;
        p_image = null;
    }

    /**
     * If possible, displays the image given as parameter.
     * @param path The path to the image.
     * @return The original image uploaded.
     **/
    public Image displayImage(String path)
    {
        // The original image
        Image tmp = null;
        try
        {
            // Loads the image if possible
            tmp = ImageIO.read(new File(path));

            tmp = centerDigit(tmp);

            // Resizes the image so that it fits the top left field
            p_image = tmp.getScaledInstance(p_fieldSize.width,
                                            p_fieldSize.height,
                                            Image.SCALE_DEFAULT);

            // p_image = centerDigit(p_image);
            // Set to true to display an image
            p_displayImage = true;
            repaint();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return tmp;
    }

    private Image centerDigit(Image image)
    {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        // Convert Image to BufferedImage
        BufferedImage bImage = new BufferedImage(width,
                                                 height,
                                                 BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = bImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        // Get byte array representing every pixels of the image
        int pixels[] = ((DataBufferInt)bImage.getRaster().getDataBuffer()).getData();

        BufferedImage newImage = new BufferedImage(width,
                                                   height,
                                                   BufferedImage.TYPE_INT_ARGB);

        int firstX = width;
        int firstY = -1;

        int lastX = 0;
        int lastY = 0;

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(bImage.getRGB(x, y) != 0xFF000000)
                {
                    if(firstY == -1)
                    {
                        firstY = y;
                    }
                    lastY = y;

                    if(firstX > x)
                    {
                        firstX = x;
                    }

                    if(lastX < x)
                    {
                        lastX = x;
                    }
                }
            }
        }

        int middleX = (lastX - firstX) / 2 + firstX;
        int middleY = (lastY - firstY) / 2 + firstY;

        int shiftX = width / 2 - middleX;
        int shiftY = height / 2 - middleY;

        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                if(x - shiftX >= 0 && x - shiftX < width && y - shiftY >= 0 && y - shiftY < height)
                {
                    newImage.setRGB(x, y, bImage.getRGB(x - shiftX, y - shiftY));
                }
                else
                {
                    newImage.setRGB(x, y, 0xFF000000);
                }
            }
        }

        return (Image) newImage;
    }
}
