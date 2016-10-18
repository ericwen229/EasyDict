package main.view;

import java.util.*;
import javax.swing.*;

public class Result extends JList<String> {

    private final DefaultListModel<String> model = new DefaultListModel<>();

    Result() {
        super();

        this.setModel(this.model);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
