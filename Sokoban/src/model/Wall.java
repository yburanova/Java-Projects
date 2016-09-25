/*
 * Copyright (c) Yulia Buranova
 */

package model;

import java.awt.*;

/**
 * The class Wall is used for a representation of a wall in the game
 * @author yulia
 * @version 1.0
 * Created by yulia on 25.09.16.
 */
public class Wall extends CollisionObject
{
    public Wall(int x, int y)
    {
        super(x, y);
    }

    /**
     * draw a wall sector on the game field
     * @param graphics
     */
    @Override
    public void draw(Graphics graphics)
    {
        graphics.setColor(Color.BLACK);

        int leftUpperCornerX = getX() - getWidth() / 2;
        int leftUpperCornerY = getY() - getHeight() / 2;


        graphics.drawRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
        graphics.fillRect(leftUpperCornerX, leftUpperCornerY, getWidth(), getHeight());
    }
}
