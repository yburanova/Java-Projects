/*
 * Copyright (c) Yulia Buranova
 */

package controller;

import model.Direction;

/**
 * The interface EventListener is used for events listening
 * @author yulia
 * @version 1.0
 * Created by yulia on 25.09.16.
 */

public interface EventListener
{
    /**
     * moves an object to the chosen direction
     * @param direction
     */
    void move(Direction direction);

    /**
     * restarts the current level
     */
    void restart();

    /**
     * starts next level
     */
    void startNextLevel();

    /**
     * informs than the current level is completed
     * @param level
     */
    void levelCompleted(int level);


}
