package main.view;

import java.awt.*;
import javax.swing.*;

class MainPane extends JSplitPane {

    private SearchPane searchPane;
    // TODO: private ResultPane resultPane;
    private JPanel resultPane;

    private static MainPane mainPane;

    private MainPane() {
        super(JSplitPane.HORIZONTAL_SPLIT, true);

        this.searchPane = SearchPane.createSearchPane();
        // TODO: this.resultPane = ResultPane.createResultPane();
        this.resultPane = new JPanel();
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
        // TODO: this.resultPane.adjust();

        this.setDividerLocation(0.32);
    }

}
