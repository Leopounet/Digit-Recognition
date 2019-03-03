import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class TopLeftField extends Field
{
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        BufferedImage bf = new BufferedImage(28, 28, BufferedImage.TYPE_INT_ARGB);
        bf.setRGB(0, 0, 28, 28, p_window.p_dataSet.p_listImages[0], 0, 28);
        g.drawImage(bf, 28, 28, this);
        System.out.println(p_window.p_dataSet.p_listLabels[0]);
    }

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
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Realeased");
            }
        });
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
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("Pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("Realeased");
            }
        });
    }
}
