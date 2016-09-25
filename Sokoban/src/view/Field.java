/*
 * Copyright (c) Yulia Buranova
 */

package view;


import controller.EventListener;
import model.Direction;
import model.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

/**
 * The class Field is used for a graphical representation of the gaming field
 * @author yulia
 * @version 1.0
 * Created by yulia on 25.09.16.
 */
public class Field extends JPanel
{
    private View view;
    private EventListener eventListener;

    public Field(View view)
    {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, 500, 500);

        Set<GameObject> gameObjectSet = view.getGameObjects().getAll();

        for (GameObject gameObject : gameObjectSet) {
            gameObject.draw(g);
        }
    }

    /**
     * The class KeyHangler is used for key events handling
     */
    public class KeyHandler extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    eventListener.move(Direction.LEFT);
                    break;

                case KeyEvent.VK_RIGHT:
                    eventListener.move(Direction.RIGHT);
                    break;

                case KeyEvent.VK_DOWN:
                    eventListener.move(Direction.DOWN);
                    break;

                case KeyEvent.VK_UP:
                    eventListener.move(Direction.UP);
                    break;

                case KeyEvent.VK_R:
                    eventListener.restart();
                    break;
            }
        }
    }

}
