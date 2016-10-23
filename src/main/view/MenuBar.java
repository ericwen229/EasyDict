package main.view;

// ================================
// Built-in modules

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ================================
// Class MenuBar

/**
 * Menu bar
 *
 * @author ericwen229
 * @see main.view.MainWindow
 */
class MenuBar extends JMenuBar {

	// ================================
	// Member functions

	/**
	 * Default class constructor that initializes components
	 * and add event listeners
	 */
	MenuBar() {
		super();

		JMenuItem help = new JMenuItem("EasyDict Help");
		help.setMnemonic('H');
		ActionListener listener = (ActionEvent e) -> {
			JOptionPane.showMessageDialog(null,
					"Enter word in input box to get results\n" +
							"Single-click a result to get detailed information\n" +
							"Double-click a tab to close a tab", "Help", JOptionPane.INFORMATION_MESSAGE);
		};
		help.addActionListener(listener);

		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');

		helpMenu.add(help);
		this.add(helpMenu);
	}

}
