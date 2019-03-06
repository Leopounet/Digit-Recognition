package com.message;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;

/**
 * Stroes a message and a color. Equivalent of a structure.
 **/
public class Message
{
    // The message to store
    public String p_message = null;

    // The color to use when displaying the message
    public Color p_color = null;

    /**
     * Creates a new message.
     * @param message The message to store
     * @param c The color to store
     **/
    public Message(String message, Color c)
    {
        p_message = message;
        p_color = c;
    }
}
