package com.window;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

import com.field.*;
import com.button.*;

public class DrawingWindow extends JFrame
{
    //
    private Dimension p_windowSize = null;

    //
    private JPanel p_contentPane = null;

    public DrawingWindow(int width, int height)
    {
        super();
        p_windowSize = new Dimension(width, height);
        initWindow();
    }

    public DrawingWindow(Dimension size)
    {
        super();
        p_windowSize = size;
        initWindow();
    }

    private void initWindow()
    {
        // Centers the window
        this.setLocationRelativeTo(null);

        // Closing the window stops the program
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the size of the window
        this.setSize(p_windowSize);

        // Makes the window fullscreen by default
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Makes the window visible
        this.setVisible(true);

        //
        p_contentPane = new JPanel();
        p_contentPane.setPreferredSize(p_windowSize);
        p_contentPane.add(new DrawingField(p_windowSize));
        this.setContentPane(p_contentPane);
    }
}
