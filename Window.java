import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

public class Window extends JFrame
{
    // Used to store the width and height of the window in pixels
    private Dimension p_windowSize = null;

    // Pane of the window
    private JPanel p_contentPane = null;

    // Fields sizes
    private Dimension p_topLeftFieldSize = null;
    private Dimension p_topRightFieldSize = null;
    private Dimension p_middleFieldSize = null;
    private Dimension p_bottomFieldSize = null;

    // Path to the training sets
    private String p_trainingImagesPath = "../data/train-images.idx3-ubyte";
    private String p_trainingLabelsPath = "../data/train-labels.idx1-ubyte";

    // The training dataset
    private DataSet p_dataSet = null;

    /**
     * Creates a new window object.
     * @param width Width in pixels of the newly created window
     * @param height Height in pixels of the newly created window
     **/
    public Window(int width, int height)
    {
        super();
        p_windowSize = new Dimension(width, height);
        p_dataSet = new DataSet(p_trainingImagesPath, p_trainingLabelsPath);
        initWindow();
        createFields();
    }

    /**
     * Creates a new window object.
     * @param size The size of the window in pixels (width, height) format
     **/
    public Window(Dimension size)
    {
        super();
        p_windowSize = size;
        p_dataSet = new DataSet(p_trainingImagesPath, p_trainingLabelsPath);
        initWindow();
        createFields();
    }

    /**
     * Sets the default parameters of the window and displays it.
     **/
    private void initWindow()
    {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(p_windowSize);
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
        p_topLeftFieldSize = new Dimension(8 * p_windowSize.width / 10, p_windowSize.height / 2);
        p_topRightFieldSize = new Dimension(2 * p_windowSize.width / 10, p_windowSize.height / 2);
        p_middleFieldSize = new Dimension(p_windowSize.width, 3 * p_windowSize.height / 10);
        p_bottomFieldSize = new Dimension(p_windowSize.width, 2 * p_windowSize.height / 10);

        // Creates the four main zones and add them to the content pane
        addField(new TopLeftField(this, p_topLeftFieldSize), 0, 0, 1, 1);
        addField(new TopRightField(this, p_topRightFieldSize), 1, 0, 1, 1);
        addField(new MiddleField(this, p_middleFieldSize), 0, 1, 2, 1);
        addField(new BottomField(this, p_bottomFieldSize), 0, 2, 2, 1);
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
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = x;
         gbc.gridy = y;
         gbc.gridheight = height;
         gbc.gridwidth = width;
         this.getContentPane().add(zone, gbc);
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
     * Returns the array corresponding to the demanded image.
     * @param index The index of the image to get
     * @return An array of integers representing the image
     **/
    public int[] getImage(int index)
    {
        return p_dataSet.getImage(index);
    }

    /**
     * Returns the label of the corresponding image.
     * @param index The index of the label to get
     * @return The label of the image at the given index
     **/
    public int getLabel(int index)
    {
        return p_dataSet.getLabel(index);
    }
}
