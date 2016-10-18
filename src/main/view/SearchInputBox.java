package main.view;

import java.awt.*;
import javax.swing.*;

public class SearchInputBox extends JTextField {

    private final Color ghostColor = Color.LIGHT_GRAY;
    private final Color correctColor = Color.BLACK;
    private final Color errorColor = Color.RED;

    SearchInputBox() {
        super();
    }

    void adjust() {}

    public void setCorrectColor() {
        this.setForeground(this.correctColor);
    }

    public void setErrorColor() {
        this.setForeground(this.errorColor);
    }

    public void setGhostColor() {
        this.setForeground(this.ghostColor);
    }

}
