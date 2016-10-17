package main.view;

import java.awt.*;
import javax.swing.*;

class SearchInputBox extends JTextField {

    private static SearchInputBox inputBox;

    private SearchInputBox() {
        super();

        this.setVisible(true);
    }

    static SearchInputBox createInputBox() {
        if (SearchInputBox.inputBox == null) {
            SearchInputBox.inputBox = new SearchInputBox();
        }
        return SearchInputBox.inputBox;
    }

    void adjust() {}

}
