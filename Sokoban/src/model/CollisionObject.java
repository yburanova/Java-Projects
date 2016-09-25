/*
 * Copyright (c) Yulia Buranova
 */

package model;

/**
 * The class CollisionObject is used for a designation of the objects,
 * which can collide (i.e. Wall & Player)
 * @author yulia
 * @version 1.0
 * Created by yulia on 25.09.16.
 */
public abstract class CollisionObject extends GameObject
{
    public CollisionObject(int x, int y)
    {
        super(x, y);
    }

    /**
     * returns true if two object collided
     * @param gameObject other object
     * @param direction direction of our motion
     * @return
     */
    public boolean isCollision(GameObject gameObject, Direction direction)
    {
        if (direction.equals(Direction.LEFT))
        {
            if ((getX() - Model.FIELD_SELL_SIZE) == gameObject.getX() && getY() == gameObject.getY())
                return true;
        }
        else if (direction.equals(Direction.RIGHT))
        {
            if ((getX() + Model.FIELD_SELL_SIZE) == gameObject.getX() && getY() == gameObject.getY())
                return true;
        }
        else if (direction.equals(Direction.UP))
        {
            if (getX() == gameObject.getX() && (getY() - Model.FIELD_SELL_SIZE) == gameObject.getY())
                return true;
        }
        else if (direction.equals(Direction.DOWN))
        {
            if (getX() == gameObject.getX() && (getY() + Model.FIELD_SELL_SIZE) == gameObject.getY())
                return true;
        }

        return false;
    }
}
