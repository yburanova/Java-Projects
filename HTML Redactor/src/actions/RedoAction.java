/*
 * Copyright (c) Yulia Buranova
 */

package src.actions;

import src.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by yulia on 10.09.16.
 */
public class RedoAction extends AbstractAction
{
    private View view;

    public RedoAction(View view)
    {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        view.redo();
    }
}
