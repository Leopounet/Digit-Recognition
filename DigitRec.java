import com.dataset.*;
import com.window.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Launches the software.
 **/
public class DigitRec
{
    // Path to the training sets
    private static String trainingImagesPath = "../data/train-images.idx3-ubyte";
    private static String trainingLabelsPath = "../data/train-labels.idx1-ubyte";

    // Path to the tests sets
    private static String testImagesPath = "../data/t10k-images.idx3-ubyte";
    private static String testLabelsPath = "../data/t10k-labels.idx1-ubyte";

    public static void main(String args[])
    {
        // Training data set
        DataSet training = new DataSet(trainingImagesPath, trainingLabelsPath);

        // Main window
        Window window = new Window(training, 800, 800);

        // Training to find the best K possible
        /*DataSet test = new DataSet(testImagesPath, testLabelsPath);

        for(int image = 0; image < test.getNbImages(); image++)
        {
            double prob[] = training.computeProbabilities(test.getImage(image));
            int max = maxIndex(prob);
            if(max == test.getLabel(image))
            {
                System.out.println("Got " + test.getLabel(image) + " right!");
            }
            else
            {
                System.out.println("Got " + test.getLabel(image) + " wrong! Expected " + test.getLabel(image) + " gave " + max);
            }
        }*/
    }

    public static int maxIndex(double array[])
    {
        int max = 0;
        double maxValue = array[0];
        for(int i = 0; i < array.length; i++)
        {
            if(array[i] > maxValue)
            {
                maxValue = array[i];
                max = i;
            }
        }
        return max;
    }
}
