package main.view;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

class Result extends JList<String> {

    private final DefaultListModel<String> model = new DefaultListModel<>();

    Result() {
        super();

        this.setModel(this.model);
    }

    void adjust() {}

    void setList(ArrayList<String> list) {
        this.model.clear();
        list.forEach(this.model::addElement);
    }

}
