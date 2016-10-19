package main.controller;

import javax.swing.event.*;

import main.model.dict.Dict;
import main.model.wordinfo.WordInfo;
import main.view.MainPanel;
import main.view.Result;

public class MainPanelController implements ListSelectionListener {

    private final MainPanel mainPanel;
    private int si = 0;

    public MainPanelController(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (si == 0) {
            si = 1;
            String word = ((Result)e.getSource()).getSelectedValue();
            if (word != null) {
                Dict d = Dict.createDict();
                WordInfo info = d.retrieveInfo(word);
                // TODO: display detailed info
            }
        }
        else {
            si = 0;
        }
    }

}
