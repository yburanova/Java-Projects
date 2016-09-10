/*
 * Copyright (c) Yulia Buranova
 */

package src.listeners;

import src.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by yulia on 06.09.16.
 */
public class TabbedPaneChangeListener implements ChangeListener
{
    private View view;

    public TabbedPaneChangeListener(View view)
    {
        this.view = view;
    }

    @Override
    public void stateChanged(ChangeEvent changeEvent)
    {
        view.selectedTabChanged();
    }
}
