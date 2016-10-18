package main.controller;

import java.util.*;
import javax.swing.*;

import main.view.Result;

public class ResultController {

    private final Result result;
    private final DefaultListModel<String> model;

    public ResultController(Result result) {
        this.result = result;
        this.model = new DefaultListModel<>();
        this.result.setModel(this.model);
    }

    void setList(ArrayList<String> list) {
        this.clear();
        list.forEach(this.model::addElement);
    }

    void clear() {
        this.model.clear();
    }

}
