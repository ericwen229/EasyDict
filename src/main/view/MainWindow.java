package main.view;

import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame {

    private MainPane mainPane;

    private static MainWindow mainWindow;

    private MainWindow() {
        super();

        this.mainPane = MainPane.createMainPane();

        this.setLayout(new BorderLayout());
        this.add(this.mainPane, BorderLayout.CENTER);

        this.setSize(new Dimension(800, 600));
        this.setVisible(true);

        this.mainPane.adjust();
    }

    public static MainWindow createMainWindow() {
        if (MainWindow.mainWindow == null) {
            MainWindow.mainWindow = new MainWindow();
        }
        return MainWindow.mainWindow;
    }

}
