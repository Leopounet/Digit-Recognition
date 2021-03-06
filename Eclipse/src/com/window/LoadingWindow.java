package com.window;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import com.field.*;

/**
 * The loading window that appears while the training data is being loaded.
 **/
public class LoadingWindow extends BasicWindow
{
	private static final long serialVersionUID = 1L;
	
    // The content pane of this window
    private JPanel p_contentPane = null;

    // The loading field to draw on
    private LoadingField p_loadingField = null;

    /**
     * Creates a new LoadingWindow.
     * @param width The width of the window
     * @param height The height of the window
     **/
    public LoadingWindow(int width, int height)
    {
        super(width, height);
        init();
    }

    /**
     * Creates a new LoadingWindow.
     * @param size The size of the window
     **/
    public LoadingWindow(Dimension size)
    {
        super(size);
        init();
    }

    /**
     * Initialized the field.
     **/
    private void init()
    {
        // Sets the title of the window
        this.setTitle("Loading, please wait...");

        // Creates the content pane
        p_contentPane = new JPanel();

        // Makes teh content pane the size of the window and adds it
        p_contentPane.setPreferredSize(p_windowSize);
        this.setContentPane(p_contentPane);

        // Creates the LoadingField and adds it to the content pane of the window
        p_loadingField = new LoadingField(p_windowSize);
        this.getContentPane().add(p_loadingField);

        // Makes the window visible
        this.setVisible(true);
    }

    /**
     * Closes the window.
     **/
    public void closeWindow()
    {
        // The program no longer exits if this window is closed
        LoadingWindow.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
