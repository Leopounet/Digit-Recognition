import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public class Window extends JFrame
{
    // Used to store the width and height of the window in pixels
    private Dimension p_windowSize = null;

    /**
     * Creates a new window object.
     * @param width Width in pixels of the newly created window
     * @param height Height in pixels of the newly created window
     **/
    public Window(int width, int height)
    {
        super();
        p_windowSize = new Dimension(width, height);
        initWindow();
    }

    /**
     * Creates a new window object.
     * @param size The size of the window in pixels (width, height) format
     **/
    public Window(Dimension size)
    {
        super();
        p_windowSize = size;
        initWindow();
    }

    /**
     * Sets the default parameters of the window and displays it.
     **/
    private void initWindow()
    {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(p_windowSize);
        this.setVisible(true);
    }
}
