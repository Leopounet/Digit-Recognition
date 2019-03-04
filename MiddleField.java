import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.awt.Graphics;

public class MiddleField extends Field
{
    // List of rectangles to represent to probabilities
    private Rectangle p_rectangles[] = null;

    // True if the diagram has to be drawn
    private boolean p_draw = false;

    // The probabilities to display
    private double p_probabilities[] = null;

    /**
     * Creates the middle field.
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public MiddleField(int width, int height)
    {
        super(width, height);
        initField(Color.GREEN);
        p_rectangles = new Rectangle[10];
        initRectangles();
    }

    /**
     * Creates the middle field.
     * @param size The size of the newly created field
     **/
    public MiddleField(Dimension size)
    {
        super(size);
        initField(Color.GREEN);
        p_rectangles = new Rectangle[10];
        initRectangles();
    }

    private void initRectangles()
    {
        for(int index = 0; index < 10; index++)
        {
            p_rectangles[index] = new Rectangle();
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(p_draw)
        {
            for(int index = 0; index < 10; index++)
            {
                g.fillRect(p_rectangles[index].x,
                           p_rectangles[index].y,
                           p_rectangles[index].width,
                           p_rectangles[index].height);

                g.drawString(String.format("%.2f%%", p_probabilities[index]), p_rectangles[index].x +
                                    p_fieldSize.width / 36,
                                    p_rectangles[index].y -
                                    p_fieldSize.height / 16);

                g.drawString(String.format("%d", index), p_rectangles[index].x +
                                p_fieldSize.width / 30,
                                19 * p_fieldSize.height / 20);
            }
        }
    }

    public void drawDiagram(double probabilities[])
    {
        double rectWidth = p_fieldSize.width / 12;
        double upWardShift = p_fieldSize.height / 10;
        double plotHeight = 8 * p_fieldSize.height / 10;
        for(int index = 0; index < 10; index++)
        {
            double startRectX = (index + 1) * rectWidth;
            double rectHeight = plotHeight * probabilities[index];
            double startRectY = upWardShift + (plotHeight - rectHeight);
            p_rectangles[index] = new Rectangle((int)startRectX, (int)startRectY, (int)rectWidth, (int)rectHeight);
        }
        p_draw = true;
        p_probabilities = probabilities;
        repaint();
    }
}
