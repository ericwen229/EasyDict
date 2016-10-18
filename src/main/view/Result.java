package main.view;

import java.util.ArrayList;
import javax.swing.*;

import main.controller.MainPaneController;

public class Result extends JList<String> {

    private final DefaultListModel<String> model = new DefaultListModel<>();

    Result() {
        super();

        this.setModel(this.model);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.addListSelectionListener(new MainPaneController(this));
    }

    void adjust() {}

    void setList(ArrayList<String> list) {
        this.clear();
        list.forEach(this.model::addElement);
    }

    void clear() {
        this.model.clear();
    }

}
