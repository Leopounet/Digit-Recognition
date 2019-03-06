package com.field;

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
        initField(Color.GRAY);

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

            drawArrow(g);

            // For every rectangles
            for(int index = 0; index < p_nbRectangles; index++)
            {

                // Draws a rectangle to represent the probabilities
                drawRectangle(g, index);

                // Draws the probability of teh current digit to be the correct
                // digit
                drawProbabilities(g, index);

                // Draws the digit under teh rectangle
                drawDigit(g, index);
            }

            // Set to false, to not draw again until asked for
            p_draw = false;
        }
    }

    /**
     * Draws an arrow to left side of the diagram.
     * @param g The Graphics component to use
     **/
    private void drawArrow(Graphics g)
    {
        // Draws an arrow on the lef side of the diagram and writes confidence[%]
        g.setColor(Color.BLACK);
        g.setFont(new Font("default", Font.BOLD, 14));

        g.drawString("Confidence [%]", 0, 16);
        g.fillRect(p_fieldSize.width / 20,
                   p_fieldSize.height / 10,
                   p_fieldSize.width / 100 + 1,
                   8 * p_fieldSize.height / 10);

        int middle = (2 * p_fieldSize.width / 20 + p_fieldSize.width / 100 + 1) / 2;
        int polygonX[] = { middle,
                           middle - p_fieldSize.width / 40,
                           middle + p_fieldSize.width / 40 };

        int polygonY[] = { p_fieldSize.height / 20,
                            p_fieldSize.height / 10,
                            p_fieldSize.height / 10 };
        g.fillPolygon(polygonX, polygonY, 3);
    }

    /**
     * Draws a rectangle.
     * @param g The Graphics component to use
     * @param index The index of the rectangle to draw
     **/
    private void drawRectangle(Graphics g, int index)
    {
        // Draws the rectangle
        g.fillRect(p_rectangles[index].x,
                   p_rectangles[index].y,
                   p_rectangles[index].width,
                   p_rectangles[index].height);
    }

    /**
     * Draws the probabilities.
     * @param g The Graphics component to use
     * @param index The index of the rectangle to draw
     **/
    private void drawProbabilities(Graphics g, int index)
    {
        // Makes the font bold red
        g.setColor(Color.RED);

        // Draws the probability of the current digit (above the column)
        // The positions have been chosen arbitrarly
        g.drawString(String.format("%d%%", (int)(p_probabilities[index] * 100)),
                                             p_rectangles[index].x +
                                             p_rectangles[index].width / 8,
                                             p_rectangles[index].y -
                                             p_fieldSize.height / 64);
    }

    /**
     * Draws the digit under the rectangle.
     * @param g The Graphics component to use
     * @param index The index of the rectangle to draw
     **/
    private void drawDigit(Graphics g, int index)
    {
        // Makes the font black
        g.setColor(Color.BLACK);

        // Draws the current digits (under the column)
        // The positions have been chosen arbitrarly
        g.drawString(String.format("%d", index), p_rectangles[index].x +
                                                 p_fieldSize.width / 30,
                                                 19 * p_fieldSize.height / 20);
    }

    /**
     * Sets the array of rectangles so that the paintComponent method can draw them.
     * @param probabilities An array of probabilities (0 < x < 1 for every vals)
     * Note : Every position have been chosen arbitrarly, in a way that it looks ok.
     **/
    public void drawDiagram(double probabilities[])
    {
        // Width of every rectangles
        int rectWidth = p_fieldSize.width / 12;

        // This is so there is a little space under the column to write th digits
        int upWardShift = p_fieldSize.height / 10;

        // Size of the zone the plot will be drawn (80% of the field)
        int plotHeight = 8 * p_fieldSize.height / 10;

        // For every rectangles
        for(int index = 0; index < p_nbRectangles; index++)
        {
            // Left most point of the current rectangle
            int startRectX = (index + 1) * rectWidth + rectWidth;

            // Height of teh rectangle
            int rectHeight = (int)(plotHeight * probabilities[index] + 10);

            // Up most point of the current rectangle
            int startRectY = 9 * p_fieldSize.height / 10 - rectHeight;

            // Creates and stores the rectangle corresponding to the current digit
            p_rectangles[index] = new Rectangle(startRectX, startRectY, rectWidth, rectHeight);
        }

        // Set to true so that paintComponent draws
        p_draw = true;

        // Sets the local array of probababilities to the given probabilities
        p_probabilities = probabilities;
        repaint();
    }
}
