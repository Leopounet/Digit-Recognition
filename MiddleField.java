import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.awt.Graphics;

/**
 * Represents the middle field of this window.
 **/
public class MiddleField extends Field
{
    // List of rectangles to represent to probabilities
    private Rectangle p_rectangles[] = null;

    // True if the diagram has to be drawn
    private boolean p_draw = false;

    // The probabilities to display
    private double p_probabilities[] = null;

    // Number of rectangles to display
    private int p_nbRectangles = 10;

    /**
     * Creates the middle field.
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public MiddleField(int width, int height)
    {
        super(width, height);
        init();
    }

    /**
     * Creates the middle field.
     * @param size The size of the newly created field
     **/
    public MiddleField(Dimension size)
    {
        super(size);
        init();
    }

    /**
     * Initialized the field.
     **/
    private void init()
    {
        // Initializes the field
        initField(Color.GREEN);

        // Creates a new array of ten rectangles
        p_rectangles = new Rectangle[p_nbRectangles];

        // Init the rectangles
        initRectangles();
    }

    /**
     * Sets every cells of the rectangle array to an empty rectangle
     **/
    private void initRectangles()
    {
        // For every rectangles, set them to an empty rectangles
        for(int index = 0; index < p_nbRectangles; index++)
        {
            p_rectangles[index] = new Rectangle();
        }
    }

    @Override
    /**
     * Paints every ractangles.
     * @param g The graphic component to use
     **/
    public void paintComponent(Graphics g)
    {
        // Super of the paintComponent constructor
        super.paintComponent(g);

        // If the button to submit as been clicked
        if(p_draw)
        {
            // For every rectangles
            for(int index = 0; index < p_nbRectangles; index++)
            {
                // Draws the rectangle
                g.fillRect(p_rectangles[index].x,
                           p_rectangles[index].y,
                           p_rectangles[index].width,
                           p_rectangles[index].height);

                // Makes the font bold red
                g.setFont(new Font("default", Font.BOLD, 16));
                g.setColor(Color.RED);

                // Draws the probability of the current digit (above the column)
                // The positions have been chosen arbitrarly
                g.drawString(String.format("%.2f%%", p_probabilities[index]), p_rectangles[index].x +
                                                     p_rectangles[index].width / 8,
                                                     p_rectangles[index].y -
                                                     p_fieldSize.height / 16);
                // Makes the font black
                g.setColor(Color.BLACK);

                // Draws the current digits (under the column)
                // The positions have been chosen arbitrarly
                g.drawString(String.format("%d", index), p_rectangles[index].x +
                                                         p_fieldSize.width / 30,
                                                         19 * p_fieldSize.height / 20);
            }

            // Set to false, to not draw again until asked for
            p_draw = false;
        }
    }

    /**
     * Sets the array of rectangles so that the paintComponent method can draw them.
     * @param probabilities An array of probabilities (0 < x < 1 for every vals)
     * Note : Every position have been chosen arbitrarly, in a way that it looks ok.
     **/
    public void drawDiagram(double probabilities[])
    {
        // Width of every rectangles
        double rectWidth = p_fieldSize.width / 12;

        // This is so there is a little space under the column to write th digits
        double upWardShift = p_fieldSize.height / 10;

        // Size of the zone the plot will be drawn (80% of the field)
        double plotHeight = 8 * p_fieldSize.height / 10;

        // For every rectangles
        for(int index = 0; index < p_nbRectangles; index++)
        {
            // Left most point of the current rectangle
            double startRectX = (index + 1) * rectWidth;

            // Height of teh rectangle
            double rectHeight = plotHeight * probabilities[index];

            // Up most point of the current rectangle
            double startRectY = upWardShift + (plotHeight - rectHeight);

            // Creates and stores the rectangle corresponding to the current digit
            p_rectangles[index] = new Rectangle((int)startRectX, (int)startRectY, (int)rectWidth, (int)rectHeight);
        }

        // Set to true so that paintComponent draws
        p_draw = true;

        // Sets the local array of probababilities to the given probabilities
        p_probabilities = probabilities;
        repaint();
    }
}
