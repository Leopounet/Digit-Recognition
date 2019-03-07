package com.image;

import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ColoredPoint
{
    //
    public int x;
    public int y;
    public int c;
    public ColoredPoint(int x, int y, int c)
    {
        this.x = x;
        this.y = y;
        this.c = c;
    }
}
