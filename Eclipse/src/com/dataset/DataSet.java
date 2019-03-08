package com.dataset;

import java.io.IOException;
import java.awt.*;
import java.io.*;
import java.util.Arrays;

import com.labeldistance.*;
import com.window.*;
import com.image.*;

/**
 * Stores the data set and computes the probabilities.
 **/
public class DataSet
{
    // The loading window to display while loading the data
    private LoadingWindow p_loadingWindow = null;

    // Input stream of images
    private FileInputStream p_imagesStream = null;

    // Input stream of labels
    private FileInputStream p_labelsStream = null;

    // Number of images/labels (should be equal)
    private int p_nbImages = 0;
    private int p_nbLabels = 0;

    // Number of rows and columns in the image dataset
    private int p_nbRows = 0;
    private int p_nbColumns = 0;

    // The list of images and labels
    private int p_listImages[][] = null;
    private int p_listLabels[] = null;

    // List of all euclidian distances and labels
    private LabelDistance p_labelDistance[] = null;

    // Current image to analyze
    private int p_currentImage[] = null;

    // array containing the probabily of the number being te one drawn
    // index 0 --> probability that 0 has been drawn
    // index 1 --> probability that 1 has been drawn
    // ...
    private double p_probabilities[] = null;

    // K value for the kNN algorithm
    private int p_kValue = 0;

