package com.field;

import java.awt.*;

import com.message.*;

/**
 * Represents the bottom field of this window.
 **/
public class BottomField extends Field
{
	private static final long serialVersionUID = 1L;
	
    // The messages to print
    private Message p_messages[] = null;

    // The maximum number of messages displayable at once
    private int p_maxMessages = 4;

    /**
     * Creates the bottom field.
     * @param width The width of the newly created field in pixel
     * @param height The height of the newly created field in pixel
     **/
    public BottomField(int width, int height)
    {
        super(width, height);
        init();
    }

    /**
     * Creates the bottom field.
     * @param size The size of the newly created field
     **/
    public BottomField(Dimension size)
    {
        super(size);
        init();
    }

    /**
     * Initializes the current object.
     **/
    private void init()
    {
        initField(Color.DARK_GRAY);
        p_messages = new Message[p_maxMessages];
        resetMessages();
    }

    /**
     * Prints the given string.
     * @param message The string to print
     * @param c The color of the text
     * @param line The line the text should be displayed at (0, 1, 2, 3)
     **/
    public void printMessage(String message, Color c, int line)
    {
        p_messages[line] = new Message(message, c);
        repaint();
    }

    /**
     * Overrides the paintComponent method.
     * @param g The graphic component to use to draw.
     **/
    public void paintComponent(Graphics g)
    {
        // Super of the paintComponent constructor
        super.paintComponent(g);

        int line = 0;
        for(Message m : p_messages)
        {
        	if(m == null)
        	{
        		continue;
        	}
        		
        	g.setFont(new Font("default", Font.BOLD, 14));
            g.setColor(m.p_color);

            // Prints the message in black
            g.drawString(m.p_message, 5, 20 * (line + 1));

            line++;
        }
    }

    /**
     * Sets every message to the empty string.
     **/
    public void resetMessages()
    {
        for(int i = 0; i < p_maxMessages; i++)
        {
            p_messages[i] = new Message("", Color.BLACK);
        }
    }
}
