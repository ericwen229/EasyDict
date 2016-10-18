package main.view;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

import main.controller.ResultController;

public class Result extends JList<String> implements ListSelectionListener {

    private int si = 0;
    private String currentKey;

    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final ResultController controller;

    Result() {
        super();

        this.setModel(this.model);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.addListSelectionListener(this);

        this.controller = new ResultController(this);
    }

    void adjust() {}

    void setList(ArrayList<String> list) {
        this.model.clear();
        list.forEach(this.model::addElement);
    }

    public void valueChanged(ListSelectionEvent e) {
        if (si == 0) {
            si = 1;
            String key = this.getSelectedValue();
            if (key != null) {
                this.currentKey = key;
            }
        }
        else {
            si = 0;
        }
    }

    public String getCurrentKey() {
        return this.currentKey;
    }

}
