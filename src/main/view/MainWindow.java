package main.view;

import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame {

    private MainPanel mainPanel;

    public MainWindow() {
        super();

        this.mainPanel = new MainPanel();

        this.setLayout(new BorderLayout());
        this.add(this.mainPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(800, 600));
        this.setVisible(true);

        this.adjust();
    }

    void adjust() {
        this.mainPanel.adjust();
    }

}
