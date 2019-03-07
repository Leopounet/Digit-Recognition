package com.window;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

import com.field.*;
import com.button.*;
import com.pane.*;

/**
 * The window that appears when the user wants to draw.
 **/
public class DrawingWindow extends JFrame
{
    // The size of the window
    private Dimension p_windowSize = null;

    // The content pane of the window
    private DrawingContentPane p_contentPane = null;

    // The main window tha tcalled this window
    private MainWindow p_mainWindow = null;

    // The image that the user draws
    private Image p_image = null;

    /**
     * Creates a new DrawingWindow.
     * @param width The width of the window
     * @param height The height of the window
     **/
    public DrawingWindow(int width, int height)
    {
        super();
        p_windowSize = new Dimension(width, height);
        initWindow();
    }

    /**
     * Creates a new DrawingWindow.
     * @param size The size of the window
     **/
    public DrawingWindow(Dimension size)
    {
        super();
        p_windowSize = size;
        initWindow();
    }

    /**
     * Initializes the window.
     **/
    private void initWindow()
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

        // Creates the content pane
        p_contentPane = new DrawingContentPane(p_windowSize);

        // Adds the buttons listeners to the window
        p_contentPane.getOField().getFinishButton().addActionListener(new FinishButtonActionListener());
        p_contentPane.getOField().getIncreaseSizeButton().addActionListener(new IncreaseSizeButtonActionListener());
        p_contentPane.getOField().getDecreaseSizeButton().addActionListener(new DecreaseSizeButtonActionListener());
        p_contentPane.getOField().getSwitchColorButton().addActionListener(new SwitchColorButtonActionListener());

        // Adds the content pane the window
        this.setContentPane(p_contentPane);
    }

    /**
     * Action listener of the finish button.
     **/
    public class FinishButtonActionListener implements ActionListener
    {
        /**
         * Modifies the window when the upload button is clicked.
         * @param e The event information
         **/
        public void actionPerformed(ActionEvent e)
        {
            // The program no longer exits if this window is closed
            DrawingWindow.this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Sets the calling window to be visible again
            p_mainWindow.setVisible(true);

            // Sets the uploaded image of the main window to be the drawn image
            p_mainWindow.setImage(p_contentPane.getDField().getImage());

            // Closes this window
            DrawingWindow.this.dispatchEvent(new WindowEvent(DrawingWindow.this, WindowEvent.WINDOW_CLOSING));
        }
    }

    /**
     * Action listener of the increase size button.
     **/
    public class IncreaseSizeButtonActionListener implements ActionListener
    {
        /**
         * Modifies the window when the upload button is clicked.
         * @param e The event information
         **/
        public void actionPerformed(ActionEvent e)
        {
            p_contentPane.getDField().setRadius(1);
            p_contentPane.getOField().getIncreaseSizeButton().setText(String.format("Increase Size (%d)", p_contentPane.getDField().getRadius()));
            p_contentPane.getOField().getDecreaseSizeButton().setText(String.format("Decrease Size (%d)", p_contentPane.getDField().getRadius()));
        }
    }

    /**
     * Action listener of the decrease size button.
     **/
    public class DecreaseSizeButtonActionListener implements ActionListener
    {
        /**
         * Modifies the window when the upload button is clicked.
         * @param e The event information
         **/
        public void actionPerformed(ActionEvent e)
        {
            p_contentPane.getDField().setRadius(-1);
            p_contentPane.getOField().getIncreaseSizeButton().setText(String.format("Decrease Size (%d)", p_contentPane.getDField().getRadius()));
            p_contentPane.getOField().getDecreaseSizeButton().setText(String.format("Decrease Size (%d)", p_contentPane.getDField().getRadius()));
        }
    }

    /**
     * Action listener of the switch color button.
     **/
    public class SwitchColorButtonActionListener implements ActionListener
    {
        /**
         * Modifies the window when the upload button is clicked.
         * @param e The event information
         **/
        public void actionPerformed(ActionEvent e)
        {

        }
    }

    /**
     * Sets the callign window.
     * @param mw The calling window
     **/
    public void setMainWindow(MainWindow mw)
    {
        p_mainWindow = mw;
    }
}
