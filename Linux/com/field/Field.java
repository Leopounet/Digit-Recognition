package com.field;

import java.awt.*;
import javax.swing.*;
/**
 * Represents a section of a window.
 **/
public abstract class Field extends JPanel
{
	private static final long serialVersionUID = 1L;
	// Size of the field in pixels
    protected Dimension p_fieldSize = null;

    /**
     * Creates a new field object.
     * @param width Width in pixels of the newly created window
     * @param height Height in pixels of the newly created window
     **/
    public Field(int width, int height)
    {
        super();
        p_fieldSize = new Dimension(width, height);
    }

    /**
     * Creates a new field object.
     * @param size The size of the window in pixels (width, height) format
     **/
    public Field(Dimension size)
    {
        super();
        p_fieldSize = size;
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
