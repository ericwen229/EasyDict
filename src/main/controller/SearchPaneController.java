package main.controller;

import java.util.*;

import java.awt.event.*;
import javax.swing.event.*;
import java.beans.*;

import main.model.dict.Dict;

import main.view.*;

public class SearchPaneController implements FocusListener, DocumentListener, PropertyChangeListener {

    private boolean isEmpty = true;
    private boolean lastSuccess = false;

    private final String ghostText = "Search";

    private final SearchInputBox inputBox;
    private final ResultList resultList;
    private final SearchPane searchPane;

    public SearchPaneController(SearchPane searchPane) {
        this.searchPane = searchPane;
        this.inputBox = searchPane.getInputBox();
        this.inputBox.addFocusListener(this);

        this.updateState();
        if (!this.inputBox.hasFocus()) {
            this.focusLost(null);
        }

        this.resultList = searchPane.getResultList();
    }

    private void registerListeners() {
        this.inputBox.getDocument().addDocumentListener(this);
        this.inputBox.addPropertyChangeListener("foreground", this);
    }

    private void unregisterListeners() {
        this.inputBox.getDocument().removeDocumentListener(this);
        this.inputBox.removePropertyChangeListener("foreground", this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.isEmpty) {
            this.unregisterListeners();
            try {
                this.inputBox.setText("");
                this.inputBox.setCorrectColor();
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
                this.inputBox.setGhostColor();
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
        this.updateContent();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.updateContent();
    }

    private void updateContent() {
        this.updateState();
        this.lastSuccess = true;
        if (!this.isEmpty) {
            String word = this.inputBox.getText();
            Dict d = Dict.createDict();
            ArrayList<String> preciseResult = d.searchWithCommonPrefix(word);
            ArrayList<String> fuzzyResult = d.searchWithEditDist(word, 2);
            ResultListController resultListController = new ResultListController(this.resultList);
            resultListController.setPreciseResult(preciseResult);
            resultListController.setFuzzyResult(fuzzyResult);
            this.lastSuccess = (preciseResult.size() > 0);
        }
        else {
            ResultListController resultListController = new ResultListController(this.resultList);
            resultListController.clearPreciseResult();
            resultListController.clearFuzzyResult();
        }
        if (this.lastSuccess) {
            this.inputBox.setCorrectColor();
        }
        else {
            this.inputBox.setErrorColor();
        }
    }

    private void updateState() {
        this.isEmpty = this.inputBox.getText().length() == 0;
    }

}
