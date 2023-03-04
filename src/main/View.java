package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {

    private Controller controller;
    private JTabbedPane tabbedPane; // it's panel with two tabs.
    private JTextPane htmlTextPane; // first tab. It'll component for visualize html editor.
    private JEditorPane plainTextPane; // It'll component for html editor as text. He'll show html code(tags).

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void init() {

    }

    public void exit() {
        controller.exit();
    }

}
