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
import com.window.*;

public class DrawButtonListener extends ButtonListener
{
    /**
     * Returns a random image (chosen in the test images).
     * @param window The main window to use
     * @param contentPane The content pane to consider
     **/
    public static Image drawImage(MainWindow window, ContentPane contentPane)
    {
        p_window = window;
        p_contentPane = contentPane;
        TopLeftField tlf = p_contentPane.getTLField();
        DrawingWindow dw = new DrawingWindow(tlf.getFieldSize());
        return null;
    }
}
