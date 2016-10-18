package main.view;

import javax.swing.*;

import main.controller.SearchInputBoxController;

public class SearchInputBox extends JTextField {

    private static SearchInputBox inputBox;

    private SearchInputBox() {
        super();

        SearchInputBoxController.createSearchInputBoxController(this);
    }


    static SearchInputBox createInputBox() {
        if (SearchInputBox.inputBox == null) {
            SearchInputBox.inputBox = new SearchInputBox();
        }
        return SearchInputBox.inputBox;
    }

    void adjust() {}

}
