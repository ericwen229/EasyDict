package main.view;

import main.controller.MainPaneController;

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

        Result preciseResult = this.searchPane.getResultList().getPreciseResult();
        Result fuzzyResult = this.searchPane.getResultList().getFuzzyResult();
        MainPaneController controller = new MainPaneController(preciseResult, fuzzyResult, this.resultPane);
        preciseResult.addListSelectionListener(controller);
        fuzzyResult.addListSelectionListener(controller);
    }

    void adjust() {
        this.searchPane.adjust();
        this.resultPane.adjust();

        this.setDividerLocation(0.32);
    }

}
