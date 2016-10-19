package main.controller;

import main.model.wordinfo.WordInfo;
import main.view.ResultPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ResultPanelController extends MouseAdapter {

    private ResultPanel resultPanel;

    private final int maxTabNum = 5;
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
        else { // add tab
            int addIndex = 0;
            if (this.currTabNum == this.maxTabNum) { // reach max num, then set last tab
                addIndex = this.resultPanel.getSelectedIndex();
                this.resultPanel.setTitleAt(addIndex, word);
                this.resultPanel.setComponentAt(addIndex, scrollInfoPanel);
            }
            else { // add tab to last
                this.resultPanel.addTab(word, scrollInfoPanel);
                ++ this.currTabNum;
                addIndex = this.currTabNum - 1;
            }
            this.resultPanel.setSelectedIndex(addIndex); // select added tab (always at tail)
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            int index = this.resultPanel.getSelectedIndex();
            this.currTabNum = this.resultPanel.getTabCount();
            if (this.currTabNum == 1) {
                this.resultPanel.setWelcomeTab();
                this.resultPanel.setWelcome(true);
            } else {
                this.resultPanel.removeTabAt(index);
                -- this.currTabNum;
            }
        }
    }

}
