package com.window;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

import com.dataset.*;
import com.field.*;

public class Window extends JFrame
{
    // Used to store the width and height of the window in pixels
    private Dimension p_windowSize = null;

    // Pane of the window
    private ContentPane p_contentPane = null;

    // The training dataset
    private DataSet p_dataSet = null;

    // Path to the training sets
    private static String trainingImagesPath = "../data/train-images.idx3-ubyte";
    private static String trainingLabelsPath = "../data/train-labels.idx1-ubyte";

    // Path to the tests sets
    private static String testImagesPath = "../data/t10k-images.idx3-ubyte";
    private static String testLabelsPath = "../data/t10k-labels.idx1-ubyte";

    // Use to browse files
    private JFileChooser p_fc = null;

    // Current directory of the user
    private File p_directory = new File(System.getProperty("user.dir"));

    // Current uploaded image
    private Image p_uploadedImage = null;


    /**
     * Creates a new window object.
     * @param ds The dataset to use
     * @param width Width in pixels of the newly created window
     * @param height Height in pixels of the newly created window
     **/
    public Window(int width, int height)
    {
        super();
        init(new Dimension(width, height));
    }

    /**
     * Creates a new window object.
     * @param ds The dataset to use
     * @param size The size of the window in pixels (width, height) format
     **/
    public Window(Dimension size)
    {
        super();
        init(size);
    }

    /**
     * Initializes the object.
     * @param size The size of the window
     **/
    private void init(Dimension size)
    {
        // Set th size of the window
        p_windowSize = size;

        // Creates a new data set
        p_dataSet = new DataSet(trainingImagesPath, trainingLabelsPath);;

        // Create a new file browser
        p_fc = new JFileChooser();

        // Initializes the window
        initWindow();

        // Creates the different fields
        createFields();
    }

    /**
     * Sets the default parameters of the window and displays it. Also adds the
     * file chooser.
     **/
    private void initWindow()
    {
        // Centers the window
        this.setLocationRelativeTo(null);

        // Closing the window stops the program
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sets the size of the window
        this.setSize(p_windowSize);

        // Makes the window fullscreen by default
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Makes the window visible
        this.setVisible(true);
    }

    /**
     * Creates the different fields of the window. The window is devided in four
     * different fields. See the documentation for more infos.
     **/
    private void createFields()
    {
        // Create and add the content pane of the JFrame
        p_contentPane = new ContentPane(p_windowSize);

        // Add the window to the list of listener of buttons of the top right field
        p_contentPane.getTRField().getUploadButton().addActionListener(new UploadButtonListener());
        p_contentPane.getTRField().getSubmitButton().addActionListener(new SubmitButtonListener());

        // Sets the content pane
        this.setContentPane(p_contentPane);
        printMessage("Success : Everyting has been succesfullyy loaded!", Color.GREEN, 0, true);
    }

     /**
      * Action listener of the upload button.
      **/
     public class UploadButtonListener implements ActionListener
     {
         /**
          * Modifies the window when the upload button is clicked.
          * @param e The event information
          **/
         public void actionPerformed(ActionEvent e)
         {
             processImage();
         }
     }

     /**
      * Action listener of the submit button.
      **/
     public class SubmitButtonListener implements ActionListener
     {
         /**
          * Modifies the window when the upload button is clicked.
          * @param e The event information
          **/
         public void actionPerformed(ActionEvent e)
         {
             processProbabilities();
         }
     }

     /**
      * Processes the image and draws it on the top left field.
      **/
     private void processImage()
     {
         // Opens the current directory
         p_fc.setCurrentDirectory(p_directory);

         // Computes if the file is accessible
         int returnVal = p_fc.showOpenDialog(Window.this);
         if (returnVal == JFileChooser.APPROVE_OPTION)
         {
             // Get the selected file
             File file = p_fc.getSelectedFile();

             if(file == null)
             {
                 printMessage("Error : No file given!", Color.RED, 0, true);
                 return;
             }

             // Displays the image
             p_uploadedImage = p_contentPane.getTLField().displayImage(file.getAbsolutePath());

             if(p_uploadedImage == null)
             {
                 printMessage("Error : Image could not be loaded!", Color.RED, 0, true);
                 return;
             }
         }
         else
         {
             printMessage("Error : File is unreachable or access has been denied or no file has been chosen!", Color.RED, 0, true);
             return;
         }
         printMessage("Success : Image successfully loaded!", Color.GREEN, 0, true);
         printMessage("Success : Press submit to find out which digit is maybe drawn...", Color.BLUE, 1, false);
     }

     /**
      * Computes and displays the probability of each digits.
      **/
     private void processProbabilities()
     {
         // If there is an image
         if(p_uploadedImage != null)
         {
             // Rescales the image to 28 * 28
             p_uploadedImage = p_uploadedImage.getScaledInstance(p_dataSet.getNbPixelRows(),
                                                                 p_dataSet.getNbPixelColumns(),
                                                                 Image.SCALE_DEFAULT);

             if(p_uploadedImage != null)
             {
                 // Draw the diagram
                 double prob[] = p_dataSet.computeProbabilities(p_uploadedImage);
                 p_contentPane.getMField().drawDiagram(prob);
                 int max = maxIndex(prob);

                 printMessage("Success : Diagram has been computed!", Color.GREEN, 0, true);
                 printMessage(String.format("The digit drawn is probably a %d (confidence = %d%%)", max,
                                                                                                    (int)(prob[max] * 100)),
                                                                                                    Color.GREEN,
                                                                                                    1,
                                                                                                    false);
             }
             else
             {
                 printMessage("Error : Image couldn't be resize!", Color.RED, 0, true);
             }
         }
         else
         {
             printMessage("Error : No image loaded!", Color.RED, 0, true);
         }
     }

     /**
      * Sends a message to print to the bottom field.
      * @param message The message to print
      * @param c The color of the message to print
      * @param line The line to print the message at (starts at 0)
      * @param reset If true, deleted every previous messages
      **/
     private void printMessage(String message, Color c, int line, boolean reset)
     {
         // Removes every previous messages
         if(reset)
         {
             p_contentPane.getBField().resetMessages();
         }
         p_contentPane.getBField().printMessage(message, c, line);
     }

     /**
      * Returns the index of the maximum value of the array.
      * @param array The array to parse
      * @return The index of the maximum value of the array
      **/
     private int maxIndex(double array[])
     {
         int maxIndex = 0;
         double max = array[0];
         for(int i = 0; i < array.length; i++)
         {
             if(array[i] > max)
             {
                 max = array[i];
                 maxIndex = i;
             }
         }
         return maxIndex;
     }

    /**
     * Returns the size of the window.
     * @return The size of the window as a Dimension object.
     **/
    public Dimension getWindowSize()
    {
        return p_windowSize;
    }

    /**
     * Sets the size of the window.
     * @param width Width in pixels of the created window
     * @param height Height in pixels of the created window
     **/
    public void setWindowSize(int width, int height)
    {
        p_windowSize = new Dimension(width, height);
    }

    /**
     * @param size The size of the window in pixels (width, height) format
     **/
    public void setWindowSize(Dimension size)
    {
        p_windowSize = size;
    }

    /**
     * Returns the data set bound to the program.
     * @return The data set to use.
     **/
    public DataSet getDataSet()
    {
        return p_dataSet;
    }
}
