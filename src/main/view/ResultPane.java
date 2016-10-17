package main.view;

import javax.swing.*;

class ResultPane extends JTabbedPane {

    private static ResultPane resultPane;

    private ResultPane() {
        super(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        this.addTab("hello", null);
        this.addTab("hello", null);
        this.addTab("hello", null);
        this.addTab("hello", null);
        this.addTab("hello", null);
        this.addTab("hello", null);
        this.addTab("hello", null);
        this.addTab("hello", null);
        this.addTab("hello", null);
    }

    static ResultPane createResultPane() {
        if (ResultPane.resultPane == null) {
            ResultPane.resultPane = new ResultPane();
        }
        return ResultPane.resultPane;
    }

    void adjust() {}

}
