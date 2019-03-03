import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public class TopRightField extends Field
{
    /**
     * Creates the top right field.
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public TopRightField(int width, int height)
    {
        super(width, height);
    }

    /**
     * Creates the top right field.
     * @param size The size of the newly created field
     **/
    public TopRightField(Dimension size)
    {
        super(size);
    }
}
