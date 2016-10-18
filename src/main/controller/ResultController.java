package main.controller;

import main.view.Result;

import javax.swing.*;
import java.util.*;

public class ResultController {

    private final Result result;
    private static final DefaultListModel<String> model = new DefaultListModel<>();

    public ResultController(Result result) {
        this.result = result;
        this.result.setModel(ResultController.model);
    }

    void setList(ArrayList<String> list) {
        this.clear();
        list.forEach(this.model::addElement);
    }

    void clear() {
        this.model.clear();
    }

}
