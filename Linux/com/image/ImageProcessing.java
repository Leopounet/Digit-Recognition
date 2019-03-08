package com.image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Class to store Image related methods.
 **/
public class ImageProcessing
{
    /**
     * Creates the image the user will draw on and fills it with squares.
     * @param fieldSize The size ofthe canvas
     * @param color The default color of every pixels
     **/
    public static BufferedImage createBufferedImage(Dimension fieldSize, int color)
    {
        // Creates the image
        BufferedImage image = new BufferedImage(fieldSize.width, fieldSize.height, BufferedImage.TYPE_INT_ARGB);

        // For every pixels of the image
        for(int x = 0; x < fieldSize.width; x++)
        {
            for(int y = 0; y < fieldSize.height; y++)
            {
                // Set the pixel black
                image.setRGB(x, y, color);
            }
        }
        return image;
    }

    /**
     * Sets every pixels belonging to the circle of radius p_radius with center
     (x, y).
     * @param image The image to draw on
     * @param fieldSize The size of the canvas
     * @param x The x coordinate of the center of the circle to draw
     * @param y The y coordinate of the center of the circle to draw
     * @param radius The raidus of the circle to draw
     * @param color The color to use
     **/
    public static void setCircle(BufferedImage image, Dimension fieldSize, int x, int y, int radius, int color)
    {
        // Creates a new array of position
        ColoredPoint p_points[] = new ColoredPoint[10000];

        // Fills the array with every pixel in the circle around the mouse
        getCircle(image, fieldSize, p_points, x, y, radius, color);

        // For every pixel recorded
        for(ColoredPoint p : p_points)
        {
            // If the pixel is null, the end of the list has been reached
            if(p == null)
            {
                break;
            }
            image.setRGB(p.x, p.y, p.c);
        }
    }

    /**
     * Fills the given array with the position of every pixels within the selected
     * radius.
     * @param image The image to draw on
     * @param fieldSize The size of the canvas
     * @param pos The list of pixels to draw
     * @param centerX The center X of the circle to draw
     * @param centerY The center Y of the circle to draw
     * @param radius The raidus of the circle to draw
     * @param color The color to use
     **/
    public static void getCircle(BufferedImage image, Dimension fieldSize, ColoredPoint pos[], int centerX, int centerY, int radius, int color)
    {
        int index = 0;
        // For every pixel within the square from (x - r, y - r) (x + r, y + r)
        for(int x = centerX - radius; x < centerX + radius; x++)
        {
            for(int y = centerY - radius; y < centerY + radius; y++)
            {
                // If the pixel is outside the field dimensions
                if(x < 0 || y < 0 || x >= fieldSize.width || y >= fieldSize.height)
                {
                    continue;
                }

                // If the pixel  is within the circle of radius p_radius, add it
                // to the list
                int d = distance(x, y, centerX, centerY);
                if(d < radius)
                {
                    // Sets the new color of the pixel
                    pos[index] = new ColoredPoint(x, y, color);
                    index++;
                }
            }
        }
    }

    /**
     * Returns the distance between two given point.
     * @param x The x coordinate of the first point
     * @param y The y coordinate of the first point
     * @param centerX The x coordinate of the second point
     * @param centerY The y coordinate of the second point
     **/
    private static int distance(int x, int y, int centerX, int centerY)
    {
        // Pythagorian theorem
        return (int) Math.sqrt(Math.pow((double)(x - centerX), 2) + Math.pow((double)(y - centerY), 2));
    }

