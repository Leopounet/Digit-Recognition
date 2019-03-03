import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public class MiddleField extends Field
{
    /**
     * Creates the middle field.
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public MiddleField(Window window, int width, int height)
    {
        super(window, width, height);
        initField(Color.GREEN);
    }

    /**
     * Creates the middle field.
     * @param size The size of the newly created field
     **/
    public MiddleField(Window window, Dimension size)
    {
        super(window, size);
        initField(Color.GREEN);
    }
}
