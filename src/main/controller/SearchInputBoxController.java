package main.controller;

import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.beans.*;

import main.model.dict.Dict;

import main.view.SearchInputBox;

public class SearchInputBoxController implements Controller, FocusListener, DocumentListener, PropertyChangeListener {

    private final Color ghostColor = Color.LIGHT_GRAY;
    private final Color correctColor = Color.BLACK;
    private final Color errorColor = Color.RED;
    private final String ghostText = "Search";

    private boolean isEmpty = true;
    private boolean lastSuccess = false;

    private final SearchInputBox inputBox;

    private static SearchInputBoxController controller;

    private SearchInputBoxController(SearchInputBox inputBox) {
        this.inputBox = inputBox;
        this.inputBox.addFocusListener(this);
        this.registerListeners();
        this.updateState();
        if (!this.inputBox.hasFocus()) {
            this.focusLost(null);
        }
    }

    public static SearchInputBoxController createSearchInputBoxController(SearchInputBox inputBox) {
        if (SearchInputBoxController.controller == null) {
            SearchInputBoxController.controller = new SearchInputBoxController(inputBox);
        }
        return SearchInputBoxController.controller;
    }

    private void registerListeners() {
        this.inputBox.getDocument().addDocumentListener(this);
        this.inputBox.addPropertyChangeListener("foreground", this);
    }

    private void unregisterListeners() {
        this.inputBox.getDocument().removeDocumentListener(this);
        this.inputBox.removePropertyChangeListener("foreground", this);
    }

    private void updateState() {
        isEmpty = this.inputBox.getText().length() == 0;
    }

    @Override
    public void update() {
        if (this.isEmpty) {
            this.lastSuccess = true;
            return;
        }
        String word = this.inputBox.getText();
        Dict d = Dict.createDict();
        ArrayList<String> preciseResult = d.searchWithCommonPrefix(word);
        ArrayList<String> fuzzyResult = d.searchWithEditDist(word, 2);
        ResultListController resultListController = ResultListController.createResultListController(null);
        resultListController.setPreciseResult(preciseResult);
        resultListController.setFuzzyResult(fuzzyResult);
        this.lastSuccess = (preciseResult.size() > 0);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.isEmpty) {
            this.unregisterListeners();
            try {
                this.inputBox.setText("");
                this.inputBox.setForeground(this.correctColor);
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
                this.inputBox.setText(this.ghostText);
                this.inputBox.setForeground(this.ghostColor);
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
        this.update();
        if (!this.isLastSuccess()) {
            this.inputBox.setForeground(this.errorColor);
        }
        else {
            this.inputBox.setForeground(this.correctColor);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.updateState();
        this.update();
        if (!this.isLastSuccess()) {
            this.inputBox.setForeground(this.errorColor);
        }
        else {
            this.inputBox.setForeground(this.correctColor);
        }
    }

    private boolean isLastSuccess() {
        return this.lastSuccess;
    }

}
