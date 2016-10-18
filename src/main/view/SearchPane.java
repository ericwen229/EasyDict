package main.view;

import java.awt.*;
import javax.swing.*;

import main.controller.*;

class SearchPane extends JPanel {

    private SearchInputBox inputBox;
    private ResultList resultList;

    SearchPane() {
        super();

        this.inputBox = new SearchInputBox();
        this.resultList = new ResultList();

        this.setLayout(new BorderLayout());
        this.add(this.inputBox, BorderLayout.NORTH);
        this.add(this.resultList, BorderLayout.CENTER);

        this.inputBox.getDocument().addDocumentListener(new SearchPaneController(this.inputBox, this.resultList));
    }

    void adjust() {
        this.inputBox.adjust();
        this.resultList.adjust();
    }

    ResultList getResultList() {
        return this.resultList;
    }

}
