package main.listeners;

import main.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class UndoMenuListener implements MenuListener {

    private final View view;
    private final JMenuItem undoItem;
    private final JMenuItem redoItem;

    public UndoMenuListener(View view, JMenuItem undoItem, JMenuItem redoItem) {
        this.view = view;
        this.undoItem = undoItem;
        this.redoItem = redoItem;
    }

    @Override
    public void menuSelected(MenuEvent e) {

    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
