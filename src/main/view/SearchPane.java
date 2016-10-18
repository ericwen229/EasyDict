package main.view;

import java.awt.*;
import javax.swing.*;

import main.controller.*;

public class SearchPane extends JPanel {

    private SearchInputBox inputBox;
    private ResultList resultList;

    SearchPane() {
        super();

        this.inputBox = new SearchInputBox();
        this.resultList = new ResultList();

        this.setLayout(new BorderLayout());
        this.add(this.inputBox, BorderLayout.NORTH);
        this.add(this.resultList, BorderLayout.CENTER);

        this.inputBox.getDocument().addDocumentListener(new SearchPaneController(this));
    }

    void adjust() {
        this.inputBox.adjust();
        this.resultList.adjust();
    }

    public SearchInputBox getInputBox() {
        return this.inputBox;
    }

    public ResultList getResultList() {
        return this.resultList;
    }

}
