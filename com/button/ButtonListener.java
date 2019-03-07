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

/**
 * Generalized class of ButtonListeners, used to store general use methods.
 **/
public class ButtonListener
{
    // The File choosed to use
    protected static JFileChooser p_fc = null;

    // The window to use
    protected static MainWindow p_window = null;

    // The content pane to use (not a Container returned by getContentPane)
    protected static MainContentPane p_contentPane = null;

    /**
     * Sends a message to print to the bottom field.
     * @param message The message to print
     * @param c The color of the message to print
     * @param line The line to print the message at (starts at 0)
     * @param reset If true, deleted every previous messages
     **/
    protected static void printMessage(String message, Color c, int line, boolean reset)
    {
        // Removes every previous messages
        if(reset)
        {
            p_contentPane.getBField().resetMessages();
        }
        p_contentPane.getBField().printMessage(message, c, line);
    }

    /**
     * Sends a message to print to the bottom field.
     * @param contentPane The content pane to use
     * @param message The message to print
     * @param c The color of the message to print
     * @param line The line to print the message at (starts at 0)
     * @param reset If true, deleted every previous messages
     **/
    public static void printMessage(MainContentPane cp, String message, Color c, int line, boolean reset)
    {
        // Removes every previous messages
        if(reset)
        {
            cp.getBField().resetMessages();
        }
        cp.getBField().printMessage(message, c, line);
    }
}
