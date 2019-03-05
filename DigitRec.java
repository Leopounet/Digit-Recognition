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
        // DataSet test = new DataSet(testImagesPath, testLabelsPath);
    }
}
