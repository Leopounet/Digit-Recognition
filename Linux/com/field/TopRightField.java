package com.field;

import java.awt.*;
import javax.swing.*;

/**
 * Represents the top right field of this window.
 **/
public class TopRightField extends Field
{
	private static final long serialVersionUID = 1L;

	// Number of buttons on a row
    private int p_nbButtonRow = 5;

    // Number of buttons on a column
    private int p_nbButtonColumn = 1;

    // List of buttons on this field
    private JButton p_upButton = null;
    private JButton p_submitButton = null;
    private JButton p_randomImageButton = null;
    private JButton p_drawButton = null;
    private JButton p_saveButton = null;

    /**
     * Creates the top right field.
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public TopRightField(int width, int height)
    {
        super(width, height);
        init();
    }

    /**
     * Creates the top right field.
     * @param size The size of the newly created field
     **/
    public TopRightField(Dimension size)
    {
        super(size);
        init();
    }

    /**
     * initializes this object.
     **/
    private void init()
    {
        // Inits the buttons
        initButtons();

        // Inits the field
        initField(Color.GRAY);

        // Init the top right field
        initTopRightField();
    }

    /**
     * Creates and initializes the different buttons.
     **/
    public void initButtons()
    {
        // Creates the buttons
        p_upButton = new JButton("Upload");
        p_submitButton = new JButton("Submit");
        p_randomImageButton = new JButton("Random Image");
        p_drawButton = new JButton("Draw Digit");
        p_saveButton = new JButton("Save");
    }

    /**
     * Initializes the top right field.
     **/
    private void initTopRightField()
    {
        // Sets the layout to a 1 row * 1 col format
        this.setLayout(new GridLayout(p_nbButtonRow, p_nbButtonColumn));

        // Adds the buttons
        this.add(p_upButton);
        this.add(p_submitButton);
        this.add(p_randomImageButton);
        this.add(p_drawButton);
        this.add(p_saveButton);
    }

    /**
     * Returns the upload button.
     * @return An UploadButton instance.
     **/
    public JButton getUploadButton()
    {
        return p_upButton;
    }

    /**
     * Returns the submit button.
     * @return An JButton instance.
     **/
    public JButton getSubmitButton()
    {
        return p_submitButton;
    }

    /**
     * Returns the random image button.
     * @return An JButton instance.
     **/
    public JButton getRandomImageButton()
    {
        return p_randomImageButton;
    }

    /**
     * Returns the draw button.
     * @return An JButton instance.
     **/
    public JButton getDrawButton()
    {
        return p_drawButton;
    }

    /**
     * Returns the save button.
     * @return An JButton instance.
     **/
    public JButton getSaveButton()
    {
        return p_saveButton;
    }
}
