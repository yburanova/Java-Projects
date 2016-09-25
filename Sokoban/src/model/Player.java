/*
 * Copyright (c) Yulia Buranova
 */

package model;

import java.awt.*;

/**
 * The class Player is used for the player representation in the game
 * Created by yulia on 25.09.16.
 */
public class Player extends CollisionObject implements Movable
{
    public Player(int x, int y)
    {
        super(x, y);
    }

    /**
     * draw the player on the gaming field
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.GREEN);

        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;

        graphics.drawOval(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        graphics.fillOval(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
    }

    /**
     * moves the player
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
