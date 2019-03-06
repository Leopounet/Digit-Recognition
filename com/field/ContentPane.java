package com.field;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public class ContentPane extends JPanel
{
    // Fields on the window
    private TopLeftField p_tlField = null;
    private TopRightField p_trField = null;
    private MiddleField p_mField = null;
    private BottomField p_bField = null;

    // Fields sizes
    private Dimension p_tlFieldSize = null;
    private Dimension p_trFieldSize = null;
    private Dimension p_mFieldSize = null;
    private Dimension p_bFieldSize = null;

    public ContentPane(Dimension size)
    {
        super(new GridBagLayout());
        this.setPreferredSize(size);

        // Sets the sizes of the different fields
        // These sizes have been choosen arbitrarly
        p_tlFieldSize = new Dimension(8 * size.width / 10, size.height / 2);
        p_trFieldSize = new Dimension(2 * size.width / 10, size.height / 2);
        p_mFieldSize = new Dimension(size.width, 4 * size.height / 10);
        p_bFieldSize = new Dimension(size.width, 1 * size.height / 10);

        // Creates the four main zones
        p_tlField = new TopLeftField(p_tlFieldSize);
        p_trField = new TopRightField(p_trFieldSize);
        p_mField = new MiddleField(p_mFieldSize);
        p_bField = new BottomField(p_bFieldSize);

        // Adds them to the content pane
        addField(p_tlField, 0, 0, 1, 1);
        addField(p_trField, 1, 0, 1, 1);
        addField(p_mField, 0, 1, 2, 1);
        addField(p_bField, 0, 2, 2, 1);
    }

    /**
     * Adds a field to the content pane.
     * @param zone The zone to add
     * @param x The position of the field on the current row (in "field" unit)
     * @param y The position of the field on the current column (in "field" unit)
     * @param width The width of the field (in "field" unit)
     * @param height The height of the field (in "field" unit)
     **/
     private void addField(JPanel zone, int x, int y, int width, int height)
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

     public TopLeftField getTLField()
     {
         return p_tlField;
     }

     public TopRightField getTRField()
     {
         return p_trField;
     }

     public MiddleField getMField()
     {
         return p_mField;
     }

     public BottomField getBField()
     {
         return p_bField;
     }
}
