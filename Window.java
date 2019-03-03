import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public class Window extends JFrame
{
    // Used to store the width and height of the window in pixels
    private Dimension p_windowSize = null;

    // Pane of the window
    private JPanel p_contentPane = null;

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
        createFields();
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
        createFields();
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

    /**
     * Creates the different fields of the window. The window is devided in four
     * different fields. See the documentation for more infos.
     **/
    private void createFields()
    {
        // Create and add the content pane of the JFrame
        p_contentPane = new JPanel(new GridBagLayout());
        p_contentPane.setPreferredSize(p_windowSize);
        this.setContentPane(p_contentPane);
    }

    /**
     * Adds a field to the content pane.

     **/
    private void addField()
    {

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
