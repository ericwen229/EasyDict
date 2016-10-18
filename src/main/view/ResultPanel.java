package main.view;

import javax.swing.*;

public class ResultPanel extends JTabbedPane {

    ResultPanel() {
        super(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

        this.addTab("Welcome", null);
    }

    void adjust() {}

}
