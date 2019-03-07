package com.window;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public class BasicWindow extends JFrame
{
    // Used to store the width and height of the window in pixels
    protected Dimension p_windowSize = null;

    public BasicWindow(int width, int height)
    {
        super();
        p_windowSize = new Dimension(width, height);
        initWindow();
    }

    public BasicWindow(Dimension size)
    {
        super();
        p_windowSize = size;
        initWindow();
    }

    /**
     * Sets the default parameters of the window and displays it. Also adds the
     * file chooser.
     **/
    protected void initWindow()
    {
        // Centers the window
        this.setLocationRelativeTo(null);

        // Closing the window stops the program
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the size of the window
        this.setSize(p_windowSize);

        // Makes the window fullscreen by default
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Makes the window visible
        this.setVisible(true);
    }

    /**
     * Returns the size of the window.
     * @return The size of the window as a Dimension object.
     **/
    public Dimension getWindowSize()
    {
        return p_windowSize;
    }

    /**
     * Sets the size of the window.
     * @param width Width in pixels of the created window
     * @param height Height in pixels of the created window
     **/
    public void setWindowSize(int width, int height)
    {
        p_windowSize = new Dimension(width, height);
    }

    /**
     * @param size The size of the window in pixels (width, height) format
     **/
    public void setWindowSize(Dimension size)
    {
        p_windowSize = size;
    }
}
