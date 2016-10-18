package main.view;

import javax.swing.*;

class MainPane extends JSplitPane {

    private SearchPane searchPane;
    private ResultPane resultPane;

    MainPane() {
        super(JSplitPane.HORIZONTAL_SPLIT, true);

        this.searchPane = new SearchPane();
        this.resultPane = new ResultPane();
        this.setLeftComponent(this.searchPane);
        this.setRightComponent(this.resultPane);

        this.setOneTouchExpandable(false);
    }

    void adjust() {
        this.searchPane.adjust();
        this.resultPane.adjust();

        this.setDividerLocation(0.32);
    }

}
