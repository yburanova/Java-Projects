package src;

/*
 * Copyright (c) Yulia Buranova
 */;

import src.listeners.FrameListener;
import src.listeners.TabbedPaneChangeListener;
import src.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by yulia on 21.08.16.
 */
public class View extends JFrame implements ActionListener
{
    private Controller controller;

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);


    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        } catch (InstantiationException e) {
            ExceptionHandler.log(e);
        } catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        }
    }

    public UndoListener getUndoListener()
    {
        return undoListener;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        String command = actionEvent.getActionCommand();

        switch (command)
        {
            case "New":
                controller.createNewDocument();
                break;
            case "Open":
                controller.openDocument();
                break;
            case "Save":
                controller.saveDocument();
                break;
            case "Save as...":
                controller.saveDocumentAs();
                break;
            case "Exit":
                controller.exit();
                break;
            case "About":
                showAbout();
                break;

        }
    }

    public Controller getController()
    {
        return controller;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    // src.View initialization
    public void init()
    {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }

    public void initMenuBar()
    {
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);

        getContentPane().add(jMenuBar, BorderLayout.NORTH);


    }

    // initializing of editor panels
    public void initEditor()
    {
        // content type
        htmlTextPane.setContentType("text/html");

        // left side with HTML code
        JScrollPane jScrollPane = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", jScrollPane);

        // right side with Text
        JScrollPane jScrollPane1 = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Text", jScrollPane1);

        // size of the Panel
        tabbedPane.setPreferredSize(new Dimension(700, 400));

        // adding listeners
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));

        // setting the panel in the center
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    // initialization of graphical user interface
    public void initGui()
    {
        // add menu
        initMenuBar();

        // add editor
        initEditor();

        // size the frame
        pack();
    }

    // check the chosen Tab
    // control the switch between two tabs
    public void selectedTabChanged()
    {
        int index = tabbedPane.getSelectedIndex();

        if (index == 0)
        {
            controller.setPlainText(plainTextPane.getText());
        }
        else if (index == 1)
        {
            plainTextPane.setText(controller.getPlainText());
        }

        resetUndo();
    }

    // check if the chosen Tab is HTML one
    public boolean isHtmlTabSelected()
    {
        return tabbedPane.getSelectedIndex() == 0;
    }

    // select the HTML tab
    public void selectHtmlTab()
    {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    // check if we can do the undo operation
    public boolean canUndo()
    {
        return undoManager.canUndo();
    }

    // check if we can do the redo operation
    public boolean canRedo()
    {
        return undoManager.canRedo();
    }

    // undo operation
    public void undo()
    {
        try
        {
            undoManager.undo();
        }
        catch(CannotUndoException e)
        {
            ExceptionHandler.log(e);
        }
    }

    // redo operation
    public void redo()
    {
        try
        {
            undoManager.redo();
        }
        catch (CannotRedoException e)
        {
            ExceptionHandler.log(e);
        }
    }

    // updae the document
    public void update()
    {
        htmlTextPane.setDocument(controller.getDocument());
    }


    // information about the program
    public void showAbout()
    {
        JOptionPane.showMessageDialog(this, "Thank you for your visit!", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    // remove the undo history
    public void resetUndo()
    {
        undoManager.discardAllEdits();
    }

    // exit
    public void exit()
    {
        controller.exit();
    }
}
