package main.controller;

import javax.swing.event.*;

import main.view.Result;

public class MainPaneController implements ListSelectionListener {

    private final Result result;
    private int si = 0;

    public MainPaneController(Result result) {
        this.result = result;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (si == 0) {
            si = 1;
            String key = this.result.getSelectedValue();
            if (key != null) {
                System.out.println(key);
            }
        }
        else {
            si = 0;
        }
    }

}
