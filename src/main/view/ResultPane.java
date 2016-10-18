package main.view;

import javax.swing.*;

public class ResultPane extends JTabbedPane {

    ResultPane() {
        super(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        this.addTab("Welcome", null);
    }

    void adjust() {}

}
