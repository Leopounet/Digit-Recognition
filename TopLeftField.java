import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public class TopLeftField extends Field
{
    /**
     * Creates the top left field.
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public TopLeftField(int width, int height)
    {
        super(width, height);
        initField(Color.RED);
    }

    /**
     * Creates the top left field.
     * @param size The size of the newly created field
     **/
    public TopLeftField(Dimension size)
    {
        super(size);
        initField(Color.RED);
    }
}
