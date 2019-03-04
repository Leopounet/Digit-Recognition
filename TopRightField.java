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

    /**
     * Creates the top right field.
     * @param window The current window
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public TopRightField(Window window, int width, int height)
    {
        super(window, width, height);
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
        initField(Color.YELLOW);
        initTopRightField();
    }

    /**
     * Initializes the top right field.
     **/
    private void initTopRightField()
    {
        // Sets the layout to a 1 row * 1 col format
        this.setLayout(new GridLayout(p_nbButtonRow, p_nbButtonColumn));

        // Adds a new button
        this.add(new JButton("Upload"));
    }
}
