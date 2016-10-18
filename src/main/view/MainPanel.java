package main.view;

import main.controller.MainPaneController;

import javax.swing.*;

public class MainPanel extends JSplitPane {

    private SearchPane searchPane;
    private ResultPanel resultPanel;

    MainPanel() {
        super(JSplitPane.HORIZONTAL_SPLIT, true);

        this.searchPane = new SearchPane();
        this.resultPanel = new ResultPanel();
        this.setLeftComponent(this.searchPane);
        this.setRightComponent(this.resultPanel);

        this.setOneTouchExpandable(false);

        MainPaneController controller = new MainPaneController(this);
        this.searchPane.getResultList().getPreciseResult().addListSelectionListener(controller);
        this.searchPane.getResultList().getFuzzyResult().addListSelectionListener(controller);

    }

    void adjust() {
        this.searchPane.adjust();
        this.resultPanel.adjust();

        this.setDividerLocation(0.32);
    }

}
