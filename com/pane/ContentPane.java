package com.pane;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

import com.field.*;

/**
 * Content pane of the main window.
 **/
public class ContentPane extends JPanel
{
    /**
     * Creates a new content pane.
     * @param size The size of the content pane
     **/
    public ContentPane(Dimension size)
    {
        super(new GridBagLayout());
        this.setPreferredSize(size);
    }

    /**
     * Adds a field to the content pane.
     * @param zone The zone to add
     * @param x The position of the field on the current row (in "field" unit)
     * @param y The position of the field on the current column (in "field" unit)
     * @param width The width of the field (in "field" unit)
     * @param height The height of the field (in "field" unit)
     **/
     protected void addField(JPanel zone, int x, int y, int width, int height)
     {
         // Sets the gridbagconstraints so that the field is placed correctly
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = x;
         gbc.gridy = y;
         gbc.gridheight = height;
         gbc.gridwidth = width;

         // Adds the field at the right position
         this.add(zone, gbc);
     }
}
