package main.view;

import main.controller.MainPanelController;

import javax.swing.*;

public class MainPanel extends JSplitPane {

    private SearchPanel searchPanel;
    private ResultPanel resultPanel;

    MainPanel() {
        super(JSplitPane.HORIZONTAL_SPLIT, true);

        this.searchPanel = new SearchPanel();
        this.resultPanel = new ResultPanel();
        this.setLeftComponent(this.searchPanel);
        this.setRightComponent(this.resultPanel);

        this.setOneTouchExpandable(false);

        MainPanelController controller = new MainPanelController(this);
        this.searchPanel.getResultList().getPreciseResult().addListSelectionListener(controller);
        this.searchPanel.getResultList().getFuzzyResult().addListSelectionListener(controller);

    }

    void adjust() {
        this.searchPanel.adjust();
        this.resultPanel.adjust();

        this.setDividerLocation(0.32);
    }

    public ResultPanel getResultPanel() {
        return this.resultPanel;
    }

}