    /**
     * Creates a new DataSet objects and reads the given files.
     * @param imagesPath Path to the training images
     * @param labelsPath Path to the labels of the training images
     **/
    public DataSet(String imagesPath, String labelsPath)
    {
        try
        {
            // Opens the given files
            p_imagesStream = new FileInputStream(new File(imagesPath));
            p_labelsStream = new FileInputStream(new File(labelsPath));

            // Gets the starting code of each files
            getNextInt(p_imagesStream);
            getNextInt(p_labelsStream);

            // Gets the size of both files
            p_nbImages = getNextInt(p_imagesStream);
            p_nbLabels = getNextInt(p_labelsStream);
            //p_nbImages = 1;
            //p_nbLabels = 1;

            // Sets the kValue in function of the number of images
            p_kValue = (int)Math.sqrt((double)p_nbImages);

            // Gets the number of rows and columns in the images file
            p_nbRows = getNextInt(p_imagesStream);
            p_nbColumns = getNextInt(p_imagesStream);

            // Sets the size of labels and images arrays
            p_listImages = new int[p_nbImages][p_nbRows * p_nbColumns];
            p_listLabels = new int[p_nbLabels];
            p_labelDistance = new LabelDistance[p_nbLabels];

            // Initializes the array of probabilities
            p_probabilities = new double[10];

            // Creates the loading window
            p_loadingWindow = new LoadingWindow(new Dimension(1000, 300));

            // Reads and stores every labels and images
            for(int index = 0; index < p_nbImages; index++)
            {
                getNextImage(p_imagesStream, index);
                getNextLabel(p_labelsStream, index);
                p_labelDistance[index] = new LabelDistance(0, 0);
                p_loadingWindow.setProgression((double)(index) / (double)(p_nbImages));
            }

            // Closes the loading window
            p_loadingWindow.closeWindow();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                // Closes the input streams
                if(p_imagesStream != null)
                {
                    p_imagesStream.close();
                }

                if(p_labelsStream != null)
                {
                    p_labelsStream.close();
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the next int of the given file.
     * @param fis The file to parse
     * @return An integer corresponding to the next integer of the given file
     **/
    private int getNextInt(FileInputStream fis) throws IOException
    {
        return (fis.read() << 24) |
                (fis.read() << 16) |
                (fis.read() << 8) |
                (fis.read());
    }

    /**
     * Adds the next image to the list of images.
     * @param fis The file to parse
     * @param nbImage The current image being processed
     **/
    private void getNextImage(FileInputStream fis, int nbImage) throws IOException
    {
        // Loops through every pixels
        for(int y = 0; y < p_nbRows; y++)
        {
            for(int x = 0; x < p_nbColumns; x++)
            {
                // Stores each pixels
                p_listImages[nbImage][y * p_nbRows + x] = getNextPixel(fis);
            }
        }
    }

    /**
     * Returns the next pixel of the given file.
     * @param fis The file to parse
     * @return A integer corresponding to the next pixel of the given file
     **/
    private int getNextPixel(FileInputStream fis) throws IOException
    {
        int grayValue = fis.read();
        return 0xFF000000 |
                (grayValue << 16) |
                (grayValue << 8) |
                (grayValue);
    }

    /**
     * Adds the next label to the list of labels.
     * @param fis The file to parse
     * @param nbLabel The current index of the label being processed
     **/
    private void getNextLabel(FileInputStream fis, int nbLabel) throws IOException
    {
        p_listLabels[nbLabel] = fis.read();
    }

    /**
     * Computes the probabilities ofeach possible numbers.
     * @param image The image to process
     * @return the probablities of each number.
     **/
    public double[] computeProbabilities(Image image)
    {
        // Converts the image to an array of ints
        p_currentImage = ImageProcessing.convertImageToIntArray(image);
        return hComputeProbabilities();
    }

    /**
     * Computes the probabilities ofeach possible numbers.
     * @param image Array on ints representing an image
     * @return the probablities of each number.
     **/
    public double[] computeProbabilities(int image[])
    {
        p_currentImage = image;
        return hComputeProbabilities();
    }

    /**
     * Hidden version of compute probabilities. (common part of both overloaded
     * methods)
     * @return the probablities of each number.
     **/
    private double[] hComputeProbabilities()
    {
        // Compute the distance between the uploaded image and all the training sample
        computeDistances();

        // Sets all probabilities to 0
        p_probabilities = new double[10];

        // For 0 to K, count the number of each digits
        for(int index = 0; index < p_kValue; index++)
        {
            p_probabilities[p_labelDistance[index].p_label]++;
        }

        // Computes the confidence for every values
        for(int index = 0; index < 10; index++)
        {
            p_probabilities[index] /= p_kValue;
        }
        return p_probabilities;
    }

    /**
     * Computes the euclidian distance between the given image and every training
     * images.
     **/
    private void computeDistances()
    {
        // For every images in the training set, compute the euclidian distance
        // to the uploaded image
        for(int index = 0; index < p_nbLabels; index++)
        {
            p_labelDistance[index] = computeDistance(index);
        }

        // Sorts the euclidian distances
        Arrays.sort(p_labelDistance, new LabelDistanceCompare());
    }

    /**
     * Compute the distance between two images.
     * @param index The index of the image to compare
     **/
    private LabelDistance computeDistance(int index)
    {
        // Var to store the distance
        double distance = 0;

        // For every pixels in the image, compute the distance squared
        for(int pixel = 0; pixel < p_nbRows *  p_nbColumns; pixel++)
        {
            distance += Math.pow(p_listImages[index][pixel] - p_currentImage[pixel], 2);
        }

        // Creates a new LabelDistance to associate a distance to a label
        LabelDistance ld = new LabelDistance(Math.sqrt(distance), p_listLabels[index]);
        return ld;
    }

    /**
     * Returns the array corresponding to the demanded image.
     * @param index The index of the image to get
     * @return An array of integers representing the image
     **/
    public int[] getImage(int index)
    {
        return p_listImages[index];
    }

    /**
     * Returns the label of the corresponding image.
     * @param index The index of the label to get
     * @return The label of the image at the given index
     **/
    public int getLabel(int index)
    {
        return p_listLabels[index];
    }

    /**
     * Returns the number of pixel on a row in a Image.
     * @return An integer representing the number of pixels on the row of and image.
     **/
    public int getNbPixelRows()
    {
        return p_nbRows;
    }

    /**
     * Returns the number of pixel on a column in a Image.
     * @return An integer representing the number of pixels on the column of and image.
     **/
    public int getNbPixelColumns()
    {
        return p_nbColumns;
    }

    /**
     * Returns the probabilities of each digits.
     * @return Returns an array of doubles corresponding to the probabilities per digits.
     **/
    public double[] getProbabilities()
    {
        return p_probabilities;
    }

    /**
     * Sets the K value.
     * @param k The new k to use
     **/
    public void setK(int k)
    {
        p_kValue = k;
    }

    /**
     * Returns the number of images in the dataset.
     * @return An int corresponding to the number of images
     **/
    public int getNbImages()
    {
        return p_nbImages;
    }
}
