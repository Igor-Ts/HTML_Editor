package main;

import main.listeners.FrameListener;
import main.listeners.TabbedPaneChangeListener;
import main.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {

    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane(); // it's panel with two tabs.
    private JTextPane htmlTextPane = new JTextPane(); // first tab. It'll component for visualize html editor.
    private JEditorPane plainTextPane = new JEditorPane(); // It'll component for html editor as text. He'll show html code(tags).

    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException r) {
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
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case ("New") -> controller.createNewDocument();
            case ("Open") -> controller.openDocument();
            case ("Save") -> controller.saveDocument();
            case ("Save as...") -> controller.saveDocumentAs();
            case ("Exit") -> controller.exit();
            case ("About") -> showAbout();
        }

    }

    public void selectedTabChanged() {
        // 0 - html tab, 1 - plain
        switch (tabbedPane.getSelectedIndex()) {
            case (0) -> controller.setPlainText(plainTextPane.getText());
            case (1) -> plainTextPane.setText(controller.getPlainText());
        }
        resetUndo();
    }

    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);
    }

    public void initMenuBar() {
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

    // this method init all tabs in editor
    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        tabbedPane.add("HTML", new JScrollPane(htmlTextPane)); // first tab
        tabbedPane.add("Text", new JScrollPane(plainTextPane)); // second tab
        tabbedPane.setPreferredSize(new Dimension(200, 200)); // panel dimension
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
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo() {
        // cancel previous action
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        // return previous action
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            ExceptionHandler.log(e);
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected() {
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout() {
        JOptionPane.showMessageDialog(this, "HTML_Editor", "About", JOptionPane.INFORMATION_MESSAGE);
    }
}
