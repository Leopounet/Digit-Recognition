package com.field;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.image.BufferedImage;

import com.image.*;

/**
 * The field the user can use to change the color.
 **/
public class ColorField extends Field
{
	private static final long serialVersionUID = 1L;
	
    // The image the user is drawing on
    private BufferedImage p_bfImage = null;

    // The drawing field to modify the color of
    private DrawingField p_df = null;

    // Width of a slider
    private int p_sliderWidth = p_fieldSize.width / 2 + 10;

    // Radius of a slider
    private int p_radius = p_fieldSize.height / 8;

    // The list of position of the circle sliders
    private int p_rgbX[] = null;
    private int p_rgbY[] = null;

    // the current color
    private int p_color = 0xFFFFFFFF;

    /**
     * Creates a new ColorField.
     * @param width The width of the field
     * @param height The height of the field
     **/
    public ColorField(int width, int height)
    {
        super(width, height);
        init();
    }

    /**
     * Creates a new ColorField.
     * @param size The size of the field
     **/
    public ColorField(Dimension size)
    {
        super(size);
        init();
    }

    /**
     * Initializes the field.
     **/
    private void init()
    {
        // Init the image the user will draw on to all black
        initField(Color.BLUE);

        // Adss mouse listeners to the field
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseHandler());

        // Creates the arrays to store the position of each slider
        p_rgbX = new int[3];
        p_rgbY = new int[3];

        // Init the sliders
        for(int slider = 0; slider < 3; slider++)
        {
            // Computes the positions of every slider cirlcle
            p_rgbX[slider] = p_sliderWidth;
            p_rgbY[slider] = (slider + 1) * p_fieldSize.height / 4 + p_radius / 2;

        }

        // Draws every sliders
        drawSliders();
    }

    /**
     * Draws every slider and the color preview.
     **/
    private void drawSliders()
    {
        // Creates a new image to draw the sliders on
        p_bfImage = ImageProcessing.createBufferedImage(p_fieldSize, 0xFF000000);

        // Init the sliders
        for(int slider = 0; slider < 3; slider++)
        {
            // Draws the sliders
            drawSlider(10, (slider + 1) * p_fieldSize.height / 4, p_fieldSize.width / 2, p_fieldSize.height / 12);

            // Draws the sliders circles
            ImageProcessing.setCircle(p_bfImage, p_fieldSize, p_rgbX[slider], p_rgbY[slider], p_radius, 0xFFFFFFFF);
        }

        // Creates a circle to preview the current color
        ImageProcessing.setCircle(p_bfImage, p_fieldSize, 3 * p_fieldSize.width / 4, p_fieldSize.height / 2, p_fieldSize.height / 4, p_color);
        repaint();
    }

    /**
     * Draws every sliders.
     * @param topLeftX The top left X position of the rectangle
     * @param topLeftY The top left Y position of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     **/
    private void drawSlider(int topLeftX, int topLeftY, int width, int height)
    {
        for(int x = topLeftX; x < topLeftX + width; x++)
        {
            for(int y = topLeftY; y < topLeftY + height; y++)
            {
                p_bfImage.setRGB(x, y, 0xFFAAAAAA);
            }
        }
    }

    /**
     * Overrides the paintComponent method.
     * @param g The graphic component to use to draw.
     **/
    public void paintComponent (Graphics g)
    {

        // Super of the paintComponent constructor
        super.paintComponent(g);

        // Updates the image the user is drawing
        g.drawImage(p_bfImage, 0, 0, this);
    }

    /**
     * Tests if a point is in a slider.
     * @param x X coordinate of the point to test
     * @param y Y coordinate of the point to test
     * @return The index of the slider the point is in, -1 if none
     **/
    private int isInSlider(int x, int y)
    {
        // If the point is between the left most and right most point of the rectangle
        if(x > 10 && x < p_sliderWidth)
        {
            for(int slider = 0; slider < 3; slider++)
            {
                // If the point is within the circle of the slider
                if(Math.abs(y - p_rgbY[slider]) < p_radius)
                {
                    return slider;
                }
            }
        }
        return -1;
    }

    /**
     * Modifies the position of a cirlce if needed.
     * @param x The X coordinate of the click
     * @param y The Y coordinate of the click
     **/
    private void setCircle(int x, int y)
    {
        // The slider circle to move
        int slider = isInSlider(x, y);

        // If no slider circle to move
        if(slider == -1)
        {
            return;
        }

        // Sets the new position of the slider to move
        p_rgbX[slider] = x;
    }

    /**
     * Sets the current color.
     **/
    private void setColor()
    {
        // Normalize the position of the slider (0 < x < 1) then multiply by 255
        // (0 < x 255)
        int r = (int)(255.0 * (double)(p_rgbX[0] - 10) / (double)(p_fieldSize.width / 2));
        int g = (int)(255.0 * (double)(p_rgbX[1] - 10) / (double)(p_fieldSize.width / 2));
        int b = (int)(255.0 * (double)(p_rgbX[2] - 10) / (double)(p_fieldSize.width / 2));

        // Concatenate components in one single pixel
        p_color = 0xFF000000 | (r << 16) | (g << 8) | b;

        // Sets the color on the drawing field
        p_df.setColor(p_color);
    }

    /**
     * Class to handle mouse interactions with the field.
     **/
    private class MouseHandler extends MouseInputAdapter
    {
        /**
         * Creates a new mouse handler.
         **/
        public MouseHandler()
        {
            super();
        }

        /**
         * Called if the mouse is pressed on this field.
         **/
        public void mousePressed(MouseEvent e)
        {
            // Moves the cursors if needed
            setCircle(e.getX(), e.getY());
            setColor();
            drawSliders();
        }

        /**
         * Called if the mouse is dragged.
         **/
        public void mouseDragged(MouseEvent e)
        {
            // Moves the cursors if needed
            setCircle(e.getX(), e.getY());
            setColor();
            drawSliders();
        }
    }

    /**
     * Sets the drawing field to modify.
     * @param df the DrawingField to consider
     **/
    public void setDrawingField(DrawingField df)
    {
        p_df = df;
    }
}
