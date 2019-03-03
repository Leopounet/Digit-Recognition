import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class TopLeftField extends Field
{
    /**
     * Creates the top left field.
     * @param window The current window
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public TopLeftField(Window window, int width, int height)
    {
        super(window, width, height);
        initField(Color.RED);
    }

    /**
     * Creates the top left field.
     * @param window The current window
     * @param size The size of the newly created field
     **/
    public TopLeftField(Window window, Dimension size)
    {
        super(window, size);
        initField(Color.RED);
    }
}
