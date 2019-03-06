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

import com.image.*;

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

    /**
     * Tries to put the digit at the center of the image.
     * @param image The image to process
     **/
    private Image centerDigit(Image image)
    {
        // Get the width and height of the image
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        // Convert Image to BufferedImage
        BufferedImage oldImage = new BufferedImage(width,
                                                 height,
                                                 BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = oldImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        // Creates a new image in which the digit will be centered
        BufferedImage newImage = new BufferedImage(width,
                                                   height,
                                                   BufferedImage.TYPE_INT_ARGB);

        // Finds the middle of the digit
        int middle[] = findMiddles(width, height, oldImage);

        // Computes the sift to apply to every black pixel of the first image to
        // put the digit at the center of the new image
        int shiftX = width / 2 - middle[0];
        int shiftY = height / 2 - middle[1];

        // For every pixels in the old image
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                // If the new x and y are in the new image (not out of bounds)
                // set the shifted pixel to have the save color as the not
                // shifted pixel in the old image
                if(x - shiftX >= 0 && x - shiftX < width && y - shiftY >= 0 && y - shiftY < height)
                {
                    newImage.setRGB(x, y, oldImage.getRGB(x - shiftX, y - shiftY));
                }

                // Otherwise set the pixel black
                else
                {
                    newImage.setRGB(x, y, 0xFF000000);
                }
            }
        }
        return (Image) newImage;
    }

    /**
     * Finds the middle of the digit.
     * @param width The width of the images
     * @param height The height of the images
     * @param oldImage The not centered digit
     * @return An array of two integer, the first one being the X position of the
     * middle and the second one being the Y position of the middle
     **/
    private int[] findMiddles(int width, int height, BufferedImage oldImage)
    {
        // Stores the heighest up most and left most pixels that are black
        int firstX = width;
        int firstY = -1;

        // Stores the heighest down most and right most pixels that are black
        int lastX = 0;
        int lastY = 0;

        // For every pixel in the original image
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                // If the pixel is black
                if(oldImage.getRGB(x, y) != 0xFF000000)
                {
                    // If the up most pixel hasn't been found yet (the first one
                    // has to be the upmost because of the way the image is parsed)
                    if(firstY == -1)
                    {
                        firstY = y;
                    }

                    // The down most pixel has to be the last black pixel found
                    // because of the way the image is parsd
                    lastY = y;

                    // If the current pixel is to the left of the current left
                    // most pixel found
                    if(firstX > x)
                    {
                        firstX = x;
                    }

                    // If the current pixel is to the right of the current right
                    // most pixel found
                    if(lastX < x)
                    {
                        lastX = x;
                    }
                }
            }
        }

        // Computes the center of the rectangle found above, this should be about
        // the center of the digit as long as there are no random black pixels
        int middleX = (lastX - firstX) / 2 + firstX;
        int middleY = (lastY - firstY) / 2 + firstY;
        int middle[] = { middleX, middleY };
        return middle;
    }
}
