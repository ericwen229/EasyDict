package main.controller;

import java.util.*;

import java.awt.event.*;
import javax.swing.event.*;
import java.beans.*;

import main.model.dict.Dict;

import main.view.*;

public class SearchPanelController implements FocusListener, DocumentListener, PropertyChangeListener {

    private boolean isEmpty = true;
    private boolean isGhost = false;

    private final String ghostText = "Search";

    private final SearchInputBox inputBox;
    private final ResultList resultList;
    private final SearchPanel searchPanel;

    public SearchPanelController(SearchPanel searchPanel) {
        this.searchPanel = searchPanel;
        this.inputBox = searchPanel.getInputBox();
        this.inputBox.addFocusListener(this);

        this.updateState();
        if (!this.inputBox.hasFocus()) {
            this.focusLost(null);
        }

        this.resultList = searchPanel.getResultList();
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
        this.updateState();
        if (this.isEmpty || this.isGhost) {
            this.unregisterListeners();
            try {
                this.inputBox.setText("");
                this.isGhost = false;
                this.inputBox.setSuccessColor();
            } finally {
                this.registerListeners();
            }
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        this.updateState();
        if (this.isEmpty) {
            this.unregisterListeners();
            try {
                this.inputBox.setText(this.ghostText);
                this.isGhost = true;
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
        boolean lastSuccess = true;
        if (!this.isEmpty && !this.isGhost) {
            String word = this.inputBox.getText();
            Dict d = Dict.createDict();
            ArrayList<String> preciseResult = d.searchWithCommonPrefix(word);
            ArrayList<String> fuzzyResult = d.searchWithEditDist(word, 2);
            ResultListController resultListController = new ResultListController(this.resultList);
            resultListController.setPreciseResult(preciseResult);
            resultListController.setFuzzyResult(fuzzyResult);
            lastSuccess = (preciseResult.size() > 0);
        }
        else {
            ResultListController resultListController = new ResultListController(this.resultList);
            resultListController.clearPreciseResult();
            resultListController.clearFuzzyResult();
        }
        if (lastSuccess) {
            this.inputBox.setSuccessColor();
        }
        else {
            this.inputBox.setFailColor();
        }
    }

    private void updateState() {
        this.isEmpty = this.inputBox.getText().length() == 0;
        if (this.isEmpty) {
            this.isGhost = true;
        }
    }

}
