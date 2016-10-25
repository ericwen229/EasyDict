package main.view;

// ================================
// Built-in modules

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

// ================================
// Class MainWindow

/**
 * MainWindow of program as a frame
 *
 * @author ericwen229
 * @see main.view.MainPanel
 */
public class MainWindow extends JFrame {

	// ================================
	// Members

	/**
	 * Main panel
	 */
	private MainPanel mainPanel;

	// ================================
	// Member functions

	/**
	 * Default class constructor that initializes components
	 * and their behaviours
	 */
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

	/**
	 * Adjust appearance
	 */
	void adjust() {
		this.mainPanel.adjust();
	}

}
