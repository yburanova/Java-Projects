/*
 * Copyright (c) Yulia Buranova
 */

package src.listeners;

import src.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * Created by yulia on 10.09.16.
 */
public class TextEditMenuListener implements MenuListener
{
    private View view;

    public TextEditMenuListener(View view)
    {
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent menuEvent)
    {
        JMenu jMenu = (JMenu) menuEvent.getSource();
        Component[] components = jMenu.getMenuComponents();
        for(Component component: components)
            component.setEnabled(view.isHtmlTabSelected());
    }

    @Override
    public void menuDeselected(MenuEvent menuEvent)
    {

    }

    @Override
    public void menuCanceled(MenuEvent menuEvent)
    {

    }
}
