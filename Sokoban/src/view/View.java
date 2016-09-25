package view;

import controller.*;
import model.GameObjects;

import javax.swing.*;

/**
 * The View class is used for the graphical representation of the game
 * @author yulia
 * @version 1.0
 */

public class View extends JFrame {
    private Controller controller;
    private Field field;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void setEventListener(EventListener eventListener)
    {
        field.setEventListener(eventListener);
    }

    /**
     * initializes a graphical field for the game
     */
    public void init() {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Сокобан");
        setVisible(true);
    }

    public void update()
    {
        field.repaint();
    }

    /**
     * tells user that a level is completed
     * @param level
     */
    public void completed(int level)
    {
        update();
        JOptionPane.showMessageDialog(null, "Level " + level + " Completed", "Level", JOptionPane.INFORMATION_MESSAGE);
        controller.startNextLevel();
    }

    public GameObjects getGameObjects()
    {
        return controller.getGameObjects();
    }
}
