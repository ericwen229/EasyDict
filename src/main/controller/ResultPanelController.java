package main.controller;

import main.model.wordinfo.WordInfo;
import main.view.ResultPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;

public class ResultPanelController extends MouseAdapter {

    private ResultPanel resultPanel;

    private int currTabNum;

    public ResultPanelController(ResultPanel resultPanel) {
        this.resultPanel = resultPanel;
        this.currTabNum = this.resultPanel.getTabCount();
    }

    public void addTab(String word, WordInfo info) {
        int index = this.resultPanel.indexOfTab(word);
        if (index != -1) {
            this.resultPanel.setSelectedIndex(index);
            return;
        }
        // TODO: create tab panel
        JEditorPane infoPanel = new JEditorPane();
        infoPanel.setEditable(false);
        infoPanel.setContentType("text/html");
        infoPanel.setText(info.generateHTML(word));
        JScrollPane scrollInfoPanel = new JScrollPane(infoPanel);
        if (this.resultPanel.getWelcome()) {
            this.resultPanel.setTitleAt(0, word);
            this.resultPanel.setComponentAt(0, scrollInfoPanel);
            this.resultPanel.setWelcome(false);
        }
        else {
            this.resultPanel.addTab(word, scrollInfoPanel);
            this.resultPanel.setSelectedComponent(scrollInfoPanel);
        }
    }

}
