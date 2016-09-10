/*
 * Copyright (c) Yulia Buranova
 */

package src.listeners;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

/**
 * Created by yulia on 10.09.16.
 */
public class UndoListener implements UndoableEditListener
{
    private UndoManager undoManager;

    public UndoListener(UndoManager undoManager)
    {
        this.undoManager = undoManager;
    }

    @Override
    public void undoableEditHappened(UndoableEditEvent undoableEditEvent)
    {
        undoManager.addEdit(undoableEditEvent.getEdit());
    }
}
