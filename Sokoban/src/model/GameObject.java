/*
 * Copyright (c) Yulia Buranova
 */

package model;

import java.awt.*;

/**
 * The GameObject class is used for an abstract representation of an interaction of different game objects,
 * @author yulia
 * @version 1.0
 * Created by yulia on 25.09.16.
 */
public abstract class GameObject
{
    // position and size
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * @param x position X of the object
     * @param y position Y of the object
     *  width & height of the object equal Model.FIELD_SELL_SIZE
     */
    public GameObject(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.width = Model.FIELD_SELL_SIZE;
        this.height = Model.FIELD_SELL_SIZE;
    }

    public GameObject(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * draws a game object to graphics
     * @param graphics
     */
    public abstract void draw(Graphics graphics);
}
