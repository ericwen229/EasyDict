package main.controller;

import javax.swing.event.*;

import main.view.MainPanel;
import main.view.Result;

public class MainPanelController implements ListSelectionListener {

    private final MainPanel mainPanel;
    private int si = 0;

    public MainPanelController(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (si == 0) {
            si = 1;
            String key = ((Result)e.getSource()).getSelectedValue();
            if (key != null) {
                System.out.println(key);
                // TODO: retrieve detailed info and display
            }
        }
        else {
            si = 0;
        }
    }

}
