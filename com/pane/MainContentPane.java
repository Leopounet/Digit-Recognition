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
public class MainContentPane extends ContentPane
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

    public MainContentPane(Dimension size)
    {
        super(size);

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
      * Returns the top left field.
      * @return A TopLeftField variable
      **/
     public TopLeftField getTLField()
     {
         return p_tlField;
     }

     /**
      * Returns the top right field.
      * @return A TopRightField variable
      **/
     public TopRightField getTRField()
     {
         return p_trField;
     }

     /**
      * Returns the middle field.
      * @return A MiddleField variable
      **/
     public MiddleField getMField()
     {
         return p_mField;
     }

     /**
      * Returns the bottom field.
      * @return A BottomField variable
      **/
     public BottomField getBField()
     {
         return p_bField;
     }
}
