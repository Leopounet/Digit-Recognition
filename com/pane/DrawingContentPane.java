package com.pane;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

import com.field.*;

/**
 * The content pane to draw on.
 **/
public class DrawingContentPane extends ContentPane
{
    // Fields on the window
    private DrawingField p_dField = null;
    private OptionField p_oField = null;

    // Fields sizes
    private Dimension p_pFieldSize = null;
    private Dimension p_oFieldSize = null;

    public DrawingContentPane(Dimension size)
    {
        super(size);

        // Sets the sizes of the different fields
        // These sizes have been choosen arbitrarly
        p_pFieldSize = new Dimension(size.width, 10 * size.height / 12);
        p_oFieldSize = new Dimension(size.width, 2 * size.height / 12);

        // Creates the four main zones
        p_dField = new DrawingField(p_pFieldSize);
        p_oField = new OptionField(p_oFieldSize);

        // Adds them to the content pane
        addField(p_dField, 0, 0, 1, 1);
        addField(p_oField, 0, 1, 1, 1);
    }

     /**
      * Returns the top left field.
      * @return A TopLeftField variable
      **/
     public DrawingField getDField()
     {
         return p_dField;
     }

     /**
      * Returns the top right field.
      * @return A TopRightField variable
      **/
     public OptionField getOField()
     {
         return p_oField;
     }
}
