import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public class BottomField extends Field
{
    /**
     * Creates the bottom field.
     * @param window The current window
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public BottomField(Window window, int width, int height)
    {
        super(window, width, height);
        initField(Color.BLUE);
    }

    /**
     * Creates the bottom field.
     * @param window The current window
     * @param size The size of the newly created field
     **/
    public BottomField(Window window, Dimension size)
    {
        super(window, size);
        initField(Color.BLUE);
    }
}
