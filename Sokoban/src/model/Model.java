/*
 * Copyright (c) Yulia Buranova
 */

package model;

import controller.EventListener;

import java.nio.file.Paths;

/**
 * The Model class is used for the game logic representation
 * Created by yulia on 24.09.16.
 */
public class Model
{
    // game field size
    public static final int FIELD_SELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;

    private LevelLoader levelLoader = new LevelLoader(Paths.get("/home/yulia/Work/IntelliJ IDEA/Projects/JavaRushHomeWork/src/com/javarush/test/level34/lesson15/big01/res/levels.txt"));

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects()
    {
        return gameObjects;
    }

    public void restartLevel(int level)
    {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart()
    {
        restartLevel(currentLevel);
    }

    public void startNextLevel()
    {
        currentLevel++;
        restartLevel(currentLevel);
    }

    public void move(Direction direction)
    {
        Player player = gameObjects.getPlayer();

        if (checkWallCollision(player, direction))
            return;

        if (checkBoxCollision(direction))
            return;

        switch (direction)
        {
            case LEFT:
                player.move(-FIELD_SELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_SELL_SIZE, 0);
                break;
            case UP:
                player.move(0, -FIELD_SELL_SIZE);
                break;
            case DOWN:
                player.move(0, FIELD_SELL_SIZE);
                break;
        }

        checkCompletion();

    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction)
    {
        for(Wall wall: gameObjects.getWalls())
        {
            if (gameObject.isCollision(wall, direction))
                return true;
        }

        return false;
    }

    /**
     * checks if a box can be moved in the selected direction
     * @param direction
     * @return
     */
    public boolean checkBoxCollision(Direction direction)
    {

        // current player
        Player player = gameObjects.getPlayer();

        // searching for a break
        GameObject  stopped = null;
        for (GameObject gameObject: gameObjects.getAll())
        {
            if (!(gameObject instanceof Player)&&!(gameObject instanceof Home) && player.isCollision(gameObject, direction))
            {
                stopped = gameObject;
            }
        }
        // free move
        if (stopped == null)
        {
            return false;
        }

        // stopped by box
        if (stopped instanceof Box)
        {
            Box stoppedBox = (Box)stopped;

            if (checkWallCollision(stoppedBox, direction))
                return true;

            for (Box box : gameObjects.getBoxes())
            {
                if(stoppedBox.isCollision(box, direction))
                    return true;
            }

            switch (direction)
            {
                case LEFT:
                    stoppedBox.move(-FIELD_SELL_SIZE, 0);
                    break;
                case RIGHT:
                    stoppedBox.move(FIELD_SELL_SIZE, 0);
                    break;
                case UP:
                    stoppedBox.move(0, -FIELD_SELL_SIZE);
                    break;
                case DOWN:
                    stoppedBox.move(0, FIELD_SELL_SIZE);
            }
        }
        return false;
    }

    /**
     * checks if the all boxes are at home
     */
    public void checkCompletion()
    {
        boolean areHomesFree = false;

        for (Home home: gameObjects.getHomes())
        {
            boolean isCurrentHomeFree = true;

            for(Box box: gameObjects.getBoxes())
            {
                if (home.getX() == box.getX() && home.getY() == box.getY())
                {
                    isCurrentHomeFree = false;
                }
            }

            if(isCurrentHomeFree == true)
            {
                areHomesFree = true;
            }
        }

        if (areHomesFree == false)
            eventListener.levelCompleted(currentLevel);
    }
}
