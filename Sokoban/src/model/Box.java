/*
 * Copyright (c) Yulia Buranova
 */

package model;

import java.awt.*;

/**
 * The class Box is used for a box represenation in the game
 * Created by yulia on 25.09.16.
 */
public class Box extends CollisionObject implements Movable
{
    public Box(int x, int y)
    {
        super(x, y);
    }

    /**
     * draw a box to the gaming field
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.BLUE);
        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;

        graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        graphics.fillRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
    }

    /**
     * moves the box
     * @param x
     * @param y
     */
    @Override
    public void move(int x, int y)
    {
        setX(getX() + x);
        setY(getY() + y);
    }
}
