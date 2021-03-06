package com.labeldistance;

/**
 * Class to store a label associated to a probability. Acts as a structure in C.
 **/
public class LabelDistance
{
    // Label of the current image
    public int p_label = -1;

    // Euclidian distance to the image uploaded
    public double p_distance = -1;

    /**
     * Creates a new object that can store a label and the euclidian distance
     * between the image and the uploaded image.
     * @param label The label of the image
     **/
    public LabelDistance(double distance, int label)
    {
        p_label = label;
        p_distance = distance;
    }
}
