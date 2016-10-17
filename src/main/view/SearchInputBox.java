package main.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.beans.*;

class SearchInputBox extends JTextField implements FocusListener, DocumentListener, PropertyChangeListener {

    private boolean isEmpty;
    private Color ghostColor = Color.LIGHT_GRAY;
    private Color foregroundColor;
    private final String ghostText = "Search";

    private static SearchInputBox inputBox;

    private SearchInputBox() {
        super();
        this.addFocusListener(this);
        this.registerListeners();
        this.updateState();
        if (!this.hasFocus()) {
            this.focusLost(null);
        }
    }


    static SearchInputBox createInputBox() {
        if (SearchInputBox.inputBox == null) {
            SearchInputBox.inputBox = new SearchInputBox();
        }
        return SearchInputBox.inputBox;
    }

    void delete() {
        this.unregisterListeners();
        this.removeFocusListener(this);
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
        foregroundColor = this.getForeground();
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (isEmpty) {
            unregisterListeners();
            try {
                this.setText("");
                this.setForeground(foregroundColor);
            } finally {
                registerListeners();
            }
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        if (isEmpty) {
            unregisterListeners();
            try {
                this.setText(ghostText);
                this.setForeground(ghostColor);
            } finally {
                registerListeners();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateState();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateState();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateState();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateState();
    }

    void adjust() {}

}
