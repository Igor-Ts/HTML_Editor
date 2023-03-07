package main;

import main.listeners.FrameListener;
import main.listeners.TabbedPaneChangeListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {

    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane(); // it's panel with two tabs.
    private JTextPane htmlTextPane = new JTextPane(); // first tab. It'll component for visualize html editor.
    private JEditorPane plainTextPane = new JEditorPane(); // It'll component for html editor as text. He'll show html code(tags).

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException r) {
            ExceptionHandler.log(r);
        }
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void selectedTabChanged() {

    }

    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }

    public void initMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this,jMenuBar);
        MenuHelper.initEditMenu(this,jMenuBar);
        MenuHelper.initStyleMenu(this,jMenuBar);
        MenuHelper.initAlignMenu(this,jMenuBar);
        MenuHelper.initColorMenu(this,jMenuBar);
        MenuHelper.initFontMenu(this,jMenuBar);
        MenuHelper.initHelpMenu(this,jMenuBar);
        getContentPane().add(jMenuBar, BorderLayout.NORTH);

    }

    // this method init all tabs in editor
    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        tabbedPane.add("HTML",new JScrollPane(htmlTextPane)); // first tab
        tabbedPane.add("Text",new JScrollPane(plainTextPane)); // second tab
        tabbedPane.setPreferredSize(new Dimension(200,200)); // panel dimension
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this)); // eventListener
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public void exit() {
        controller.exit();
    }

    public boolean canUndo() {
        return false;
    }

    public boolean canRedo() {
        return false;
    }

}
