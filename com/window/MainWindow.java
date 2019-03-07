package com.window;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import com.dataset.*;
import com.field.*;
import com.button.*;
import com.pane.*;
import com.image.*;

/**
 * The main window of this program, most of the event start here.
 **/
public class MainWindow extends JFrame
{
    // Used to store the width and height of the window in pixels
    private Dimension p_windowSize = null;

    // Pane of the window
    private MainContentPane p_contentPane = null;

    // The training dataset
    private DataSet p_dataSet = null;
    private DataSet p_testSet = null;

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
    public MainWindow(int width, int height)
    {
        super();
        init(new Dimension(width, height));
    }

    /**
     * Creates a new window object.
     * @param ds The dataset to use
     * @param size The size of the window in pixels (width, height) format
     **/
    public MainWindow(Dimension size)
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
        p_dataSet = new DataSet(trainingImagesPath, trainingLabelsPath);
        p_testSet = new DataSet(testImagesPath, testLabelsPath);

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
        p_contentPane = new MainContentPane(p_windowSize);

        // Add the window to the list of listener of buttons of the top right field
        p_contentPane.getTRField().getUploadButton().addActionListener(new UploadButtonActionListener());
        p_contentPane.getTRField().getSubmitButton().addActionListener(new SubmitButtonActionListener());
        p_contentPane.getTRField().getRandomImageButton().addActionListener(new RandomImageButtonActionListener());
        p_contentPane.getTRField().getDrawButton().addActionListener(new DrawButtonActionListener());
        p_contentPane.getTRField().getSaveButton().addActionListener(new SaveButtonActionListener());

        // Sets the content pane
        this.setContentPane(p_contentPane);
        ButtonListener.printMessage(p_contentPane, "Success : Everyting has been succesfullyy loaded!", Color.GREEN, 0, true);
    }

     /**
      * Action listener of the upload button.
      **/
     public class UploadButtonActionListener implements ActionListener
     {
         /**
          * Modifies the window when the upload button is clicked.
          * @param e The event information
          **/
         public void actionPerformed(ActionEvent e)
         {
             File tmp = UploadButtonListener.chooseFile(MainWindow.this, p_contentPane);
             if(tmp != null)
             {
                 p_uploadedImage = UploadButtonListener.getImage(MainWindow.this, p_contentPane, tmp);
                 UploadButtonListener.displayImage(MainWindow.this, p_contentPane, p_uploadedImage);
             }
         }
     }

     /**
      * Action listener of the submit button.
      **/
     public class SubmitButtonActionListener implements ActionListener
     {
         /**
          * Modifies the window when the upload button is clicked.
          * @param e The event information
          **/
         public void actionPerformed(ActionEvent e)
         {
             SubmitButtonListener.processProbabilities(p_uploadedImage, MainWindow.this, p_contentPane);
         }
     }

     /**
      * Action listener of the random image button.
      **/
     public class RandomImageButtonActionListener implements ActionListener
     {
         /**
          * Modifies the window when the upload button is clicked.
          * @param e The event information
          **/
         public void actionPerformed(ActionEvent e)
         {
             p_uploadedImage = RandomImageButtonListener.getRandomImage(MainWindow.this, p_contentPane);
             UploadButtonListener.displayImage(MainWindow.this, p_contentPane, p_uploadedImage);
         }
     }

     /**
      * Action listener of the draw button.
      **/
     public class DrawButtonActionListener implements ActionListener
     {
         /**
          * Modifies the window when the upload button is clicked.
          * @param e The event information
          **/
         public void actionPerformed(ActionEvent e)
         {
             DrawButtonListener.drawImage(MainWindow.this, p_contentPane);
         }
     }

     /**
      * Action listener of the save button.
      **/
     public class SaveButtonActionListener implements ActionListener
     {
         /**
          * Modifies the window when the upload button is clicked.
          * @param e The event information
          **/
         public void actionPerformed(ActionEvent e)
         {
             SaveButtonListener.saveImage(p_uploadedImage, MainWindow.this, p_contentPane);
         }
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
     * @return The data set to use
     **/
    public DataSet getDataSet()
    {
        return p_dataSet;
    }

    /**
     * Returns the test set.
     * @return The test set
     **/
    public DataSet getTestSet()
    {
        return p_testSet;
    }

    /**
     * Returns the current directory.
     * @return A File corresponding the current directory
     **/
    public File getCurrentDirectory()
    {
        return p_directory;
    }

    /**
     * Sets the uploaded image.
     * @param image The image to set
     **/
    public void setImage(BufferedImage image)
    {
        // Centers the digit and set the image
        p_uploadedImage = ImageProcessing.centerDigit(image);

        // wait 0.5 sec (to let the window appear correctly)
        try
        {
            Thread.sleep(500);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        // Display the image
        UploadButtonListener.displayImage(MainWindow.this, p_contentPane, p_uploadedImage);
        ButtonListener.printMessage(p_contentPane, "Success : Image successfully loaded!", Color.GREEN, 0, true);
        ButtonListener.printMessage(p_contentPane, "Success : Press submit to find out which digit is maybe drawn...", Color.BLUE, 1, false);
    }
}
