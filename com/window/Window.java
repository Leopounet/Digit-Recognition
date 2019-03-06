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
    private JPanel p_contentPane = null;

    // The training dataset
    private DataSet p_dataSet = null;

    // Fields on the window
    private TopLeftField p_tlField = null;
    private TopRightField p_trField = null;
    private MiddleField p_mField = null;
    private BottomField p_bField = null;

    // Fields sizes
    private Dimension p_topLeftFieldSize = null;
    private Dimension p_topRightFieldSize = null;
    private Dimension p_middleFieldSize = null;
    private Dimension p_bottomFieldSize = null;

    // Use to browse files
    private JFileChooser p_fc = null;

    // Current directory of the user
    File p_directory = new File(System.getProperty("user.dir"));

    // Current uploaded image
    private Image p_uploadedImage = null;


    /**
     * Creates a new window object.
     * @param ds The dataset to use
     * @param width Width in pixels of the newly created window
     * @param height Height in pixels of the newly created window
     **/
    public Window(DataSet ds, int width, int height)
    {
        super();
        init(ds, new Dimension(width, height));
    }

    /**
     * Creates a new window object.
     * @param ds The dataset to use
     * @param size The size of the window in pixels (width, height) format
     **/
    public Window(DataSet ds, Dimension size)
    {
        super();
        init(ds, size);
    }

    /**
     * Initializes the object.
     * @param size The size of the window
     **/
    private void init(DataSet ds, Dimension size)
    {
        // Set th size of the window
        p_windowSize = size;

        // Creates a new data set
        p_dataSet = ds;

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
        p_contentPane = new JPanel(new GridBagLayout());
        p_contentPane.setPreferredSize(p_windowSize);
        this.setContentPane(p_contentPane);

        // Sets the sizes of the different fields
        // These sizes have been choosen arbitrarly
        p_topLeftFieldSize = new Dimension(8 * p_windowSize.width / 10, p_windowSize.height / 2);
        p_topRightFieldSize = new Dimension(2 * p_windowSize.width / 10, p_windowSize.height / 2);
        p_middleFieldSize = new Dimension(p_windowSize.width, 4 * p_windowSize.height / 10);
        p_bottomFieldSize = new Dimension(p_windowSize.width, 1 * p_windowSize.height / 10);

        // Creates the four main zones
        p_tlField = new TopLeftField(p_topLeftFieldSize);
        p_trField = new TopRightField(p_topRightFieldSize);
        p_mField = new MiddleField(p_middleFieldSize);
        p_bField = new BottomField(p_bottomFieldSize);

        // Add the window to the list of listener of buttons of the top right field
        p_trField.getUploadButton().addActionListener(new UploadButtonListener());
        p_trField.getSubmitButton().addActionListener(new SubmitButtonListener());

        // Adds them to the content pane
        addField(p_tlField, 0, 0, 1, 1);
        addField(p_trField, 1, 0, 1, 1);
        addField(p_mField, 0, 1, 2, 1);
        addField(p_bField, 0, 2, 2, 1);
    }

    /**
     * Adds a field to the content pane.
     * @param zone The zone to add
     * @param x The position of the field on the current row (in "field" unit)
     * @param y The position of the field on the current column (in "field" unit)
     * @param width The width of the field (in "field" unit)
     * @param height The height of the field (in "field" unit)
     **/
     private void addField(JPanel zone, int x, int y, int width, int height)
     {
         // Sets the gridbagconstraints so that the field is placed correctly
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = x;
         gbc.gridy = y;
         gbc.gridheight = height;
         gbc.gridwidth = width;

         // Adds the field at the right position
         this.getContentPane().add(zone, gbc);
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

             // Displays the image
             p_uploadedImage = p_tlField.displayImage(file.getAbsolutePath());
         }
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

             // Draw the diagram
             p_mField.drawDiagram(p_dataSet.computeProbabilities(p_uploadedImage));
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
     * @return The data set to use.
     **/
    public DataSet getDataSet()
    {
        return p_dataSet;
    }
}
