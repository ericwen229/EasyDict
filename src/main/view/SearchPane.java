package main.view;

import java.awt.*;
import javax.swing.*;

class SearchPane extends JPanel {

    private SearchInputBox inputBox;
    private ResultList resultList;

    private static SearchPane searchPane;

    private SearchPane() {
        super();

        this.inputBox = SearchInputBox.createInputBox();
        this.resultList = ResultList.createResultList();

        this.setLayout(new BorderLayout());
        this.add(this.inputBox, BorderLayout.NORTH);
        this.add(this.resultList, BorderLayout.CENTER);
    }

    static SearchPane createSearchPane() {
        if (SearchPane.searchPane == null) {
            SearchPane.searchPane = new SearchPane();
        }
        return SearchPane.searchPane;
    }

    void adjust() {
        this.inputBox.adjust();
        this.resultList.adjust();
    }

}
