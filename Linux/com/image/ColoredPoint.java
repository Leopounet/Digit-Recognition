package com.image;

/**
 * Class to represent a colored point.
 */
public class ColoredPoint
{
    // Point coordinates
    public int x = 0;
    public int y = 0;

    // Color of the point
    public int c = 0;

    /**
     * Creates a new ColoredPoint
     * @param x	X coord of the point
     * @param y Y coord of the point
     * @param c Color of the point
     **/
    public ColoredPoint(int x, int y, int c)
    {
        this.x = x;
        this.y = y;
        this.c = c;
    }
}
