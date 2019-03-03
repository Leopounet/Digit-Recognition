import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

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
    }

    /**
     * Creates the bottom field.
     * @param size The size of the newly created field
     **/
    public BottomField(Dimension size)
    {
        super(size);
    }
}
