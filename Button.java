import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public class Button extends JButton
{
    /**
     * Creates a new Button.
     * @param name The name displayed on the button
     **/
    public Button(String name)
    {
        this.setText(name);
    }
}
