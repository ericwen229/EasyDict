package main.controller;

import javax.swing.event.*;

import main.view.*;

public class MainPaneController implements ListSelectionListener {

    private final Result preciseResult;
    private final Result fuzzyResult;
    private int si = 0;

    public MainPaneController(Result preciseResult, Result fuzzyResult, ResultPane resultPane) {
        this.preciseResult = preciseResult;
        this.fuzzyResult = fuzzyResult;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (si == 0) {
            si = 1;
            String key = ((Result)e.getSource()).getSelectedValue();
            if (key != null) {
                System.out.println(key);
                // TODO: retrieve detailed info
            }
        }
        else {
            si = 0;
        }
    }

}