    /**
     * Tries to put the digit at the center of the image.
     * @param image The image to process
     * @return The centered image
     **/
    public static Image centerDigit(Image image)
    {
        // Get the width and height of the image
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        // Convert Image to BufferedImage
        BufferedImage oldImage = new BufferedImage(width,
                                                 height,
                                                 BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = oldImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        // Creates a new image in which the digit will be centered
        BufferedImage newImage = new BufferedImage(width,
                                                   height,
                                                   BufferedImage.TYPE_INT_ARGB);

        // Finds the middle of the digit
        int middle[] = findMiddles(width, height, oldImage);

        // Computes the sift to apply to every black pixel of the first image to
        // put the digit at the center of the new image
        int shiftX = width / 2 - middle[0];
        int shiftY = height / 2 - middle[1];

        // The main color of the image
        int mainColor = getMainColor(oldImage);

        // For every pixels in the old image
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                // If the new x and y are in the new image (not out of bounds)
                // set the shifted pixel to have the save color as the not
                // shifted pixel in the old image
                if(x - shiftX >= 0 && x - shiftX < width && y - shiftY >= 0 && y - shiftY < height)
                {
                    newImage.setRGB(x, y, oldImage.getRGB(x - shiftX, y - shiftY));
                }

                // Otherwise set the pixel black
                else
                {
                    newImage.setRGB(x, y, mainColor);
                }
            }
        }
        return (Image) newImage;
    }

    /**
     * Finds the middle of the digit.
     * @param width The width of the images
     * @param height The height of the images
     * @param oldImage The not centered digit
     * @return An array of two integer, the first one being the X position of the
     * middle and the second one being the Y position of the middle
     **/
    private static int[] findMiddles(int width, int height, BufferedImage oldImage)
    {
        // Stores the heighest up most and left most pixels that are black
        int firstX = width;
        int firstY = -1;

        // Stores the heighest down most and right most pixels that are black
        int lastX = 0;
        int lastY = 0;

        // For every pixel in the original image
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                // If the pixel is black
                if(oldImage.getRGB(x, y) != 0xFF000000)
                {
                    // If the up most pixel hasn't been found yet (the first one
                    // has to be the upmost because of the way the image is parsed)
                    if(firstY == -1)
                    {
                        firstY = y;
                    }

                    // The down most pixel has to be the last black pixel found
                    // because of the way the image is parsd
                    lastY = y;

                    // If the current pixel is to the left of the current left
                    // most pixel found
                    if(firstX > x)
                    {
                        firstX = x;
                    }

                    // If the current pixel is to the right of the current right
                    // most pixel found
                    if(lastX < x)
                    {
                        lastX = x;
                    }
                }
            }
        }

        // Computes the center of the rectangle found above, this should be about
        // the center of the digit as long as there are no random black pixels
        int middleX = (lastX - firstX) / 2 + firstX;
        int middleY = (lastY - firstY) / 2 + firstY;
        int middle[] = { middleX, middleY };
        return middle;
    }

    /**
     * Converts an Image to a BufferedImage.
     * @param image The image to convert
     **/
    public static BufferedImage convertImageToBufferedImage(Image image)
    {
        // Convert Image to BufferedImage
        BufferedImage bImage = new BufferedImage(image.getWidth(null),
                                                 image.getHeight(null),
                                                 BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = bImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return bImage;
    }

    /**
     * Converts an Image into an array of integers.
     * @param image The image to convert
     **/
    public static int[] convertImageToIntArray(Image image)
    {
        // Get byte array representing every pixels of the image
        return ((DataBufferInt)convertImageToBufferedImage(image).getRaster().getDataBuffer()).getData();
    }

    /**
     * Converts a RGB image to a gray scaled image.
     * @param image The image to convert
     **/
    public static BufferedImage convertRGBtoGray(Image image)
    {
        // Converts the Image to a Buffered image
        BufferedImage bfImage = convertImageToBufferedImage(image);

        // Gets the main color of the image
        int mainColor = getMainColor(bfImage);

        // For every pixels of the image
        for(int x = 0; x < bfImage.getWidth(null); x++)
        {
            for(int y = 0; y < bfImage.getHeight(null); y++)
            {
                // get the pixel
                int pixel = bfImage.getRGB(x, y);

                // If the pixel is not the main color, compute its gray value
                if(pixel != mainColor)
                {
                    int gray = getGray(pixel);
                    bfImage.setRGB(x, y, gray);
                }

                // If the pixel is the main color, set it to black
                else
                {
                    bfImage.setRGB(x, y, 0xFF000000);
                }
            }
        }
        return bfImage;
    }

    /**
     * Returns the main color of an image.
     * @param image The image to find the main color of
     * @return An integer representing the main color
     **/
    private static int getMainColor(BufferedImage image)
    {
        // Counts how many time each colors appear
        ColorOccurence co[] = new ColorOccurence[10000];

        // For every pixel of the image
        for(int x = 0; x < image.getWidth(null) / 2; x++)
        {
            for(int y = 0; y < image.getHeight(null) / 2; y++)
            {
                // Get the pixel
                int pixel = image.getRGB(x, y);

                // Parse every element initialized of the array
                for(int i = 0; i < 10000; i++)
                {
                    // If the element is null, a new color has been found
                    if(co[i] == null)
                    {
                        // Creates a new color and sets its occurence to 1
                        co[i] = new ColorOccurence(pixel, 1);
                        break;
                    }

                    // If the color is already is already in the array, add one
                    if(co[i].color == pixel){
                        co[i].occurence += 1;
                        break;
                    }
                }
            }
        }

        // Maximum number of occurence
        int max = 0;

        // The color that appeared the most so far
        int maxVal = 0;

        // For every color
        for(ColorOccurence c : co)
        {
            // If null, the whole array has been parsed
            if(c == null)
            {
                break;
            }

            // If this color occurs more times than the previous most appearing
            // color, set it as the new most appearing color
            if(c.occurence > max)
            {
                max = c.occurence;
                maxVal = c.color;
            }
        }
        return maxVal;
    }

    /**
     * Converts a RGB color to a gray color. To do that it simply computes
     * the highest value of R G B and sets it as the shade of gray. This is not
     * an actual conversion to a gray scale, the idea is simply to change a
     * colored digit to a gray digit, therefore 255 in any component means it
     * should be represented as white.
     * @param pixel The pixel to process
     * @return An integer representing the nex pixel
     **/
    private static int getGray(int pixel)
    {
        /*
        // Extract r, g, b components of the hex value
        int r = (pixel >> 16) - 0xFFFFFF00;
        int g = (pixel >> 8) - (0xFFFF0000 | (r << 8));
        int b = (pixel) - (0xFF000000 | (r << 16) | (g << 8));

        // Get the max of the three components
        int gray = maxVal(maxVal(r, g), b);

        // Return a fully gray pixel
        return 0xFF000000 | (gray << 16) | (gray << 8) | gray;
        */
        return 0xFFFFFFFF;
    }
}
