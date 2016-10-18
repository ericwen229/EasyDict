package main.view;

import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame {

    private MainPane mainPane;

    public MainWindow() {
        super();

        this.mainPane = new MainPane();

        this.setLayout(new BorderLayout());
        this.add(this.mainPane, BorderLayout.CENTER);

        this.setSize(new Dimension(800, 600));
        this.setVisible(true);

        this.adjust();
    }

    void adjust() {
        this.mainPane.adjust();
    }

}
