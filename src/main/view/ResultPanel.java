package main.view;

import javax.swing.*;

public class ResultPanel extends JTabbedPane {

    private boolean isWelcome;

    ResultPanel() {
        super(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);

        this.addTab("Welcome", null);
        this.isWelcome = true;
    }

    void adjust() {}

    public boolean getWelcome() {
        return this.isWelcome;
    }

    public void setWelcome(boolean isWelcome) {
        this.isWelcome = isWelcome;
    }

}
