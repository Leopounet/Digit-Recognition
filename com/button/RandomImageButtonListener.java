package com.button;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.image.BufferedImage;

import com.dataset.*;
import com.field.*;
import com.pane.*;
import com.window.*;

public class RandomImageButtonListener extends ButtonListener
{
    /**
     * Returns a random image (chosen in the test images).
     * @param window The main window to use
     * @param contentPane The content pane to consider
     **/
    public static Image getRandomImage(MainWindow window, MainContentPane contentPane)
    {
        // Sets the window to use
        p_window = window;

        // Sets the content pane to use
        p_contentPane = contentPane;

        // Loads the test set
        DataSet test = p_window.getTestSet();

        // Choose a random image from the test set
        int randIndex = ThreadLocalRandom.current().nextInt(0, test.getNbImages());
        int pixels[] = test.getImage(randIndex);

        // Gets width and height of the image
        int width = test.getNbPixelRows();
        int height = test.getNbPixelColumns();

        // Creates a new buffered image to store the pixels in
        BufferedImage newImage = new BufferedImage(width,
                                                   height,
                                                   BufferedImage.TYPE_INT_ARGB);

        // For every pixel of the image, put it on the buffered image
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                newImage.setRGB(x, y, pixels[y * height + x]);
            }
        }

        printMessage("Success : Image successfully loaded!", Color.GREEN, 0, true);
        printMessage("Success : Press submit to find out which digit is maybe drawn...", Color.BLUE, 1, false);
        return (Image) newImage;
    }
}
