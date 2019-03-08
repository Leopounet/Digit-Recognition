package com.button;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.image.BufferedImage;

import com.pane.*;
import com.window.*;
import com.image.*;

/**
 * Called when the save button is pressed.
 **/
 public class SaveButtonListener extends ButtonListener
 {
     // List of letters and numbers
     private static String p_alphabet = new String("abcdefghijklmnopqrstuvwxyz0123456789-_");

     // Size of the random name of the image
     private static int p_nameLength = 15;

     /**
      * Saves the given image.
      * @param window The main window to use
      * @param contentPane The content pane to consider
      **/
     public static void saveImage(Image uploadedImage, MainWindow window, MainContentPane contentPane)
     {
         // If there are no image
         if(uploadedImage == null)
         {
             return;
         }
         
         // Sets the window to use
         p_window = window;

         // Sets the content pane to use
         p_contentPane = contentPane;

         // Convert Image to BufferedImage
         BufferedImage image = ImageProcessing.convertImageToBufferedImage(uploadedImage);

         String name = randomString();

         // Saves the image
         try
         {
             File outputfile = new File(name);
             ImageIO.write(image, "png", outputfile);
             printMessage("Success : Image successfully saved! (" + name + ")", Color.GREEN, 0, true);
         }
         catch (IOException e)
         {
             printMessage("Error : Could not save the image!", Color.RED, 0, true);
         }
     }

     private static String randomString()
     {

         // Builds a random name for the image (collision safe hopefully)
         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append("src/image/");

         // Chooses 15 random characters
         for(int i = 0; i < p_nameLength; i++)
         {
             int randIndex = ThreadLocalRandom.current().nextInt(0, p_alphabet.length());
             stringBuilder.append(p_alphabet.charAt(randIndex));
         }

         // Adds png to the nam of the file
         stringBuilder.append(".png");
         return stringBuilder.toString();
     }
 }
