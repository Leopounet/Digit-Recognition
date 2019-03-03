import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public abstract class Field extends JPanel
{
    // Size of the field in pixels
    protected Dimension p_fieldSize = null;
    protected Window p_window = null;

    /**
     * Creates a new field object.
     * @param window The current window
     * @param width Width in pixels of the newly created window
     * @param height Height in pixels of the newly created window
     **/
    public Field(Window window, int width, int height)
    {
        super();
        p_fieldSize = new Dimension(width, height);
        p_window = window;
    }

    /**
     * Creates a new field object.
     * @param window The current window
     * @param size The size of the window in pixels (width, height) format
     **/
    public Field(Window window, Dimension size)
    {
        super();
        p_fieldSize = size;
        p_window = window;
    }

    /**
     * Initializes the field layout.
     * @param c The background color of the field. (mostly used to debug)
     **/
    protected void initField(Color c)
    {
        this.setBackground(c);
        this.setPreferredSize(p_fieldSize);
    }

    /**
     * Returns the size of the field.
     * @return The size of the field as a Dimension object.
     **/
    public Dimension getFieldSize()
    {
        return p_fieldSize;
    }

    /**
     * Sets the size of the field.
     * @param width Width in pixels of the created field
     * @param height Height in pixels of the created field
     **/
    public void setFieldSize(int width, int height)
    {
        p_fieldSize = new Dimension(width, height);
    }

    /**
     * @param size The size of the field in pixels (width, height) format
     **/
    public void setFieldSize(Dimension size)
    {
        p_fieldSize = size;
    }
}
