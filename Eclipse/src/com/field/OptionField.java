package com.field;

import java.awt.*;
import javax.swing.*;

/**
 * The field of the drawing window containing the buttons.
 **/
public class OptionField extends Field
{
	private static final long serialVersionUID = 1L;
	
    // The different buttons on this field
    private JButton p_finishButton = null;
    private JButton p_increaseSizeButton = null;
    private JButton p_decreaseSizeButton = null;

    // Number of buttons on a row
    private int p_nbButtonRow = 1;

    // Number of buttons on a column
    private int p_nbButtonColumn = 3;

    /**
     * Creates a new option field.
     * @param width The width of the field
     * @param height The height of the field
     **/
    public OptionField(int width, int height)
    {
        super(width, height);
        initField(Color.DARK_GRAY);
        initButtons();
        initOptionField();
    }

    /**
     * Creates a new option field.
     * @param size The size of the field
     **/
    public OptionField(Dimension size)
    {
        super(size);
        initField(Color.DARK_GRAY);
        initButtons();
        initOptionField();
    }

    /**
     * Creates and initializes the different buttons.
     **/
    public void initButtons()
    {
        // Creates the buttons
        p_finishButton = new JButton("Finish");
        p_increaseSizeButton = new JButton("Increase Size (30)");
        p_decreaseSizeButton = new JButton("Decrease Size (30)");
    }

    /**
     * Initializes the top right field.
     **/
    private void initOptionField()
    {
        // Sets the layout to a 1 row * 1 col format
        this.setLayout(new GridLayout(p_nbButtonRow, p_nbButtonColumn));

        // Adds the buttons
        this.add(p_finishButton);
        this.add(p_increaseSizeButton);
        this.add(p_decreaseSizeButton);
    }

    /**
     * Returns the upload button.
     * @return An UploadButton instance.
     **/
    public JButton getFinishButton()
    {
        return p_finishButton;
    }

    /**
     * Returns the submit button.
     * @return An JButton instance.
     **/
    public JButton getIncreaseSizeButton()
    {
        return p_increaseSizeButton;
    }

    /**
     * Returns the random image button.
     * @return An JButton instance.
     **/
    public JButton getDecreaseSizeButton()
    {
        return p_decreaseSizeButton;
    }
}
