package main.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

class Result extends JList<String> {

    Result(String title) {
        super();

        Border titleBorder = BorderFactory.createTitledBorder(title);
        this.setBorder(titleBorder);

        this.setVisible(true);
    }

    void adjust() {}

}
