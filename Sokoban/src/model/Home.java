/*
 * Copyright (c) Yulia Buranova
 */

package model;

import java.awt.*;

/**
 * The class Home is used for a representation of the "home" - a field
 *  that can not move and collide with other objects
 * Created by yulia on 25.09.16.
 */
public class Home extends GameObject
{
    public Home(int x, int y)
    {
        super(x, y);
        setWidth(2);
        setHeight(2);
    }

    /**
     * draws a home on the game field
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.RED);

        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;


        graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        graphics.fillRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
    }
}
