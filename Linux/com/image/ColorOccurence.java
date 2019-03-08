package com.image;

/**
 * A class to count how many time a color appears.
 **/
public class ColorOccurence
{
    // The color to consider
    public int color = 0;

    // The number of timed it has been found
    public int occurence = 0;

    /**
     * Creates a new color occurence object.
     * @param color The color to consider
     * @param occurence the number of time it has appeared
     **/
    public ColorOccurence(int color, int occurence)
    {
        this.color = color;
        this.occurence = occurence;
    }
}
