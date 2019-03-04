import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

/**
 * Represents the bottom field of this window.
 **/
public class BottomField extends Field
{
    /**
     * Creates the bottom field.
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public BottomField(int width, int height)
    {
        super(width, height);
        init();
    }

    /**
     * Creates the bottom field.
     * @param size The size of the newly created field
     **/
    public BottomField(Dimension size)
    {
        super(size);
        init();
    }

    /**
     * Initializes the current object.
     **/
    private void init()
    {
        initField(Color.BLUE);
    }
}
