
package controller;

/*
 * Copyright (c) Yulia Buranova
 */

import model.Direction;
import model.GameObjects;
import model.Model;
import view.View;

/**
 * The Controller class is used for control operations and decisions.
 * @author yulia
 * @version 1.0
 * Created by yulia on 24.09.16.
 */
public class Controller implements EventListener
{
    private View view;
    private Model model;

    /**
     * Constructs a Controller object
     */
    public Controller()
    {
        model = new Model();
        model.restart();
        model.setEventListener(this);


        // view initialization
        view = new View(this);
        view.init();
        view.setEventListener(this);

    }

    public static void main(String[] args)
    {
        Controller controller = new Controller();
    }

    @Override
    public void move(Direction direction)
    {
        model.move(direction);
        view.update();
    }

    @Override
    public void restart()
    {
        model.restart();
        view.update();
    }

    @Override
    public void startNextLevel()
    {
        model.startNextLevel();
        view.update();
    }

    @Override
    public void levelCompleted(int level)
    {
        view.completed(level);
    }

    public GameObjects getGameObjects()
    {
        return model.getGameObjects();
    }
}
