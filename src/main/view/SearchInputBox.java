package main.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.beans.*;

import main.controller.SearchInputBoxController;

public class SearchInputBox extends JTextField implements FocusListener, DocumentListener, PropertyChangeListener {

    private final Color ghostColor = Color.LIGHT_GRAY;
    private final Color correctColor = Color.BLACK;
    private final Color errorColor = Color.RED;
    private final String ghostText = "Search";
    private final SearchInputBoxController controller;

    private boolean isEmpty;

    private static SearchInputBox inputBox;

    private SearchInputBox() {
        super();

        this.addFocusListener(this);
        this.registerListeners();
        this.updateState();
        if (!this.hasFocus()) {
            this.focusLost(null);
        }

        this.controller = new SearchInputBoxController(this);
    }


    static SearchInputBox createInputBox() {
        if (SearchInputBox.inputBox == null) {
            SearchInputBox.inputBox = new SearchInputBox();
        }
        return SearchInputBox.inputBox;
    }

    private void registerListeners() {
        this.getDocument().addDocumentListener(this);
        this.addPropertyChangeListener("foreground", this);
    }

    private void unregisterListeners() {
        this.getDocument().removeDocumentListener(this);
        this.removePropertyChangeListener("foreground", this);
    }

    private void updateState() {
        isEmpty = this.getText().length() == 0;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.isEmpty) {
            this.unregisterListeners();
            try {
                this.setText("");
                this.setForeground(this.correctColor);
            } finally {
                this.registerListeners();
            }
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.isEmpty) {
            this.unregisterListeners();
            try {
                this.setText(this.ghostText);
                this.setForeground(this.ghostColor);
            } finally {
                this.registerListeners();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.updateState();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        this.updateState();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        this.updateState();
        boolean success = this.controller.update();
        if (!success) {
            this.setForeground(this.errorColor);
        }
        else {
            this.setForeground(this.correctColor);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.updateState();
        boolean success = this.controller.update();
        if (!success) {
            this.setForeground(this.errorColor);
        }
        else {
            this.setForeground(this.correctColor);
        }
    }

    void adjust() {}

    public boolean isEmpty() {
        return this.isEmpty;
    }

}
