import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public class TopRightField extends Field
{
    // Number of buttons on a row
    private int p_nbButtonRow = 1;

    // Number of buttons on a column
    private int p_nbButtonColumn = 1;

    // List of buttons on this field
    private UploadButton p_upButton = null;

    /**
     * Creates the top right field.
     * @param window The current window
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public TopRightField(Window window, int width, int height)
    {
        super(window, width, height);
        initButtons();
        initField(Color.YELLOW);
        initTopRightField();
    }

    /**
     * Creates the top right field.
     * @param window The current window
     * @param size The size of the newly created field
     **/
    public TopRightField(Window window, Dimension size)
    {
        super(window, size);
        initButtons();
        initField(Color.YELLOW);
        initTopRightField();
    }

    /**
     * Creates and initializes the different buttons.
     **/
    public void initButtons()
    {
        p_upButton = new UploadButton("Upload");
    }

    /**
     * Initializes the top right field.
     **/
    private void initTopRightField()
    {
        // Sets the layout to a 1 row * 1 col format
        this.setLayout(new GridLayout(p_nbButtonRow, p_nbButtonColumn));

        // Adds a new button
        this.add(p_upButton);
    }

    /**
     * Returns the upload button.
     * @return An UploadButton instance.
     **/
    public UploadButton getUploadButton()
    {
        return p_upButton;
    }
}
