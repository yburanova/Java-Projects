/*
 * Copyright (c) Yulia Buranova
 */

package src.listeners;

import src.View;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by yulia on 05.09.16.
 */
public class FrameListener extends WindowAdapter
{
    private View view;

    public FrameListener(View view)
    {
        this.view = view;
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        view.exit();
    }
}
