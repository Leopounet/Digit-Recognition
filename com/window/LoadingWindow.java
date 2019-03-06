package com.window;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

import com.field.*;

/**
 * The loading window that appears while the training data is being loaded.
 **/
public class LoadingWindow extends JFrame
{
    // The content pane of this window
    private JPanel p_contentPane = null;

    // The size of this window
    private Dimension p_windowSize = null;

    // The loading field to draw on
    private LoadingField p_loadingField = null;

    /**
     * Creates a new LoadingWindow.
     * @param width The width of the window
     * @param height The height of the window
     **/
    public LoadingWindow(int width, int height)
    {
        super();
        p_windowSize = new Dimension(width, height);
        init();
    }

    /**
     * Creates a new LoadingWindow.
     * @param size The size of the window
     **/
    public LoadingWindow(Dimension size)
    {
        super();
        p_windowSize = size;
        init();
    }

    /**
     * Initialized the field.
     **/
    private void init()
    {
        // Centers the window
        this.setLocationRelativeTo(null);

        // Sets the size of the window
        this.setSize(p_windowSize);

        // Makes the window full screen by default
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Makes the window visible
        this.setVisible(true);

        // Creates the content pane
        p_contentPane = new JPanel();

        // Makes teh content pane the size of the window and adds it
        p_contentPane.setPreferredSize(p_windowSize);
        this.setContentPane(p_contentPane);

        // Creates the LoadingField and adds it to the content pane of the window
        p_loadingField = new LoadingField(p_windowSize);
        this.getContentPane().add(p_loadingField);
    }

    /**
     * Closes the window.
     **/
    public void closeWindow()
    {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Updates the progress bar on the loading field.
     * @param progression The progression of the progress bar (0 < x < 1)
     **/
    public void setProgression(double progression)
    {
        p_loadingField.updateProgression(progression);
    }
}
