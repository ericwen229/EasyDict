package main.view;

import java.awt.*;
import javax.swing.*;

class MainPane extends JSplitPane {

    private SearchPane searchPane;
    private ResultPane resultPane;

    private static MainPane mainPane;

    private MainPane() {
        super(JSplitPane.HORIZONTAL_SPLIT, true);

        this.searchPane = SearchPane.createSearchPane();
        this.resultPane = ResultPane.createResultPane();
        this.setLeftComponent(this.searchPane);
        this.setRightComponent(this.resultPane);

        this.setOneTouchExpandable(false);
    }

    static MainPane createMainPane() {
        if (MainPane.mainPane == null) {
            MainPane.mainPane = new MainPane();
        }
        return MainPane.mainPane;
    }

    void adjust() {
        this.searchPane.adjust();
        this.resultPane.adjust();

        this.setDividerLocation(0.32);
    }

}
