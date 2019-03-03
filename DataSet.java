import java.io.*;

public class DataSet
{
    // Input stream of images
    private FileInputStream p_imagesStream = null;

    // Input stream of labels
    private FileInputStream p_labelsStream = null;

    // Start code of images/labels
    private int p_startCodeImages = 0;
    private int p_startCodeLabels = 0;

    // Number of images/labels (should be equal)
    private int p_nbImages = 0;
    private int p_nbLabels = 0;

    // Number of rows in the image dataset
    private int p_nbRows = 0;

    // Number of columns in the image dataset
    private int p_nbColumns = 0;

    // The list of images
    private int p_listImages[][] = null;

    // The list of labels
    private int p_listLabels[] = null;

    /**
     * Creates a new DataSet objects and reads the given files.
     **/
    public DataSet(String imagesPath, String labelsPath)
    {
        try
        {
            // Opens the given files
            p_imagesStream = new FileInputStream(new File(imagesPath));
            p_labelsStream = new FileInputStream(new File(labelsPath));

            // Gets the starting code of each files
            p_startCodeImages = getNextInt(p_imagesStream);
            p_startCodeLabels = getNextInt(p_labelsStream);

            // Gets the size of both files
            p_nbImages = getNextInt(p_imagesStream);
            p_nbLabels = getNextInt(p_labelsStream);
            p_nbImages = 1;
            p_nbLabels = 1;

            // Gets the number of rows and columns in the images file
            p_nbRows = getNextInt(p_imagesStream);
            p_nbColumns = getNextInt(p_imagesStream);

            // Sets the size of labels and images arrays
            p_listImages = new int[p_nbImages][p_nbRows * p_nbColumns];
            p_listLabels = new int[p_nbLabels];

            // Reads and stores every labels and images
            for(int index = 0; index < p_nbImages; index++)
            {
                getNextImage(p_imagesStream, index);
                getNextLabel(p_labelsStream, index);
            }
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
            System.out.println(p_startCodeImages + " " + p_startCodeLabels);
            System.out.println(p_nbImages + " " + p_nbLabels);
            System.out.println(p_nbRows + " " + p_nbColumns);
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
     * Adds the next image to the list of images
     * @param fis The file to parse
     **/
    private void getNextImage(FileInputStream fis, int nbImage) throws IOException
    {
        for(int x = 0; x < p_nbColumns; x++)
        {
            for(int y = 0; y < p_nbRows; y++)
            {
                p_listImages[nbImage][x * p_nbColumns + y] = getNextPixel(fis);
            }
        }
    }

    /**
     * Adds the next label to the list of labels
     * @param fis The file to parse
     **/
    private void getNextLabel(FileInputStream fis, int nbLabel) throws IOException
    {
        p_listLabels[nbLabel] = fis.read();
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
}
