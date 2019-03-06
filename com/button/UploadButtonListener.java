package com.button;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

import com.dataset.*;
import com.field.*;

import com.window.*;

/**
 * Called when the upload button is pressed.
 **/
public class UploadButtonListener extends ButtonListener
{
    /**
     * Allows the user to choose a file.
     * @param window The main window to use
     * @param contentPane The content pane to consider
     * @return The selected File, null if no file selected
     **/
    public static File chooseFile(MainWindow window, ContentPane contentPane)
    {
        // Sets the window to use
        p_window = window;

        // Sets the content pane to use
        p_contentPane = contentPane;

        // Creates a new file chooser
        p_fc = new JFileChooser();

        // Opens the current directory
        p_fc.setCurrentDirectory(p_window.getCurrentDirectory());

        // Computes if the file is accessible
        int returnVal = p_fc.showOpenDialog(p_window);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            // Get the selected file
            File file = p_fc.getSelectedFile();

            if(file == null)
            {
                printMessage("Error : No file given!", Color.RED, 0, true);
                return null;
            }

            return file;
        }
        else
        {
            printMessage("Error : File is unreachable or access has been denied or no file has been chosen!", Color.RED, 0, true);
            return null;
        }
    }

    /**
     * Returns the image selected by the user.
     * @param window The main window to use
     * @param contentPane The content pane to consider
     * @param file The file to load
     * @return The selected image, null if the file is not an image
     **/
    public static Image getImage(MainWindow window, ContentPane contentPane, File file)
    {
        // Sets the window to use
        p_window = window;

        // Sets the content pane to use
        p_contentPane = contentPane;

        // Displays the image
        Image p_uploadedImage = p_contentPane.getTLField().getImage(file.getAbsolutePath());

        if(p_uploadedImage == null)
        {
            printMessage("Error : Image could not be loaded!", Color.RED, 0, true);
            return null;
        }

        printMessage("Success : Image successfully loaded!", Color.GREEN, 0, true);
        printMessage("Success : Press submit to find out which digit is maybe drawn...", Color.BLUE, 1, false);
        return p_uploadedImage;
    }

    /**
     * Displayes the current image.
     * @param window The main window to use
     * @param contentPane The content pane to consider
     * @param image The image to display
     **/
    public static void displayImage(MainWindow window, ContentPane contentPane, Image image)
    {
        // Sets the window to use
        p_window = window;

        // Sets the content pane to use
        p_contentPane = contentPane;

        p_contentPane.getTLField().displayImage(image);
    }
}
