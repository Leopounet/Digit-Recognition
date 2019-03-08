package com.button;

import com.field.*;
import com.window.*;
import com.pane.*;

/**
 * Called when the draw button is pressed.
 **/
public class DrawButtonListener extends ButtonListener
{
    /**
     * Creates a new window to draw on.
     * @param window The main window to use
     * @param contentPane The content pane to consider
     **/
    public static void drawImage(MainWindow window, MainContentPane contentPane)
    {
        // Sets the window to use
        p_window = window;

        // Sets the content pane to use
        p_contentPane = contentPane;

        // Gets the top left field
        TopLeftField tlf = p_contentPane.getTLField();

        // Creates a new drawing window with the same dimension as the top left field
        DrawingWindow dw = new DrawingWindow(tlf.getFieldSize().width, (int)((double)(tlf.getFieldSize().height) * 1.4));

        // Sends the main window to the drawing window
        dw.setMainWindow(p_window);

        // Hides the main window
        p_window.setVisible(false);
    }
}
