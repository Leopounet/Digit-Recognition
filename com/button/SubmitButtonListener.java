package com.button;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

import com.dataset.*;
import com.field.*;
import com.pane.*;
import com.window.*;
import com.dataset.*;

/**
 * Called when the submit button is pressed.
 **/
public class SubmitButtonListener extends ButtonListener
{
    /**
    * Processes the probabilities of the given image.
    * @param window The main window to use
    * @param contentPane The content pane to consider
    **/
    public static void processProbabilities(Image uploadedImage, MainWindow window, MainContentPane contentPane)
    {
        // Sets the content pane to use
        p_contentPane = contentPane;

        // Gets the data set to use
        DataSet p_dataSet = window.getDataSet();

        // If there is an image
        if(uploadedImage != null)
        {
            // Rescales the image to 28 * 28
            uploadedImage = uploadedImage.getScaledInstance(p_dataSet.getNbPixelRows(),
                                                                p_dataSet.getNbPixelColumns(),
                                                                Image.SCALE_DEFAULT);

            if(uploadedImage != null)
            {
                // Draw the diagram
                double prob[] = p_dataSet.computeProbabilities(uploadedImage);
                p_contentPane.getMField().drawDiagram(prob);
                int max = maxIndex(prob);

                printMessage("Success : Diagram has been computed!", Color.GREEN, 0, true);
                printMessage(String.format("The digit drawn is probably a %d (confidence = %d%%)", max,
                                                                                                   (int)(prob[max] * 100)),
                                                                                                   Color.GREEN,
                                                                                                   1,
                                                                                                   false);
            }
            else
            {
                printMessage("Error : Image couldn't be resize!", Color.RED, 0, true);
            }
        }
        else
        {
            printMessage("Error : No image loaded!", Color.RED, 0, true);
        }
    }

    /**
     * Returns the index of the maximum value of the array.
     * @param array The array to parse
     * @return The index of the maximum value of the array
     **/
    private static int maxIndex(double array[])
    {
        int maxIndex = 0;
        double max = array[0];
        for(int i = 0; i < array.length; i++)
        {
            if(array[i] > max)
            {
                max = array[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
