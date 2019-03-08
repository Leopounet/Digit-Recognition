package com.field;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

import com.image.*;

/**
 * Represents the top left field of this window.
 **/
public class TopLeftField extends Field
{
	private static final long serialVersionUID = 1L;
	
	// Image to display
    private Image p_image = null;

    /**
     * Creates the top left field.
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public TopLeftField(int width, int height)
    {
        super(width, height);
        initField(Color.WHITE);
    }

    /**
     * Creates the top left field.
     * @param size The size of the newly created field
     **/
    public TopLeftField(Dimension size)
    {
        super(size);
        initField(Color.WHITE);
    }

    /**
     * Overrides the paintComponent method.
     * @param g The graphic component to use to draw.
     **/
    public void paintComponent (Graphics g){

        // Super of the paintComponent constructor
        super.paintComponent(g);
        paintImage(g);
    }

    /**
     * Paints the image to the canvas.
     * @param g The graphic component to use to draw.
     **/
    private void paintImage(Graphics g)
    {
    	if(p_image == null)
    	{
    		return;
    	}
        g.drawImage(p_image, 0, 0, this);
    }

    /**
     * If possible, return the selected image.
     * @param path The path to the image.
     * @return The resized image uploaded.
     **/
    public Image getImage(String path)
    {
        // The original image
        Image tmp = null;
        try
        {
            // Loads the image if possible
            tmp = ImageIO.read(new File(path));

            tmp = ImageProcessing.centerDigit(tmp);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return tmp;
    }

    /**
     * Displays the selected image.
     * @param image The image to display
     **/
    public void displayImage(Image image)
    {
        // Resizes the image so that it fits the top left field
        p_image = image.getScaledInstance(p_fieldSize.width,
                                          p_fieldSize.height,
                                          Image.SCALE_DEFAULT);

        repaint();
    }
}
